package dev.nancygym.workoutlog.database


@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(exerciseCategory: ExerciseCategory)

    @Query("SELECT * FROM ExerciseCategory")
    fun getExerciseCategories(): LiveData<List<ExerciseCategory>>
}
