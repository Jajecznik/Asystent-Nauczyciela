package com.example.asystent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)

        binding.newSubject.setOnClickListener {
            val intent = Intent(this@MainActivity, NewSubject::class.java)
            startActivity(intent)
        }

        binding.newStudent.setOnClickListener {
            if(db.schoolDao().readAllSubjects().isEmpty()) {
                Snackbar.make(it, "Najpierw dodaj zajęcia!", Snackbar.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this@MainActivity, NewStudent::class.java)
                startActivity(intent)
            }
        }

        binding.newMark.setOnClickListener {
            val intent = Intent(this@MainActivity, SubjectsList::class.java)
            intent.putExtra("key", "marks")
            startActivity(intent)
        }

        binding.studentsList.setOnClickListener {
            val intent = Intent(this@MainActivity, SubjectsList::class.java)
            intent.putExtra("key", "students")
            startActivity(intent)
        }

        binding.appReset.setOnClickListener {
            if (db.schoolDao().readAllMarks().toString().isNotEmpty()) {
                db.schoolDao().deleteAllMarks()
                finish()
            }
            if (db.schoolDao().readAllStudents().toString().isNotEmpty()) {
                db.schoolDao().deleteAllStudents()
                finish()
            }
            if (db.schoolDao().readAllSubjects().toString().isNotEmpty()) {
                db.schoolDao().deleteAllSubjects()
                finish()
            }
            Toast.makeText(this, "Wszystkie dane zostały usunięte!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        }
    }
}