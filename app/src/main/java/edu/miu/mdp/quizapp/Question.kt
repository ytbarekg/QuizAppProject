package edu.miu.mdp.quizapp

import androidx.room.Entity

@Entity
data class Question(val id: Int,
                    val question: String,
                    val answer: Char,
                    val choiceA: String,
                    val choiceB: String,
                    val choiceC: String,
                    val choiceD: String ) {
}