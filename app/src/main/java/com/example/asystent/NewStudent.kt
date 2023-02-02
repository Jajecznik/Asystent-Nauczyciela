package com.example.asystent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.databinding.ActivityNewStudentBinding
import com.google.android.material.snackbar.Snackbar

class NewStudent : AppCompatActivity(){

    lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)

        val newName = binding.newStudentNameET.text
        val newSurname = binding.newStudentSurnameET.text
        val newNumber = binding.newStudentNumberET.text

        binding.newStudentNextBT.setOnClickListener {
            val list = db.schoolDao().readAllStudents()
            var z = false
            for (i in list) {
                if (i.number == newNumber.toString()) {
                    z = true
                }
            }

            if (newName.isNotEmpty() && newSurname.isNotEmpty() && newNumber.isNotEmpty() && newNumber.length == 6) {
                if (z) {
                    Snackbar.make(it, "Podany numer albumu jest w użyciu!", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(this@NewStudent, NewStudentGroup::class.java)
                    intent.putExtra("name", newName.toString())
                    intent.putExtra("surname", newSurname.toString())
                    intent.putExtra("number", newNumber.toString())
                    startActivity(intent)
                }
            }
            else {
                Snackbar.make(it, "Wypełnij wszystkie pola!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}