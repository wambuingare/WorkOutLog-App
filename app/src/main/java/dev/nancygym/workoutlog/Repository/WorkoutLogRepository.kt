package dev.nancygym.workoutlog.Repository

class WorkoutLogRepository {
    val database =WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao =database.workoutLogDao()

    suspend fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            workoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }
    fun getTodaysWorkoutLogRecords(userId:String, currentDate: String): LiveData<List<WorkoutLogRecord>>{
        return  workoutLogRecordDao.getWorkoutLogsById(userId,currentDate)

    }
}