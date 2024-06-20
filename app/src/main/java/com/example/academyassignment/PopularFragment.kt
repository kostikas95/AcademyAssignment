package com.example.academyassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.academyassignment.databinding.FragmentPopularBinding


class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (context != null) {
            // binding.recyclerView gives us the view of the layout' s element with that id but in
            // snake_case. the type of the binding.recyclerView is RecyclerView as in the xml
            // this type has an attribute adapter which we define ourselves
            binding.popRecyclerView.adapter = RecyclerViewAdapter(getArrayData())
        }
    }
}