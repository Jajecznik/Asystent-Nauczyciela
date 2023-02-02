package com.example.asystent.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithMarks(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "number",
        entityColumn = "number"
    )
    val marks: MutableList<Mark>
)
