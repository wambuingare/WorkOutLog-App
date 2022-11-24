package dev.nancygym.workoutlog.viewModel

import android.app.Application
import android.content.Context

class WorkoutLog: Application () {
    companion object {
        lateinit var appcontext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appcontext = applicationContext
    }
}