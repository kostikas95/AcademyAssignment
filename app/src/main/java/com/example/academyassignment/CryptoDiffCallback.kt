package com.example.academyassignment

import androidx.recyclerview.widget.DiffUtil
import com.example.academyassignment.model.CryptoBasicData

class CryptoDiffCallback : DiffUtil.ItemCallback<CryptoBasicData>() {
    override fun areItemsTheSame(old: CryptoBasicData, new: CryptoBasicData) : Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: CryptoBasicData, new: CryptoBasicData) : Boolean {
        return old == new
    }
}