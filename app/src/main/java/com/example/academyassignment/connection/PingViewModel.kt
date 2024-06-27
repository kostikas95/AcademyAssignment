package com.example.academyassignment.connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.academyassignment.Networking
import com.example.academyassignment.model.PingResponse
import kotlinx.coroutines.launch

class PingViewModel : ViewModel() {

    private val _pingResponse = MutableLiveData<PingResponse?>()
    val pingResponse: LiveData<PingResponse?>
        get() = _pingResponse

    fun fetchPing() {
        viewModelScope.launch {
            val response = Networking.ping()
            //_pingResponse.postValue(response)
        }
    }
}