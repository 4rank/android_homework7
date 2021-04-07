package com.example.fedorinchik_hw7.viewmodelfactory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fedorinchik_hw7.GifData
import com.example.fedorinchik_hw7.GiphyRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel (private val repository: GiphyRepository) : ViewModel() {

    val giphGet: MutableLiveData<Response<GifData>> = MutableLiveData()
    val giphTrend: MutableLiveData<Response<GifData>> = MutableLiveData()

    fun startGiphy(request: String) {
        viewModelScope.launch {
            val set = repository.takeGiphy(request)
            giphGet.value = set
        }
    }

    fun trendGiphy() {
        viewModelScope.launch {
            val set = repository.startTrend()
            giphTrend.value = set
        }
    }
}