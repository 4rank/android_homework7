package com.example.fedorinchik_hw7.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fedorinchik_hw7.*
import kotlinx.coroutines.launch
import retrofit2.Response

open class ViewModel (private val repository: Repository) : ViewModel() {

    private val _responseStartGiphy: MutableLiveData<Response<GiphyData>> = MutableLiveData()
    private val giphyStartResponse: LiveData<Response<GiphyData>>
        get() = _responseStartGiphy

    private val _responseGiphy: MutableLiveData<Response<GiphyData>> = MutableLiveData()
    private val giphyResponse: LiveData<Response<GiphyData>>
        get() = _responseGiphy


    @SuppressLint("StaticFieldLeak")
    private lateinit var recyclerView: RecyclerView

    var searchTag:String = ""


    fun startGiphy(adapter: AdapterGiphy) {
        viewModelScope.launch {
            val response = repository.startGiphy()
            _responseStartGiphy.value = response
        }
        giphyStartResponse.observeForever { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setChanged(it) }
            }
        }
    }

    fun getGiphy(
        query: String,
        adapter: AdapterGiphy,
        editText: EditText
    ) {
        viewModelScope.launch {
            val response = repository.provideGiphy(query)
            _responseGiphy.value = response
        }
        searchTag = editText.text.toString()
        giphyResponse.observeForever { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setChanged(it) }
            }
        }
    }

    fun startRecycler(adapter: AdapterGiphy, recycler: RecyclerView) {
        recyclerView = recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    }

    fun intent(context: Context, giphyData: Data): Intent {
        val intent = Intent(context, GiphyActivity::class.java)
        intent.putExtra(GiphConstants.GIPHY_URL, giphyData.images.original.url)
        intent.putExtra(GiphConstants.GIPHY_TITLE, giphyData.title)
        return intent
    }

}