package dev.nancygym.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var tilfirstname: TextInputLayout
    lateinit var etFirstname: TextInputEditText
    lateinit var tilLastname: TextInputLayout
    lateinit var etLastName: TextInputEditText
    lateinit var tilEmail: TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var tilPassword: TextInputLayout
    lateinit var etPassword: TextInputEditText
    lateinit var tilConfirmpassword: TextInputLayout
    lateinit var etConfirmpassword: TextInputEditText
    lateinit var tvlogin:TextView
    lateinit var btnsignup:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        
        tvlogin = findViewById(R.id.tvlogin)
        tilfirstname = findViewById(R.id.tilfirstname)
        etFirstname = findViewById(R.id.etFirstname)
        tilLastname = findViewById(R.id.tilLastname)
        etLastName = findViewById(R.id.etLastName)
        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etPassword = findViewById(R.id.etPassword)
        tilConfirmpassword = findViewById(R.id.tilConfirmpassword)
        etConfirmpassword = findViewById(R.id.etConfirmpassword)
        btnsignup = findViewById(R.id.btnsignup)

        tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnsignup.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
            validatesignup()
        }

    }
    fun validatesignup(){
        var firstname = etFirstname.text.toString()
        var LastName = etLastName.text.toString()
        var Email = etEmail.text.toString()
        var password = etPassword.text.toString()
        var Confirmpassword = etConfirmpassword.text.toString()

        if (firstname.isBlank()) {
            tilfirstname.error = "Firstname is required"
        }
        if (LastName.isBlank()) {
            tilLastname.error = "Lastname is required"
        }
        if (Email.isBlank()) {
            tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            tilPassword.error = "Password is required"
        }
        if (Confirmpassword.isBlank()) {
            tilConfirmpassword.error = "Confirm your password"
        }
    }
}

