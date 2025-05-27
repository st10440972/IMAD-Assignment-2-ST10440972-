package vcmsa.ci.carhistoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {

    //Setting up questions in an array
    private val questions = arrayOf(
        "The first ever car was invented in 1886.",
        "Lamborghini is originally a tractor manufacturer.",
        "Tesla does not have self-driving feature.",
        "Kunimitsu Takahashi invented drifting in 1970.",
        "The Ford Mustang was created for speed racing."
    )

    //Setting up the answers for the questions above in a array
    private val answers = arrayOf(
        true,
        true,
        false,
        true,
        false
    )

    //Declaring constant for questions and score
    private var currentQuestionIndex = 0
    private var score = 0

    //Declaring values that will be used later in the code
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var reviewButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)


        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        reviewButton = findViewById(R.id.reviewButton)

        showQuestion()

        //Setting up the true and false buttons
        trueButton.setOnClickListener {
            checkAnswer(true)

        }

        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        //Setting up Button to page to the next activity for marks
        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("score", score)
            startActivity(intent)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
        //Showing the questions and allowing users to go through questions without a next button
        fun showQuestion() {
            if (currentQuestionIndex < answers.size) {
                questionTextView.text = questions[currentQuestionIndex]
            } else {
                trueButton.isEnabled = false
                falseButton.isEnabled = false
            }
        }

        //Checking the user's answers whether it is correct or not
        fun checkAnswer(userAnswer: Boolean) {
            if (currentQuestionIndex < answers.size) {
                if (userAnswer == answers[currentQuestionIndex]) {
                    score++

                }
                currentQuestionIndex++
                showQuestion()
            }
        }
    }
