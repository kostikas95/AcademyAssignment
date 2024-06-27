package com.example.academyassignment.cryptodetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.academyassignment.R
import com.example.academyassignment.databinding.FragmentCryptoDetailsBinding
import com.example.academyassignment.databinding.FragmentPopularBinding
import com.example.academyassignment.popular.PopularViewModel
import com.squareup.picasso.Picasso

class CryptoDetailsFragment : Fragment() {

    private lateinit var cryptoId: String
    private lateinit var binding: FragmentCryptoDetailsBinding
    private lateinit var viewModel: CryptoDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cryptoId = it.getString(CRYPTO_ID_ARG) ?: ""
        }
        Log.d("CRYPTODETAILSFRAGMENT", "crypto id: ${cryptoId}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(CryptoDetailsViewModel::class.java)
        viewModel.cryptoId = cryptoId

        viewModel.crypto.observe(viewLifecycleOwner, Observer { currentCrypto ->
            Log.d("CRYPTODETAILSFRAGMENT", "observer triggered")

            currentCrypto?.let {
                val id = currentCrypto.id
                Picasso.get().load(currentCrypto.image).into(binding.cryptoImage)
                binding.cryptoName.text = currentCrypto.name
                binding.cryptoSymbol.text = currentCrypto.symbol

                binding.currentPrice.text = currentCrypto.current_price.toString()
                binding.priceChange.text = "${currentCrypto.price_change_percentage_24h}%"
                if (currentCrypto.price_change_percentage_24h > 0) {
                    binding.priceChangeImage.setImageResource(R.drawable.price_change_positive)
                } else {
                    binding.priceChangeImage.setImageResource(R.drawable.price_change_negative)
                    binding.priceChangeImage.scaleY = -1f
                }
                binding.marketCap.text = currentCrypto.market_cap.toString()
                binding.marketCapChangePercentage.text = "${currentCrypto.market_cap_change_percentage_24h}%"
                if (currentCrypto.market_cap_change_percentage_24h > 0) {
                    binding.marketCapChangeImage.setImageResource(R.drawable.price_change_positive)
                } else {
                    binding.marketCapChangeImage.setImageResource(R.drawable.price_change_negative)
                    binding.marketCapChangeImage.scaleY = -1f
                }
            } ?: run {
                Log.d("CRYPTODETAILSFRAGMENT", "current crypto is null")
            }





        })
        viewModel.fetchCryptoData()
        binding.physicalCurrency.setImageResource(R.drawable.dollar_sign)








        // set fav button
        // set refresh button


    }



    companion object {
        private const val CRYPTO_ID_ARG = "cryptoId"

        fun newInstance(cryptoId: String): CryptoDetailsFragment {
            val args = Bundle().apply {
                putString(CRYPTO_ID_ARG, cryptoId)
            }
            return CryptoDetailsFragment().apply {
                arguments = args
            }
        }
    }
}