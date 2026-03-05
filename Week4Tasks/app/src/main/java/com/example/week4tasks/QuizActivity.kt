package com.example.week4tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val username = intent.getStringExtra("USERNAME") ?: "Player"
        findViewById<TextView>(R.id.tvQuizTitle).text = "Welcome, $username!"

        val rg1 = findViewById<RadioGroup>(R.id.rgQuestion1)
        val rg2 = findViewById<RadioGroup>(R.id.rgQuestion2)
        val rg3 = findViewById<RadioGroup>(R.id.rgQuestion3)
        val rg4 = findViewById<RadioGroup>(R.id.rgQuestion4)
        val rg5 = findViewById<RadioGroup>(R.id.rgQuestion5)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            // Validation: Check if all questions are answered
            val unansweredGroup = when {
                rg1.checkedRadioButtonId == -1 -> rg1
                rg2.checkedRadioButtonId == -1 -> rg2
                rg3.checkedRadioButtonId == -1 -> rg3
                rg4.checkedRadioButtonId == -1 -> rg4
                rg5.checkedRadioButtonId == -1 -> rg5
                else -> null
            }

            if (unansweredGroup != null) {
                Toast.makeText(this, "Please answer all questions before submitting", Toast.LENGTH_SHORT).show()
                unansweredGroup.isFocusableInTouchMode = true
                unansweredGroup.requestFocus()
                return@setOnClickListener
            }

            // Show Confirmation Dialog before submitting
            showSubmitConfirmationDialog(rg1, rg2, rg3, rg4, rg5)
        }
    }

    private fun showSubmitConfirmationDialog(rg1: RadioGroup, rg2: RadioGroup, rg3: RadioGroup, rg4: RadioGroup, rg5: RadioGroup) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Submit Quiz")
        builder.setMessage("Are you sure you want to submit your answers?")

        builder.setPositiveButton("Yes") { _, _ ->
            var score = 0

            // Check Question 1 (Paris - rb1_3)
            if (rg1.checkedRadioButtonId == R.id.rb1_3) score++

            // Check Question 2 (Mars - rb2_2)
            if (rg2.checkedRadioButtonId == R.id.rb2_2) score++

            // Check Question 3 (Pacific Ocean - rb3_4)
            if (rg3.checkedRadioButtonId == R.id.rb3_4) score++

            // Check Question 4 (William Shakespeare - rb4_2)
            if (rg4.checkedRadioButtonId == R.id.rb4_2) score++

            // Check Question 5 (Au - rb5_3)
            if (rg5.checkedRadioButtonId == R.id.rb5_3) score++

            // Pass data to ResultActivity
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish() // Optional: Finish QuizActivity so user can't go back to it from results
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}