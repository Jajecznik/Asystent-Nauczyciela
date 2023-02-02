package com.example.asystent.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "number",
        entityColumn = "id",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: MutableList<Subject>
)
