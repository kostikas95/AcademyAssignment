package com.example.academyassignment.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.academyassignment.Networking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {

    private var _connectionToInternet = MutableLiveData<Boolean>()
    val connectionToInternet: LiveData<Boolean>
        get() = _connectionToInternet

    private var _connectionToServer = MutableLiveData<Boolean>()
    val connectionToServer: LiveData<Boolean>
        get() = _connectionToServer


    fun checkConnectionToInternet(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        if (network == null) {
            _connectionToInternet.postValue(false)
            return
        }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities == null) {
            _connectionToInternet.postValue(false)
            return
        }
        _connectionToInternet.postValue(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
    }
    fun pingApi() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: String = Networking.ping()
                if (response == "{\"gecko_says\":\"(V3) To the Moon!\"}") {
                    _connectionToServer.postValue(true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}