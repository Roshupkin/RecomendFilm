package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreIDNetworkEntity(
    @Expose
    var idGenre: Int?,
    @Expose
    var genre: String?
)