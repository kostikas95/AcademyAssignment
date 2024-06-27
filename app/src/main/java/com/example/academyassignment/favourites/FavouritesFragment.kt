package com.example.academyassignment.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.academyassignment.PreferencesManager
import com.example.academyassignment.R
import com.example.academyassignment.RecyclerListAdapter
import com.example.academyassignment.cryptodetails.CryptoDetailsFragment
import com.example.academyassignment.databinding.FragmentFavouritesBinding
import com.example.academyassignment.databinding.FragmentPopularBinding
import com.example.academyassignment.popular.PopularViewModel


class FavouritesFragment : Fragment() {

    private lateinit var viewModel: FavouritesViewModel
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var cryptoAdapter: RecyclerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FavouritesViewModel::class.java)


        cryptoAdapter = RecyclerListAdapter { cryptoId ->
            navigateToDetailsFragment(cryptoId)
        }

        binding.favsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cryptoAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        viewModel.favouriteCryptoList.observe(viewLifecycleOwner, Observer { cryptoList ->
            cryptoAdapter.submitList(cryptoList)
        })

        viewModel.fetchFavouriteCryptos()
    }

    private fun navigateToDetailsFragment(cryptoId: String) {
        val cryptoDetailsFragment = CryptoDetailsFragment.newInstance(cryptoId)

        // Perform fragment transaction to replace the current fragment
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(requireActivity().findViewById<ViewGroup>(R.id.fragment_container).id, cryptoDetailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}