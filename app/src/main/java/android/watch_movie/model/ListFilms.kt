package android.watch_movie.model

data class ListFilms(
   var pagesCount: Int,
   var films: List<Film>? = null
)