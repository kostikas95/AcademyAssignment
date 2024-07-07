package com.example.academyassignment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.academyassignment.databinding.ActivityMainBinding
import com.example.academyassignment.favourites.FavouritesFragment
import com.example.academyassignment.popular.PopularFragment
import com.example.academyassignment.search.SearchFragment

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

        // val pingResponse: String = Networking.ping()
        // Log.d("PING", pingResponse)


        val buttonPop: Button = binding.buttonPop
        buttonPop.setOnClickListener {
            // manipulate color of other buttons here
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(fragmentContainer.id, PopularFragment()).addToBackStack(null)
            fragmentTransaction.commit()
        }

        val buttonSearch: Button = binding.buttonSearch
        buttonSearch.setOnClickListener {
            // manipulate color of other buttons here
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(fragmentContainer.id, SearchFragment()).addToBackStack(null)
            fragmentTransaction.commit()
        }

        val buttonFavs: Button = binding.buttonFavs
        buttonFavs.setOnClickListener {
            // manipulate color of other buttons here
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(fragmentContainer.id, FavouritesFragment()).addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}