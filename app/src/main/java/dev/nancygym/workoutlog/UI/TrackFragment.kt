package dev.nancygym.workoutlog.UI

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.nancygym.workoutlog.R
import dev.nancygym.workoutlog.viewmodel.ExerciseViewModel
import dev.nancygym.workoutlog.viewmodel.WOrkoutPlanItem
import dev.nancygym.workoutlog.viewmodel.WorkoutPlanViewModel


class TrackFragment : Fragment(), LogWorkout {
    lateinit var binding: FragmentTrackBinding
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    val exerciseViewModel: ExerciseViewModel by viewModels()

    lateinit var prefs: SharedPreferences
    lateinit var userId: String
    lateinit var wOrkoutPlanItemId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentTrackingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        prefs =
            requireContext().getSharedPreferences(Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        userId = prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING).toString()
        workoutPlanViewModel.getExistingWorkoutPlans(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer {
            workoutPlanViewModel
            val workoutPlanId = workoutPlan.workoutPlanId
            val dayNumber = LocalDate.now().dayOfWeek.value
            workoutPlanViewModel.getTodayWorkoutPlanItem(workoutPlanId, dayNumber).observe(this,
                Observer { workoutPlanItem ->
                    if (workoutPlanItem != null) {
                        workoutPlanItemId = workoutPlanItem.workoutPlanItemId

                        val todayExerciseIds = workoutPlanItem.exerciseId
                        exerciseViewModel.getExerciseByExerciseId(todayExerciseIds)
                            .observe(this, Observer { exercises ->
                                val adapter = TrackFragment(exercises, this)
                                binding.rvTrack.layoutManager =
                                    binding.rvTrack.adapter = adapter
                            })
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "No workoutplan  item found for today. Create one to proceed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })

        })

    }

    override fun onClickDone(set: Int, weight: Int, reps: Int, exerciseId: String) {

    }












}


