package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CountryNetworkEntiy(
    @SerializedName("country")
    @Expose
    var country: String
)
