package com.example.fedorinchik_hw7.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fedorinchik_hw7.GiphyRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: GiphyRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}