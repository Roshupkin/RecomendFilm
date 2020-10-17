package android.watch_movie.model

import android.watch_movie.network.entity.FilmNetworkEntity

data class Best_Film(
   var pagesCount: Int,
   var films: List<Film>? = null
)