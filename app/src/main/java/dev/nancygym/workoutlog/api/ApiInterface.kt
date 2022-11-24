package dev.nancygym.workoutlog.api

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    //    exercise categories
    @GET ("/exercise-categories")
    suspend fun fetchExerciseCategories(@Header("Authorization")accessToken: String): Response<List<ExerciseCategory>>

    @GET("/exercises")
    suspend fun fetchExercises(@Header("Authorization")accessToken:String): Response<List<Exercises>>
}
