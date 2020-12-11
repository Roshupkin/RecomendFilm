package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class ListFilmsNetworkEntity(
    @Expose
    val pagesCount: Int,
    @Expose
    val films: List<FilmNetworkEntity>?

)