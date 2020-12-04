package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListFilmsNetworkEntity(
    @Expose
    val pagesCount: Int,
    @Expose
    val films: List<FilmNetworkEntity>?

)