package vcmsa.ci.carhistoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val markTextView = findViewById<TextView>(R.id.markTextView)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        //Fetching mark from previous activity
        val score = intent.getIntExtra("score", 0)

        //Getting the mark
        markTextView.text = "You scored $score out of 5!!!"

        //Restart Quiz
        resetButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        //Exit application button
        exitButton.setOnClickListener {
            finishAffinity() //Closing all activities
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}