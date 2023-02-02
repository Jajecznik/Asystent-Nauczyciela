package com.example.asystent.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "id",
        entityColumn = "number",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: MutableList<Student>
)
