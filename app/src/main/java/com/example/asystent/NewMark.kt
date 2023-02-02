package com.example.asystent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.data.entity.Mark
import com.example.asystent.databinding.ActivityNewMarkBinding
import com.example.asystent.model.Data
import com.google.android.material.snackbar.Snackbar

class NewMark : AppCompatActivity() {

    private lateinit var sub: String
    private lateinit var z: String
    lateinit var binding: ActivityNewMarkBinding
    private lateinit var markEntity: Mark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)
        val key = intent.extras?.getString("bundle_student_id").toString()
        z = key
        val subj = intent.extras?.getString("subject").toString()
        sub = subj

        val marks = Data.marks
        var mark = "2"
        var points = 0

        binding.markMarkNP.minValue = 0
        binding.markMarkNP.maxValue = 5
        binding.markMarkNP.displayedValues = marks
        binding.markMarkNP.setOnValueChangedListener {
                picker, oldVal, newVal -> mark = marks[newVal]
        }

        binding.percentMarkNP.minValue = 0
        binding.percentMarkNP.maxValue = 100
        binding.percentMarkNP.setOnValueChangedListener { picker, oldVal, newVal -> points = newVal }

        val name = binding.nameMarkET.text
        binding.newMarkBtn.setOnClickListener {
            if (name.isNotEmpty()) {
                markEntity = Mark(0, name.toString(), mark, points.toString(), sub, z)
                db.schoolDao().addMark(markEntity)

                val intent = Intent(this@NewMark, MainActivity::class.java)
                Toast.makeText(this, "Dodano nowe zajęcia", Toast.LENGTH_SHORT).show()
                startActivity(intent)

            }
            else {
                Snackbar.make(it, "Wypełnij wszystkie pola!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}