package com.theberdakh.from2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.theberdakh.from2.util.NetworkConnectionRepository

class TranslateFragmentViewModel(private val networkConnectionRepository: NetworkConnectionRepository): ViewModel() {

    val isOnline = networkConnectionRepository.isConnected.asLiveData()
}