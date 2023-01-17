package edu.miu.mdp.quizapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(@PrimaryKey val id: Int,
                    val question: String,
                    val answer: Char,
                    val choiceA: String,
                    val choiceB: String,
                    val choiceC: String,
                    val choiceD: String ) {
}