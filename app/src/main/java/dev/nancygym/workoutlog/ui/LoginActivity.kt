package dev.nancygym.workoutlog.ui


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.Models.LoginRequest
import dev.nancygym.workoutlog.Repository.UserRepository
import dev.nancygym.workoutlog.ViewModel.UserViewModel
import dev.nancygym.workoutlog.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel:UserViewModel by ViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse ->
            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            saveLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext, HomeActivity::class.java))
        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    fun castViews() {
        binding.tvSignUp.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            validatelogin()
        }
        binding.btnLogin.setOnClickListener {
            validatelogin()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun validatelogin() {
        var error = false
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        var email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }
        var password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error) {
            var loginRequest = LoginRequest(email, password)
            binding.pbLogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)


        }
    }

    fun saveLoginDetails(loginResponse: LoginResponse) {
        var editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }
}






