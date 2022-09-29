package dev.nancygym.workoutlog.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.Models.LoginRequest
import dev.nancygym.workoutlog.Models.RegisterRequest
import dev.nancygym.workoutlog.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()

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
        }



