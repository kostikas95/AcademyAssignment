package com.example.academyassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.academyassignment.databinding.HolderRecyclerViewBinding
import com.example.academyassignment.model.CryptoData
import com.example.academyassignment.repositories.CryptosRepository

class RecyclerViewAdapter(arrayData: List<String>) : RecyclerView.Adapter<RecyclerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerViewHolder {
        val view = HolderRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CryptosRepository.getCryptos().count()
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(arrayData[position])
    }
}