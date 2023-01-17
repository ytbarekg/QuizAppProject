package edu.miu.mdp.quizapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface QuestionDao {
    @Query("Select * from Question")
    suspend fun get():List<Question>

    @Insert
    suspend fun addQuestion(que: Question)

    @Update
    suspend fun updateQuestion(que: Question)

    @Delete
    suspend fun deleteQuestion(que: Question)
}