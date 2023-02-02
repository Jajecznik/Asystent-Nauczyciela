package com.example.asystent.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mark_table")
data class Mark(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val mark: String,
    val points: String,
    val group: String,
    val number: String
)
