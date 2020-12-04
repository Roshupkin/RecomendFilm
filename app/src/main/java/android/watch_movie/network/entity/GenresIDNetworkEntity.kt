package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class GenresIDNetworkEntity(
    @Expose
    val genres: List<GenreIDNetworkEntity>?
)