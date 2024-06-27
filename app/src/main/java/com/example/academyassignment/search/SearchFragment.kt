package com.example.academyassignment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.academyassignment.R
import com.example.academyassignment.databinding.FragmentSearchBinding
import com.example.academyassignment.RecyclerListAdapter
import com.example.academyassignment.cryptodetails.CryptoDetailsFragment
import com.example.academyassignment.popular.PopularFragment


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var cryptoAdapter: RecyclerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        viewModel.cryptoList.observe(viewLifecycleOwner, Observer { cryptoList ->
            cryptoAdapter.submitList(cryptoList)
        })

        cryptoAdapter = RecyclerListAdapter { cryptoId ->
            navigateToDetailsFragment(cryptoId)
        }

        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cryptoAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        val buttonSearch: ImageButton = binding.searchButton
        buttonSearch.setOnClickListener {
            val editText: EditText = binding.editText
            val strInput: String = editText.text.toString()
            if (strInput.length > 0) {
                viewModel.fetchSimilar(strInput)
            }
        }
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