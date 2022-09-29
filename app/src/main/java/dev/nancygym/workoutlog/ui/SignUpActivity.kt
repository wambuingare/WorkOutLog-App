package dev.nancygym.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel

import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.Models.RegisterRequest
import dev.nancygym.workoutlog.ViewModel.UserViewModel
import dev.nancygym.workoutlog.api.ApiClient
import dev.nancygym.workoutlog.api.ApiInterface
import dev.nancygym.workoutlog.databinding.ActivitySignUpBinding
import retrofit2.Callback


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel:UserViewModel by ViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponsiveLiveData.observe(
            this,
            {registerResponse->
                Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,LoginActivity::class.java))
            })
        userViewModel.registerErrorLiveData.observe(
            this,
            {registerError ->
                Toast.makeText(baseContext,registerError,Toast.LENGTH_LONG).show()
            })
    }
    fun castViews() {
        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun validatesignup() {
        var firstname = binding.etFirstname.text.toString()
        var lastname = binding.etlastName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmpassword = binding.etConfirmpassword.text.toString()
        var error= false

        if (firstname.isBlank()) {
            binding.tilfirstname.error = "Firstname is required"
        }
        if (lastname.isBlank()) {
            binding.tilLastname.error = "Lastname is required"
        }
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (confirmpassword.isBlank()) {
            binding.tilConfirmpassword.error = "Confirm your password"
        }
        if (password != confirmpassword) {
            binding.tilConfirmpassword.error = "Wrong password"
        }
        if (!error) {
            var registerRequest = RegisterRequest(firstname, lastname, email, password)
            userViewModel.registerUser(registerRequest)
        }
    }
    fun makeRegisterRequest(registerRequest: RegisterRequest) {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)
    }
}

