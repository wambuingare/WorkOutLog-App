package dev.nancygym.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.nancygym.workoutlog.databinding.ActivityLoginBinding
import dev.nancygym.workoutlog.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
    }

    fun castViews(){
        binding.tvlogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
            }

    fun validatesignup(){
      var firstname = binding.etFirstname.text.toString()
       var LastName = binding.etlastName.text.toString()
       var Email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
       var Confirmpassword = binding.etConfirmpassword.text.toString()

        if (firstname.isBlank()) {
            binding.tilfirstname.error = "Firstname is required"
        }
        if (LastName.isBlank()) {
            binding.tilLastname.error = "Lastname is required"
        }
        if (Email.isBlank()) {
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (Confirmpassword.isBlank()) {
            binding.tilConfirmpassword.error = "Confirm your password"
        }
        if (password != Confirmpassword) {
           binding. tilConfirmpassword.error = "Wrong password"
        }
    }
}

