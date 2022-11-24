package dev.nancygym.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.models.LoginRequest
import dev.nancygym.workoutlog.models.RegisterRequest
import dev.nancygym.workoutlog.models.RegisterResponse
import dev.nancygym.workoutlog.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()
    val registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()

     fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
               loginResponseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepository.makeUserRequest(registerRequest)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            } else{
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }

    }
}



