package com.example.week4tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnStartQuiz = findViewById<Button>(R.id.btnStartQuiz)

        btnStartQuiz.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val input = EditText(this).apply {
            hint = "Enter your name"
            setPadding(48, 24, 48, 24)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Quiz")
        builder.setMessage("Are you sure you want to start the quiz now?")
        builder.setView(input)

        builder.setPositiveButton("Yes", null)

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val username = input.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show()
            } else {
                dialog.dismiss()
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            }
        }
    }
}