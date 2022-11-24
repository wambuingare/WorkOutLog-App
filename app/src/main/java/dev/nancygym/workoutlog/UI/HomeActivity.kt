package dev.nancygym.workoutlog.UI

import android.content.SharedPreferences
import dev.nancygym.workoutlog.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity () {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    val exrciseViewModel: ExerciseViewModel by viewModels()
    lateinit var token:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS_FILE, MODE_PRIVATE)
        token = sharedPrefs.getString(Constants.ACCESS_TOKEN, "").toString()
        exerciseViewModel.fetchDbExercises()
        exerciseViewModel.fetchDbCategories()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()

    }

    override fun onResume() {
        super.onResume()

        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExerciseCategories(token)
            }
        })
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExercises(token)
            }
        } )
        exerciseViewModel.errorLiveData.observe(this, Observer { erroMsg->
            Toast.makeText(this,erroMsg,Toast.LENGTH_LONG).show()
        })
    }

    fun setupBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, PlanFragment())
                        .commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, TrackFragment())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }

}








