package dev.nancygym.workoutlog.database


@Dao
interface WorkoutLogDaoRecord {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLog: WorkoutLogRecord)

    @Query("SELECT * FROM workoutlogrecord WHERE userId =:userId AND date >=:currentDate")
    fun getWorkoutLogsById(userId: String, currentDate: String): LiveData<List<WorkoutLogRecord>>
}
