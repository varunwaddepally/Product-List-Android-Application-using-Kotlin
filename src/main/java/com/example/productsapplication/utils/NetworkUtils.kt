package com.example.productsapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtils private constructor(private val context: Context) {

    // Singleton instance
    companion object {
        @Volatile
        private var INSTANCE: NetworkUtils? = null

        fun getInstance(context: Context): NetworkUtils {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: NetworkUtils(context).also { INSTANCE = it }
            }
        }
    }

    // Function to check internet availability
    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}
