package com.example.asystent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.adapter.SubjectA
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.databinding.ActivitySubjectsListBinding

class SubjectsList : AppCompatActivity() {

    lateinit var z: String
    private lateinit var binding: ActivitySubjectsListBinding
    private val subjectAdapter by lazy { SubjectA(z) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)

        val key = intent.extras?.getString("key").toString()
        z = key
        checkItem(db)
    }

    private fun initRecyclerView() {
        binding.apply {
            subjectsListRV.apply {
                layoutManager = LinearLayoutManager(this@SubjectsList)
                adapter = subjectAdapter
            }
        }
    }

    private fun checkItem(db: SchoolDatabase) {
        binding.apply {
            if (db.schoolDao().readAllSubjects().isNotEmpty()) {
                warningText.visibility = View.GONE
                subjectsListRV.visibility = View.VISIBLE
                subjectAdapter.differ.submitList(db.schoolDao().readAllSubjects())
                initRecyclerView()
            }
            else {
                subjectsListRV.visibility = View.GONE
                warningText.visibility = View.VISIBLE
            }
        }
    }
}