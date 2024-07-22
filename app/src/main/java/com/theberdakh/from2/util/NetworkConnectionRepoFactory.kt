package com.theberdakh.from2.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.theberdakh.from2.presentation.TranslateFragmentViewModel

class NetworkConnectionRepoFactory(private val repository: NetworkConnectionRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslateFragmentViewModel::class.java)){
            return TranslateFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}