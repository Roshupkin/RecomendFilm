package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryNetworkEntity(
    @Expose
    var id: Int?,
    @Expose
    val country: String?
)