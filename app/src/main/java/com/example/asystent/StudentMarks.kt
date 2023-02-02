package com.example.asystent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.adapter.MarkA
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.data.entity.Mark
import com.example.asystent.databinding.ActivityStudentMarksBinding

class StudentMarks : AppCompatActivity() {

    private lateinit var sub: String
    private lateinit var z: String
    lateinit var binding: ActivityStudentMarksBinding
    private val studentAdapter by lazy { MarkA() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentMarksBinding.inflate(layoutInflater)
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
            marksListRV.apply {
                layoutManager = LinearLayoutManager(this@StudentMarks)
                adapter = studentAdapter
            }
        }
    }

    private fun checkItem(db: SchoolDatabase) {
        val studentId = intent.extras!!.getString("bundle_student_id").toString()
        val list = db.schoolDao().getStudentWithMarks(studentId)
        binding.apply {
            if (list.first().marks.isNotEmpty()) {
                val marks: MutableList<Mark> = mutableListOf()
                for(i in list.first().marks) {
                    if (i.group.equals(sub)) {
                        marks.add(i)
                    }
                }
                warningText.visibility = View.GONE
                marksListRV.visibility = View.VISIBLE
                studentAdapter.differ.submitList(marks)
                initRecyclerView()
            }
            else {
                marksListRV.visibility = View.GONE
                warningText.visibility = View.VISIBLE
            }
        }
    }
}