package com.example.asystent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.asystent.adapter.StudentA
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.data.entity.Student
import com.example.asystent.databinding.ActivityStudentsListBinding

class StudentsList : AppCompatActivity() {

    lateinit var sub: String
    lateinit var z: String
    lateinit var binding: ActivityStudentsListBinding
    private val studentAdapter by lazy { StudentA(z, sub) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)
        val key = intent.extras?.getString("key").toString()
        z = key
        val subj = intent.extras?.getString("subject").toString()
        sub = subj
        checkItem(db)
    }

    private fun initRecyclerView() {
        binding.apply {
            studentsListRV.apply {
                layoutManager = LinearLayoutManager(this@StudentsList)
                adapter = studentAdapter
            }
        }
    }

    private fun checkItem(db: SchoolDatabase) {
        val subjectId = intent.extras!!.getString("bundle_subject_id")!!.toInt()
        val list = db.schoolDao().getStudentsOfSubject(subjectId)
        binding.apply {
            if (list.first().students.isNotEmpty()) {
                val students = list.first().students
                warningText.visibility = View.GONE
                studentsListRV.visibility = View.VISIBLE
                studentAdapter.differ.submitList(students)
                initRecyclerView()
            }
            else {
                studentsListRV.visibility = View.GONE
                warningText.visibility = View.VISIBLE
            }
        }
    }
}