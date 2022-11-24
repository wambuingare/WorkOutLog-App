package dev.nancygym.workoutlog.database


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercises)
    //    @Query"(SELECT)"
    @Query ( "SELECT * FROM Exercises")
    fun getExercises(): LiveData<List<Exercises>>

    @Query("SELECT * FROM Exercises WHERE categoryId = :categoryId")
    fun getExercisesByCategory(categoryId: String): LiveData<List<Exercises>>

    @Query("SELECT * FROM Exercises WHERE exerciseId IN (:todayExerciseId)")
    fun getTodayExercisesByExerciseId(todayExerciseId:List<String>):LiveData<List<Exercises>>
}