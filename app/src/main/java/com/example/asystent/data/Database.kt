package com.example.asystent.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystent.data.dao.SchoolDao
import com.example.asystent.data.entity.Mark
import com.example.asystent.data.entity.Student
import com.example.asystent.data.entity.StudentSubjectCrossRef
import com.example.asystent.data.entity.Subject

@Database(entities = [
    Subject::class,
    Student::class,
    Mark::class,
    StudentSubjectCrossRef::class
], version = 2)
abstract class SchoolDatabase: RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {
            synchronized(this) {
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}