package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class GenreIDNetworkEntity(
    @Expose
    var idGenre: Int?,
    @Expose
    var genre: String?
)