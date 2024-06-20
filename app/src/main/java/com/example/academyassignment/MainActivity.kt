package com.example.academyassignment

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.academyassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        val fragmentContainer: FragmentContainerView = binding.fragmentContainer

        val buttonPop: Button = binding.buttonPop
        buttonPop.setOnClickListener {
            // manipulate color of other buttons here
            Log.d("MAIN ACTIVITY", "go to populars")
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(fragmentContainer.id, PopularFragment())
            fragmentTransaction.commit()
        }

        val buttonFavs: Button = binding.buttonFavs
        buttonFavs.setOnClickListener {
            // manipulate color of other buttons here
            Log.d("MAIN ACTIVITY", "go to favourites")
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(fragmentContainer.id, FavouritesFragment())
            fragmentTransaction.commit()
        }

        // same for search button

    }

}