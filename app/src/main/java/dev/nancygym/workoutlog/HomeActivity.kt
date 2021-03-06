package dev.nancygym.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.nancygym.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        setUpBottomNav()
    }
    fun castViews(){
//        bnvHome=findViewById(R.id.bottom_navigation)
//        fcvHome=findViewById(R.id.fcvHome)
    }
    fun setUpBottomNav(){
       binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.plan->{
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track->{
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, TrackFragment())
                    transaction.commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}