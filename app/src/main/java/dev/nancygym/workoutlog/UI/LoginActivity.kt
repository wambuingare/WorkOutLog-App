package dev.nancygym.workoutlog.UI


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.models.LoginRequest
import dev.nancygym.workoutlog.viewmodel.UserViewModel
import dev.nancygym.workoutlog.databinding.ActivityLoginBinding
import dev.nancygym.workoutlog.models.LoginResponse
import java.util.Objects


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, { LoginResponse ->
            Toast.makeText(baseContext, LoginResponse?.message, Toast.LENGTH_LONG).show()
            saveLoginDetails(LoginResponse!!)
            startActivity(Intent(baseContext, HomeActivity::class.java))
        })

        userViewModel.errorLiveData.observe(this, { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
        })
    }

    fun castViews() {
        binding.btnLogin.setOnClickListener {
            validatelogin()

        }
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun validatelogin() {
        var error = false
        binding.tilPassword1.error = null
        binding.tilEmailaddress.error = null
        var email = binding.etEmailaddress.text.toString()

        if (email.isBlank()) {
            binding.tilEmailaddress.error = "Enter email"
            error = true

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmailaddress.error = "Not a valid email address"
            error = true
        }

        var password = binding.etPassword1.text.toString()
        if (password.isBlank()) {
            binding.tilPassword1.error = "Enter password"
            error = true

        }
        if (!error) {
            val loginRequest = LoginRequest(email, password)
            binding.pbLogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)

        }
    }

    fun saveLoginDetails(LoginResponse: LoginResponse) {
        var editor = sharedPrefs.edit()
        val token = "Bearer ${LoginResponse.accesstoken}"
        editor.putString(Constants.ACCESS_TOKEN, token)
        editor.putString(Constants.USER_ID, LoginResponse.userId)
        editor.putString(Constants.PROFILE_ID, LoginResponse.profileId)
        editor.apply()

    }
}




















