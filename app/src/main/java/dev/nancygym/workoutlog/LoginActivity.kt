package dev.nancygym.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var tilEmail: TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var tilpassword: TextInputLayout
    lateinit var etpassword: TextInputEditText
    lateinit var tvsignup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin = findViewById(R.id.btnLogin)
        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail)
        tilpassword = findViewById(R.id.tilPassword)
        etpassword = findViewById(R.id.etPassword)
        tvsignup = findViewById(R.id.tvSignUp)

        tvsignup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)

            validatelogin()

        }
    }

        fun validatelogin() {
            var error = false
            tilEmail.error = null
            tilpassword.error = null
            var email = etEmail.text.toString()
            if (email.isBlank()) {
                tilEmail.error = "Email is required"
                error = true
            }
            var password = etpassword.text.toString()
            if (password.isBlank()) {
                tilpassword.error = "Password is required"
                error = true
            }
            if (error != true) {
            }
        }
    }

