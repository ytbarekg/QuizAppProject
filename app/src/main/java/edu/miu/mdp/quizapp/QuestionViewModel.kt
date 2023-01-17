package edu.miu.mdp.quizapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel: ViewModel() {
    var currentQuestionNo = MutableLiveData<Int>()
    init {
        currentQuestionNo.value = -1;
    }

}