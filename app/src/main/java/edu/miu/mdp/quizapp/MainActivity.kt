package edu.miu.mdp.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var questions: List<Question>
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java )
        next.setOnClickListener {
            if(questions.size - 1 > questionViewModel.currentQuestionNo.value!!)
                questionViewModel.currentQuestionNo.value = questionViewModel.currentQuestionNo.value!!.plus(1);
        }

        previous.setOnClickListener{
            if(questionViewModel.currentQuestionNo.value!! > 0)
                questionViewModel.currentQuestionNo.value = questionViewModel.currentQuestionNo.value!!.plus(-1);
        }
        questionViewModel.currentQuestionNo.observe(this) {
            showQuestion(questionViewModel.currentQuestionNo.value!!)
        }
        launch {
            applicationContext.let {
                questions = QuestionDatabase(it).getQuestionDao().get();

                val q1 = Question(questions.size + 1, " You can create an emulator to simulate the configuration of a particular type of Android device using a tool like ___.",
                    'A',
                    "Theme Editor\n",
                    "Android SDK Manager\n",
                    "AVD Manager\n",
                    "Virtual Editor\n")
                val q2 = Question(questions.size + 2, " What parameter specifies the Android API level that Gradle should use to compile your app?\n",
                    'B',
                    "minSdkVersion\n",
                    "compileSdkVersion\n",
                    "targetSdkVersion\n",
                    "testSdkVersion\n")
                val q3 = Question(questions.size + 3, " What phrase means that the compiler validates types while compiling?\n",
                    'A',
                    "Type safety\n",
                    "Data binding\n",
                    "Type validation\n",
                    "Wrong text\n")

                val q4 = Question(questions.size + 4, " Which of the following is not true about fragments?\n",
                    'A',
                    "A fragment has its own lifecycle and receives its own input events.\n",
                    "It is not possible to remove a fragment while the activity is running.\n",
                    "A fragment is defined in a Kotlin class.\n",
                    "A fragment's UI is defined in an XML layout file.\n")

//                QuestionDatabase(it).getQuestionDao().addQuestion(q1)
//                QuestionDatabase(it).getQuestionDao().addQuestion(q2)
//                QuestionDatabase(it).getQuestionDao().addQuestion(q3)
//                QuestionDatabase(it).getQuestionDao().addQuestion(q4)


                questions = QuestionDatabase(it).getQuestionDao().get();
                questionViewModel.currentQuestionNo.value = questionViewModel.currentQuestionNo.value!!.plus(1)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Cancel the Job
        job.cancel()
    }

    fun showQuestion(index: Int) {
        if(index < 0 || index >= questions.size) {
            return;
        }
        questionNumber.text = (index + 1).toString() + ". ";
        var q = questions.get(index)
        questionView.text = q.question.toString();
        choiceAView.text = q.choiceA;
        choiceBView.text = q.choiceB;
        choiceCView.text = q.choiceC;
        choiceDView.text = q.choiceD;
    }

}