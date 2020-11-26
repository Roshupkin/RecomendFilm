package android.watch_movie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import javax.inject.Inject

class NetworkCheck
@Inject
constructor() {
    val TAG = "NetworkCheck"
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                     //   Log.e(TAG, "NetworkCapabilities.TRANSPORT_CELLULAR   true")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                   //     Log.e(TAG, "NetworkCapabilities.TRANSPORT_WIFI  true")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                     //   Log.e(TAG, "NetworkCapabilities.TRANSPORT_ETHERNET   true")
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                Log.e(TAG,
                    "activeNetworkInfo.isConnected   true")
                return true
            }
        }
        Log.e(TAG, "Internet Connection   false ")
        return false
    }

}