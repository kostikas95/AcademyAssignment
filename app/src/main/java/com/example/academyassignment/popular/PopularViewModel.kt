package com.example.academyassignment.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.academyassignment.Networking
import com.example.academyassignment.model.CryptoBasicData
import com.example.academyassignment.model.VsCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {

    private val _popularCryptoList = MutableLiveData<List<CryptoBasicData>>()
    val popularCryptoList: LiveData<List<CryptoBasicData>>
        get() = _popularCryptoList


    private var currentPage = 1
    private val perPage = 25
    private val vsCurrency: VsCurrency = VsCurrency.USD

    fun fetchFirstPageOfCryptos() {
        currentPage = 1
        fetchCryptos()
    }

    fun fetchNextPageOfCryptos() {
        currentPage++
        fetchCryptos()
    }

    private fun fetchCryptos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cryptoBasicDataList = Networking.queryCoinListWithMarketData(vsCurrency, currentPage, perPage)
                val currentList = _popularCryptoList.value ?: emptyList()
                val updatedList = currentList + cryptoBasicDataList
                _popularCryptoList.postValue(updatedList)
                // val names: String = currentList.map { it.name }.joinToString(",")
                // Log.d("VIEW MODEL", "${currentList.size} " + names)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
