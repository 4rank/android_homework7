package com.example.fedorinchik_hw7.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.fedorinchik_hw7.Repository

class MainViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}