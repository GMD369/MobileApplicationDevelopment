package com.example.week4tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val username = intent.getStringExtra("USERNAME") ?: "Player"

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvWellDone = findViewById<TextView>(R.id.tvWellDone)
        val btnAgain = findViewById<Button>(R.id.btnAgain)

        tvScore.text = "$score/5"

        tvWellDone.text = when (score) {
            5    -> "Perfect Score!"
            4    -> "Great Job!"
            3    -> "Good Effort!"
            2    -> "Keep Practicing!"
            1    -> "Better Luck Next Time!"
            else -> "Don't Give Up!"
        }

        btnAgain.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("USERNAME", username)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
