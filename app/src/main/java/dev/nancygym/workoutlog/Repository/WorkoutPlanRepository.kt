package dev.nancygym.workoutlog.Repository

class WorkoutPlanRepository {
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao = database.workoutPlanDao()
    val workoutPlanItemDao = database.workoutPlanItemDao()
//    val  workoutPlanId = database

    suspend fun saveWorkoutPlan(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }
    suspend fun saveWorkoutPlanItem (workoutPlanItem: WorkouPlanItem){
        withContext(Dispatchers.IO){
            workoutPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
        }
    }
    fun getWorkoutPlanByUserId(userId: String): LiveData<WorkoutPlan> {
        return workoutPlanDao.getWorkoutPlanByUserId(userId)
    }
}