package dev.nancygym.workoutlog.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import dev.nancygym.workoutlog.models.RegisterRequest
import dev.nancygym.workoutlog.viewmodel.UserViewModel
import dev.nancygym.workoutlog.api.ApiClient
import dev.nancygym.workoutlog.api.ApiInterface
import dev.nancygym.workoutlog.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(
            this,
            { registerResponse ->
                Toast.makeText(baseContext, registerResponse.message, Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext, LoginActivity::class.java))
            })
        userViewModel.registerErrorLiveData.observe(
            this,
            { registerError ->
                Toast.makeText(baseContext, registerError, Toast.LENGTH_LONG).show()
            })
    }

    fun castViews() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
    }

    fun validateSignup() {

        binding.tilName1.error = null
        binding.tilName2.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilphoneNumber.error = null
        binding.tilConfirmPassword.error = null
        var error = false
        var firstname = binding.etName1.text.toString()
        if (firstname.isBlank()) {
            binding.tilName1.error = "First name is required"
            error = true
        }
        var secondname = binding.etName2.text.toString()
        if (secondname.isBlank()) {
            binding.tilName2.error = "Last name is required"
            error = true
        }
        var email2 = binding.etEmail2.text.toString()
        if (email2.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true

        }
        var number = binding.etphoneNumber.text.toString()
        if (number.isBlank()) {
            binding.tilphoneNumber.error = "Email is required"
            error = true

        }
        var password2 = binding.etPassword2.text.toString()
        if (password2.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true

        }
        var confirm = binding.etConfirmPassword.text.toString()
        if (confirm.isBlank()) {
            binding.tilConfirmPassword.error = "Confirmation is required"
            error = true

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()) {
            binding.tilEmail.error = "Not a valid email address"
            error = true
        }
        if (password2 != confirm) {
            binding.tilConfirmPassword.error = "password does not match"

        }
        if (!error) {
            val registerRequest = RegisterRequest(firstname, secondname, number, email2, password2)
            userViewModel.registerUser(registerRequest)
        }
    }
}




