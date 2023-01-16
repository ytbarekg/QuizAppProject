package edu.miu.mdp.quizapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface QuestionDao {
    @Query("Select * from Question")
    fun getAll():List<Question>

    @Query("Select * from Question where id=:id")
    fun getById(id: Int):Question

    @Insert
    fun addQuestion(que: Question)

    @Update
    fun updateQuestion(que: Question)

    @Delete
    fun deleteQuestion(que: Question)
}