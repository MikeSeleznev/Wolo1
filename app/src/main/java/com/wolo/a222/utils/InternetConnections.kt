package com.wolo.a222.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class InternetConnections(context: Context, private val callback: InternetConnectionCallback) : LiveData<Boolean>() {

   /* private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun observeConnection(
        networkCallback: ConnectivityManager.NetworkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        object :
            ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network?) {

            }

            override fun onUnavailable() {
                callback.onInternetAvailable(false)
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {

            }

            override fun onAvailable(network: Network?) {
                callback.onInternetAvailable(true)
            }
        }
    ) {
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun unregisterObserver(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }*/
}

interface InternetConnectionCallback{
    fun onInternetAvailable(isAvailable: Boolean)
}
