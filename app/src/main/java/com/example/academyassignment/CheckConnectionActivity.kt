package com.example.academyassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.academyassignment.databinding.ActivityCkeckConnectionBinding

class CheckConnectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCkeckConnectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCkeckConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }
}