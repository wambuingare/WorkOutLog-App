package dev.nancygym.workoutlog.Repository

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val exerciseCategoryDao = database.exerciseCategoryDao()
    val exerciseDao = database.exerciseDao()


    suspend fun  fetchExerciseCategories(accessToken: String):Response<List<ExerciseCategory>>{
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExerciseCategories(accessToken)
            if (response.isSuccessful){
                var categories = response.body()
                categories?.forEach{ category->
                    exerciseCategoryDao.insertExerciseCategory(category)
                }
            }
            response
        }
    }

    suspend fun fetchExercises(accessToken: String):Response<List<Exercises>> {
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful){
                var exercises = response.body()
                exercises?.forEach{ exercise->
                    exerciseDao.insertExercise(exercise)
                }
            }
            response
        }
    }

    fun getDbExercises(): LiveData<List<Exercises>> {
        return exerciseDao.getExercises()
    }
    fun getDbCategories (): LiveData<List<ExerciseCategory>> {
        return  exerciseCategoryDao.getExerciseCategories()
    }

    fun getExerciseByCategoryId(categoryId: String): LiveData<List<Exercises>> {
        return exerciseDao.getExercisesByCategory(categoryId)

    }
}








}