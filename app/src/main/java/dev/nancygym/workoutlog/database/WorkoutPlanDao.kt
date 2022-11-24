package dev.nancygym.workoutlog.database


@Dao
interface WorkoutPlanDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan WHERE userId =:userId")
    fun getWorkoutPlanByUserId(userId: String):LiveData<WorkoutPlan>
}