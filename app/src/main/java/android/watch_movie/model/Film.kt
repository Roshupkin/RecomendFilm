package android.watch_movie.model

import android.watch_movie.network.entity.CountryNetworkEntiy
import android.watch_movie.network.entity.GenreNetworkEntity


data class Film(
    var filmId: Int,
    var nameRu: String,
    var nameEn: String,
    var year: String,
    var filmLength: String,
    var countries: List<Country>? = null,
    var genres: List<Genre>? = null,
    var rating: String,
    var ratingVoteCount: Int,
    var posterUrl: String,
    var posterUrlPreview: String,
    var ratingChange: Any
)