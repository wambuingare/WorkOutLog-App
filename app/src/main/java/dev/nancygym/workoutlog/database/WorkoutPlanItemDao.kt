package dev.nancygym.workoutlog.database


@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem(workoutPlanItemDao: WorkouPlanItem)

    @Query("SELECT* FROM workoutplanitem WHERE workoutPlanId = :workoutPlanId AND day = :dayNumber")
    fun getTodayWorkoutPlanItem(workoutPlanId:String,dayNumber:Int): LiveData<WorkouPlanItem>
}