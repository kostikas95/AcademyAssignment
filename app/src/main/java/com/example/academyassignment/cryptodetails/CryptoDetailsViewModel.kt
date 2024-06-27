package com.example.academyassignment.cryptodetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.academyassignment.Networking
import com.example.academyassignment.model.CryptoDetailedData
import com.example.academyassignment.model.VsCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoDetailsViewModel() : ViewModel() {

    var cryptoId: String? = null
    private val vsCurrency: VsCurrency = VsCurrency.USD
    private val _crypto = MutableLiveData<CryptoDetailedData>()
    val crypto: LiveData<CryptoDetailedData> get() = _crypto

    fun fetchCryptoData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val receivedCrypto: CryptoDetailedData = Networking.queryCoinListWithMarketData(vsCurrency, cryptoId!!)
                _crypto.postValue(receivedCrypto)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}