package com.example.academyassignment

import androidx.recyclerview.widget.RecyclerView
import com.example.academyassignment.databinding.HolderRecyclerViewBinding

class RecyclerViewHolder(val binding: HolderRecyclerViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

        public fun bind(data: String) {
            // link the data to the holder attributes here
        }
}