package com.example.week3tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val tilAge = findViewById<TextInputLayout>(R.id.tilAge)
        val etAge = findViewById<TextInputEditText>(R.id.etAge)
        val tilHeight = findViewById<TextInputLayout>(R.id.tilHeight)
        val etHeight = findViewById<TextInputEditText>(R.id.etHeight)
        val tilWeight = findViewById<TextInputLayout>(R.id.tilWeight)
        val etWeight = findViewById<TextInputEditText>(R.id.etWeight)
        val btnShow = findViewById<Button>(R.id.btnShow)

        etName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateName(etName, tilName)
            }
        }

        etAge.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateNumber(etAge, tilAge, "Age")
            }
        }

        etHeight.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateNumber(etHeight, tilHeight, "Height")
            }
        }

        etWeight.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateNumber(etWeight, tilWeight, "Weight")
            }
        }

        btnShow.setOnClickListener {
            val isNameValid = validateName(etName, tilName)
            val isAgeValid = validateNumber(etAge, tilAge, "Age")
            val isHeightValid = validateNumber(etHeight, tilHeight, "Height")
            val isWeightValid = validateNumber(etWeight, tilWeight, "Weight")

            if (isNameValid && isAgeValid && isHeightValid && isWeightValid) {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("EXTRA_NAME", etName.text.toString().trim())
                    putExtra("EXTRA_AGE", etAge.text.toString().trim())
                    putExtra("EXTRA_HEIGHT", etHeight.text.toString().trim().toDouble())
                    putExtra("EXTRA_WEIGHT", etWeight.text.toString().trim().toDouble())
                }
                startActivity(intent)
            }
        }
    }

    private fun validateName(et: TextInputEditText, til: TextInputLayout): Boolean {
        val name = et.text.toString().trim()
        return if (name.length < 3) {
            til.error = "Name must be at least 3 characters"
            false
        } else {
            til.error = null
            true
        }
    }

    private fun validateNumber(et: TextInputEditText, til: TextInputLayout, fieldName: String): Boolean {
        val value = et.text.toString().trim()
        return if (value.isEmpty()) {
            til.error = "$fieldName is required"
            false
        } else if (value.toDoubleOrNull() == null || value.toDouble() <= 0) {
            til.error = "Enter a valid $fieldName"
            false
        } else {
            til.error = null
            true
        }
    }
}
