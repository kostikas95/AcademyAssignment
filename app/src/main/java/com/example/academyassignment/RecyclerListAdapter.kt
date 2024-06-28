package com.example.academyassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.academyassignment.databinding.HolderRecyclerViewBinding
import com.example.academyassignment.model.CryptoBasicData

class RecyclerListAdapter(
    private val onItemClick: (String) -> Unit
): ListAdapter<CryptoBasicData, RecyclerViewHolder>(CryptoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerViewHolder {
        val view = HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}