package com.example.asystent.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = false)
    val number: String,
    val name: String,
    val surname: String,
    )