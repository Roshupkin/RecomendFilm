package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreNetworkEntity(
    @SerializedName("id")
    @Expose
    var idGenre: Int?,
    @SerializedName("genre")
    @Expose
    var genre: String?
)