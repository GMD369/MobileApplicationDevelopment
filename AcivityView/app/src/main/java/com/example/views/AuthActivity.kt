package com.example.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val spinnerCity = findViewById<Spinner>(R.id.spinnerCity)
        val rgUserType = findViewById<RadioGroup>(R.id.rgUserType)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Set up Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = adapter
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val city = spinnerCity.selectedItem.toString()
            val selectedId = rgUserType.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedId)
            val userType = radioButton?.text ?: "None"

            if (username.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Login successful as $userType from $city", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            val city = spinnerCity.selectedItem.toString()
            if (cbTerms.isChecked) {
                Toast.makeText(this, "Registration successful for $city", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
