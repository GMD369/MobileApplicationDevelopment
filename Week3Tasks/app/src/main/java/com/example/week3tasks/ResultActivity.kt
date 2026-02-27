package com.example.week3tasks

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvAge = findViewById<TextView>(R.id.tvAge)
        val tvHeightWeight = findViewById<TextView>(R.id.tvHeightWeight)
        val tvBmiScore = findViewById<TextView>(R.id.tvBmiScore)
        val tvBmiCategory = findViewById<TextView>(R.id.tvBmiCategory)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val age = intent.getStringExtra("EXTRA_AGE") ?: ""
        val heightCm = intent.getDoubleExtra("EXTRA_HEIGHT", 0.0)
        val weightKg = intent.getDoubleExtra("EXTRA_WEIGHT", 0.0)

        // Calculate BMI
        val heightM = heightCm / 100
        val bmi = weightKg / heightM.pow(2)

        // Set User Details
        tvName.text = "Name: $name"
        tvAge.text = "Age: $age"
        tvHeightWeight.text = "Height: ${heightCm}cm | Weight: ${weightKg}kg"

        // Set BMI Score
        tvBmiScore.text = String.format("%.1f", bmi)

        // Determine Category and Color
        val (category, color) = when {
            bmi < 18.5 -> "Underweight" to Color.BLUE
            bmi < 25.0 -> "Normal" to Color.parseColor("#2E7D32") // Green
            else -> "Overweight" to Color.RED
        }

        tvBmiCategory.text = category
        tvBmiCategory.setTextColor(color)

        btnBack.setOnClickListener {
            finish()
        }
    }
}
