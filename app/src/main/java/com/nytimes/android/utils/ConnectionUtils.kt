package com.nytimes.android.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectionUtils {
    companion object{
        fun CheckNetwork(context: Context): Boolean {
            try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                        return true
                    } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                        return true
                    }
                } else {
                    return false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }
    }
}