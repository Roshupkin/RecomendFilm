package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListFilmsNetworkEntity(
    @SerializedName("pagesCount")
    @Expose
    val pagesCount: Int,
    @SerializedName("films")
    @Expose
    val films: List<FilmNetworkEntity>?

)