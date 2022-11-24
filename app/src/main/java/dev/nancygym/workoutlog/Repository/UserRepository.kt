package dev.nancygym.workoutlog.Repository

import dev.nancygym.workoutlog.LoginResponse
import dev.nancygym.workoutlog.models.LoginRequest
import dev.nancygym.workoutlog.api.ApiClient
import dev.nancygym.workoutlog.api.ApiInterface
import dev.nancygym.workoutlog.models.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest):Response<LoginResponse>
    = withContext(Dispatchers.IO) {
        val response = apiClient.loginUser(loginRequest)
            return@withContext response
        }
    suspend fun makeUserRequest(registerRequest: RegisterRequest)
            = withContext(Dispatchers.Id){
        val response=apiClient.registerUser(registerRequest)
        return@withContext response
    }
}


