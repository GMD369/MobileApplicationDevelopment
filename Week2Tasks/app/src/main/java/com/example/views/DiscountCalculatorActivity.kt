package com.example.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DiscountCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount_calculator)

        val priceInput = findViewById<EditText>(R.id.priceInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val originalPriceText = findViewById<TextView>(R.id.originalPriceText)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val input = priceInput.text.toString()
            if (input.isNotEmpty()) {
                val price = input.toDouble()
                val discount = price * 0.20
                val finalPrice = price - discount
                originalPriceText.text = "Original Price: $price"
                resultText.text = "Discounted Price: $finalPrice"
            } else {
                originalPriceText.text = ""
                resultText.text = "Please enter a price"
            }
        }
    }
}