package com.example.academyassignment.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.academyassignment.Networking
import com.example.academyassignment.PreferencesManager
import com.example.academyassignment.model.CryptoBasicData
import com.example.academyassignment.model.VsCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel() : ViewModel() {

    private val _favouriteCryptoList = MutableLiveData<List<CryptoBasicData>>()
    val favouriteCryptoList: LiveData<List<CryptoBasicData>>
        get() = _favouriteCryptoList


    private val vsCurrency: VsCurrency = VsCurrency.USD

    fun fetchFavouriteCryptos() {
        val ids: List<String> = PreferencesManager.getFavoriteCryptos()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cryptoBasicDataList = Networking.queryCoinListWithMarketData(vsCurrency, ids)
                _favouriteCryptoList.postValue(cryptoBasicDataList)
                // val names: String = currentList.map { it.name }.joinToString(",")
                // Log.d("VIEW MODEL", "${currentList.size} " + names)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}