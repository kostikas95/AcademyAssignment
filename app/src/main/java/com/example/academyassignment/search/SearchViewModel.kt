package com.example.academyassignment.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.academyassignment.Networking
import com.example.academyassignment.model.CryptoBasicData
import com.example.academyassignment.model.VsCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoBasicData>>()
    val cryptoList: LiveData<List<CryptoBasicData>> get() = _cryptoList

    private val vsCurrency: VsCurrency = VsCurrency.USD

    fun fetchSimilar(strInput: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cryptoBasicDataList = Networking.searchForSimilar(vsCurrency, strInput)
                _cryptoList.postValue(cryptoBasicDataList)

                val names: String = cryptoBasicDataList.map { it.name }.joinToString(",")
                Log.d("SEARCH VIEW MODEL", "${cryptoBasicDataList.size} " + names)
                Log.d("SEARCH VIEW MODEL", "id: ${cryptoBasicDataList[0].id}")
                Log.d("SEARCH VIEW MODEL", "name: ${cryptoBasicDataList[0].name}")
                Log.d("SEARCH VIEW MODEL", "current_price: ${cryptoBasicDataList[0].current_price}")
                Log.d("SEARCH VIEW MODEL", "symbol: ${cryptoBasicDataList[0].symbol}")
                Log.d("SEARCH VIEW MODEL", "image: ${cryptoBasicDataList[0].image}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}