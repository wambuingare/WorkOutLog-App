package dev.nancygym.workoutlog.api

import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.Models.LoginRequest
import dev.nancygym.workoutlog.Models.RegisterRequest
import dev.nancygym.workoutlog.Models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/user/registration")
     fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    
    @POST("/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}