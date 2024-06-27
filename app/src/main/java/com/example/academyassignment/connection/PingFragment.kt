package com.example.academyassignment.connection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.academyassignment.R
import com.example.academyassignment.databinding.FragmentPingBinding
import com.example.academyassignment.databinding.FragmentPopularBinding
import com.example.academyassignment.popular.PopularViewModel


class PingFragment : Fragment() {

    private lateinit var binding: FragmentPingBinding
    private lateinit var viewModel: PingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}