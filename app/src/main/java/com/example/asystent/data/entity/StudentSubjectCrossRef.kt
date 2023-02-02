package com.example.asystent.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["number", "id"])
data class StudentSubjectCrossRef (
    val number: String,
    val id: Int
    )