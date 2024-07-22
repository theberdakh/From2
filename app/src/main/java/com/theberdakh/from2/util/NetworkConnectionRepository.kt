package com.theberdakh.from2.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkConnectionRepository(private val connectivityManager: ConnectivityManager) {

    private val _isConnected = MutableStateFlow(false)
    val isConnected: Flow<Boolean> = _isConnected
    companion object {
        const val TAG = "NetworkConnectionRepository"
    }

    init {
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                Log.i(TAG, "onAvailable")
                _isConnected.value = true
            }

            override fun onLost(network: Network) {
                Log.i(TAG, "onLost")
                _isConnected.value = false
            }

        })
    }

}