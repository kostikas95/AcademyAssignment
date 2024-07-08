package com.example.academyassignment.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.academyassignment.R
import com.example.academyassignment.databinding.FragmentSplashBinding
import com.example.academyassignment.popular.PopularFragment


class SplashFragment : Fragment() {

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SplashViewModel::class.java)

        viewModel.checkConnectionToInternet(requireActivity())

        viewModel.connectionToInternet.observe(viewLifecycleOwner, Observer { hasInternet ->
            // Update your UI based on the connection status
            if (hasInternet) {
                viewModel.pingApi()
            } else {
                binding.connectionStatusText.text = "No internet connection.\nEnable mobile data or connect to a wifi network."
            }
        })

        viewModel.connectionToServer.observe(viewLifecycleOwner, Observer {connectsToServer ->
            if (connectsToServer) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PopularFragment())
                    .commit()
            } else {
                binding.connectionStatusText.text = "Server unavailable.\nTry again later."
            }
        })
    }

}