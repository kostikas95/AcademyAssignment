package com.example.academyassignment.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyassignment.databinding.FragmentPopularBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.academyassignment.R
import com.example.academyassignment.RecyclerListAdapter
import com.example.academyassignment.cryptodetails.CryptoDetailsFragment
import com.example.academyassignment.model.CryptoBasicData

class PopularFragment : Fragment() {

    private lateinit var viewModel: PopularViewModel
    private lateinit var binding: FragmentPopularBinding
    private lateinit var cryptoAdapter: RecyclerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PopularViewModel::class.java)

        cryptoAdapter = RecyclerListAdapter { cryptoId ->
            navigateToDetailsFragment(cryptoId)
        }

        binding.popRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cryptoAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (lastVisibleItemPosition + 1 == totalItemCount) {
                        viewModel.fetchNextPageOfCryptos()
                    }
                }
            })
        }

        viewModel.popularCryptoList.observe(viewLifecycleOwner, Observer { cryptoList ->
            cryptoAdapter.submitList(cryptoList)
        })

        viewModel.fetchFirstPageOfCryptos()
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
