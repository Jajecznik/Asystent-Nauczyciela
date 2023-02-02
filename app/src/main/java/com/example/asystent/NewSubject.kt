package com.example.asystent

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.asystent.data.SchoolDatabase
import com.example.asystent.data.entity.Subject
import com.example.asystent.databinding.ActivityNewSubjectBinding
import com.example.asystent.model.Data
import com.google.android.material.snackbar.Snackbar
import java.util.*

class NewSubject : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityNewSubjectBinding
    private lateinit var subjectEntity: Subject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = SchoolDatabase.getInstance(this)

        var timeStart = ""
        var timeEnd = ""
        val newSubjectBtn = binding.newSubjectBtn
        val newSubjectName = binding.newSubjectNameET
        val newSubjectDaySp = binding.newSubjectDaySp
        val newSubjectStartBtn = binding.newSubjectStartBtn
        val newSubjectEndBtn = binding.newSubjectEndBtn

        // spinner adapter
        val daysAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Data.days)
        newSubjectDaySp.adapter = daysAdapter

        newSubjectStartBtn.setOnClickListener {
            val time = Calendar.getInstance()
            val startHour = time.get(Calendar.HOUR_OF_DAY)
            val startMinute = time.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                newSubjectStartBtn.text = ""

                timeStart = properFormat(hourOfDay, minute)
                newSubjectStartBtn.text = timeStart
            }, startHour, startMinute, true).show()
        }

        newSubjectEndBtn.setOnClickListener {
            val time = Calendar.getInstance()
            val startHour = time.get(Calendar.HOUR_OF_DAY)
            val startMinute = time.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                newSubjectEndBtn.text = ""

                timeEnd = properFormat(hourOfDay, minute)
                newSubjectEndBtn.text = timeEnd
            }, startHour, startMinute, true).show()
        }

        newSubjectBtn.setOnClickListener {
            if (newSubjectName.text.isNotEmpty() && timeStart.isNotEmpty() && timeEnd.isNotEmpty()) {
                subjectEntity = Subject(0, newSubjectName.text.toString(), newSubjectDaySp.selectedItem.toString(), "$timeStart - $timeEnd")
                db.schoolDao().addSubject(subjectEntity)
                finish()

                val intent = Intent(this@NewSubject, MainActivity::class.java)
                newSubjectName.text.clear()
                Toast.makeText(this, "Dodano nowe zajęcia", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            else {
                Snackbar.make(it, "Wypełnij wszystkie pola!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun properFormat(hourOfDay: Int, minute: Int): String {
        val h: String = if (hourOfDay < 10)
            "0$hourOfDay"
        else
            "$hourOfDay"

        val m: String = if (minute < 10)
            "0$minute"
        else
            "$minute"

        return "$h:$m"
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // selecting an item
        val item = parent!!.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}