package com.example.views

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val firstName = intent.getStringExtra("FIRST_NAME") ?: "User"
        findViewById<TextView>(R.id.tvWelcome).text = "Welcome, $firstName!"
    }
}
