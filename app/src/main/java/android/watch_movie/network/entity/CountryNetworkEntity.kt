package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class CountryNetworkEntity(
    @Expose
    var id: Int?,
    @Expose
    val country: String?
)