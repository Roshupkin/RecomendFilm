package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreNetworkEntity(
    @Expose
    var genre: String?
)