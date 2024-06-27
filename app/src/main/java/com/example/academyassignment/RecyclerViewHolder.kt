package com.example.academyassignment

import androidx.recyclerview.widget.RecyclerView
import com.example.academyassignment.databinding.HolderRecyclerViewBinding
import com.example.academyassignment.model.CryptoBasicData
import com.squareup.picasso.Picasso

class RecyclerViewHolder(val binding: HolderRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {

    public fun bind(crypto: CryptoBasicData, onItemClick: (String) -> Unit) {
        Picasso.get().load(crypto.image).into(binding.cryptoImage)
        val name: String = crypto.name
        val symbol: String = crypto.symbol
        binding.coinTitle.text = "$name\n($symbol)"
        binding.currentPrice.text = String.format("%.3f", crypto.current_price)
        binding.priceChange.text = String.format("%.3f%%", crypto.price_change_percentage_24h)
        if (crypto.price_change_percentage_24h > 0) {
            binding.changeImage.setImageResource(R.drawable.price_change_positive)
            binding.changeImage.scaleY = 1f
        } else {
            binding.changeImage.setImageResource(R.drawable.price_change_negative)
            binding.changeImage.scaleY = -1f
        }
        binding.physicalCurrency.setImageResource(R.drawable.dollar_sign)

        itemView.setOnClickListener {
            onItemClick(crypto.id)
        }
    }
}