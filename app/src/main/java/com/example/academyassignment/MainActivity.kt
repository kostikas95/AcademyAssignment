package com.example.academyassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.academyassignment.databinding.ActivityMainBinding
import com.example.academyassignment.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        PreferencesManager.initialize(this)
        val fragmentContainer: FragmentContainerView = binding.fragmentContainer
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentContainer.id, SplashFragment()).addToBackStack(null)
        fragmentTransaction.commit()



        // val buttonPop: Button = binding.buttonPop
        // buttonPop.setOnClickListener {
        //     // manipulate color of other buttons here
        //     val fragmentTransaction = supportFragmentManager.beginTransaction()
        //     fragmentTransaction.replace(fragmentContainer.id, PopularFragment()).addToBackStack(null)
        //     fragmentTransaction.commit()
        // }
    }
}