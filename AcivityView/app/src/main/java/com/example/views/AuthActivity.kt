package com.example.views

import android.content.Intent
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

    private lateinit var etName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        etName = findViewById(R.id.etName)
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        val spinnerCity = findViewById<Spinner>(R.id.spinnerCity)
        val rgUserType = findViewById<RadioGroup>(R.id.rgUserType)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Spinner adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.cities_array))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapter

        // Focus change listeners
        etName.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) validateName() }
        etUsername.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) validateUsername() }
        etEmail.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) validateEmail() }
        etPassword.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) validatePassword() }
        etConfirmPassword.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) validateConfirmPassword() }

        btnLogin.setOnClickListener {
            val usernameOk = validateUsername()
            val passwordOk = validatePassword()
            if (usernameOk && passwordOk) {
                val city = spinnerCity.selectedItem.toString()
                val userType = findViewById<RadioButton>(rgUserType.checkedRadioButtonId)?.text ?: "None"
                toast("Login successful as $userType from $city")
            }
        }

        btnRegister.setOnClickListener {
            val allValid = validateName() and validateUsername() and validateEmail() and
                           validatePassword() and validateConfirmPassword()
            if (!allValid) return@setOnClickListener

            if (!cbTerms.isChecked) {
                toast("Please accept terms and conditions")
                return@setOnClickListener
            }
            val firstName = etName.text.toString().trim().split(" ")[0]
            startActivity(Intent(this, WelcomeActivity::class.java).putExtra("FIRST_NAME", firstName))
        }
    }

    private fun validateName(): Boolean {
        val name = etName.text.toString().trim()
        return if (name.length < 3) {
            etName.error = "Name must be at least 3 characters"
            false
        } else {
            etName.error = null
            true
        }
    }

    private fun validateUsername(): Boolean {
        val username = etUsername.text.toString().trim()
        return if (username.isEmpty()) {
            etUsername.error = "Username is required"
            false
        } else {
            etUsername.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = etEmail.text.toString().trim()
        return when {
            email.isEmpty() -> {
                etEmail.error = "Email is required"
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                etEmail.error = "Enter a valid email"
                false
            }
            else -> {
                etEmail.error = null
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        val password = etPassword.text.toString()
        return when {
            password.isEmpty() -> {
                etPassword.error = "Password is required"
                false
            }
            password.length < 6 -> {
                etPassword.error = "Password must be at least 6 characters"
                false
            }
            else -> {
                etPassword.error = null
                true
            }
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val password = etPassword.text.toString()
        val confirm = etConfirmPassword.text.toString()
        return when {
            confirm.isEmpty() -> {
                etConfirmPassword.error = "Please confirm your password"
                false
            }
            confirm != password -> {
                etConfirmPassword.error = "Passwords do not match"
                false
            }
            else -> {
                etConfirmPassword.error = null
                true
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
