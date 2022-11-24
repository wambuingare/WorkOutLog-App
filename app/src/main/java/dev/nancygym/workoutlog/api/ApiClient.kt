package dev.nancygym.workoutlog.api

object ApiClient {
    val client = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(WorkoutLog.appContext))
        .build()
    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create()).client(client)
        .build()

    fun <T> buildApiClient(apiInterface: Class<T>): T {
        return retrofit.create(apiInterface)
    }
}
