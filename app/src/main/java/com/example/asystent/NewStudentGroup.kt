package com.example.asystent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.adapter.StudentSubjectAdapter
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.data.entity.Student
import com.example.asystent.data.entity.StudentSubjectCrossRef
import com.example.asystent.data.entity.Subject
import com.example.asystent.databinding.ActivityNewStudentGroupBinding
import com.example.asystent.model.SubjectModelCheck
import com.google.android.material.snackbar.Snackbar

class NewStudentGroup : AppCompatActivity() {

    lateinit var binding: ActivityNewStudentGroupBinding
    lateinit var subjectMenuAdapter: StudentSubjectAdapter
    private lateinit var studentEntity: Student
    private lateinit var crossRefEntity: StudentSubjectCrossRef
    lateinit var name: String
    lateinit var surname: String
    lateinit var number: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)

        name = intent.extras?.getString("name") ?: ""
        surname = intent.extras?.getString("surname") ?: ""
        number = intent.extras?.getString("number") ?: ""

        val subjectsList = db.schoolDao().readAllSubjects()
        val data = getData(subjectsList)

        initRecyclerView()
        subjectMenuAdapter.submitData(data)

        binding.newStudentAddBT.setOnClickListener {
            val checkedList = ArrayList<Int>()
            var checked = false
            data.forEach{ i ->
                if (i.isSelected) {
                    checked = true
                    checkedList.add(i.id)
                }
            }
            if (checked) {
                studentEntity = Student(number, name, surname)
                db.schoolDao().addStudent(studentEntity)

                checkedList.forEach { i ->
                    crossRefEntity = StudentSubjectCrossRef(number, i)
                    db.schoolDao().addStudentSubjectCrossRef(crossRefEntity)
                }
                finish()

                val intent = Intent(this@NewStudentGroup, MainActivity::class.java)
                Toast.makeText(this, "Dodano nowego studenta", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            else {
                Snackbar.make(it, "Wybierz przedmiot!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun getData(list: MutableList<Subject>): MutableList<SubjectModelCheck> {
        val newList: MutableList<SubjectModelCheck> = mutableListOf()
        for (i in list.indices) {
            newList.add(SubjectModelCheck(list[i].id, list[i].name, list[i].day, list[i].hours, false))
        }
        return newList
    }

    private fun initRecyclerView() {
        binding.apply {
            newStudentSubjectsRV.apply {
                layoutManager = LinearLayoutManager(this@NewStudentGroup)
                subjectMenuAdapter = StudentSubjectAdapter()
                adapter = subjectMenuAdapter
            }
        }
    }
}