package com.example.asystent.data.dao

import androidx.room.*
import com.example.asystent.data.entity.*

@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addSubject(subject: Subject)

    @Update
    fun updateSubject(subject: Subject)

    @Query("SELECT * FROM subject_table ORDER BY id ASC")
    fun readAllSubjects(): MutableList<Subject>

    @Query("SELECT * FROM subject_table WHERE id LIKE :id")
    fun getSubject(id: Int): Subject

    @Query("DELETE FROM subject_table")
    fun deleteAllSubjects()


    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER BY number ASC")
    fun readAllStudents(): MutableList<Student>

    @Query("SELECT * FROM student_table WHERE number LIKE :number")
    fun getStudent(number: String): Student

    @Query("DELETE FROM student_table")
    fun deleteAllStudents()


    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addMark(mark: Mark)

    @Update
    fun updateMarks(mark: Mark)

    @Query("SELECT * FROM mark_table ORDER BY id ASC")
    fun readAllMarks(): MutableList<Mark>

    @Query("SELECT * FROM mark_table WHERE id LIKE :id")
    fun getMark(id: Int): Mark

    @Query("SELECT * FROM student_table WHERE number LIKE :number")
    fun getStudentWithMarks(number: String): MutableList<StudentWithMarks>

    @Query("DELETE FROM mark_table")
    fun deleteAllMarks()


    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Query("SELECT * FROM subject_table WHERE id LIKE :id")
    fun getStudentsOfSubject(id: Int): MutableList<SubjectWithStudents>

    @Query("SELECT * FROM student_table WHERE number LIKE :number")
    fun getSubjectsOfStudent(number: String): MutableList<StudentWithSubjects>
}