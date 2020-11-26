package android.watch_movie.network.entity

data class RatingNetworkEntity(
    val rating: Int? = null,
    val ratingVoteCount: Int? = null,
    val ratingImdb: Int? = null,
    val ratingImdbVoteCount: Int? = null,
    val ratingFilmCritics: String? = null,
    val ratingFilmCriticsVoteCount: Int? = null,
    val ratingAwait: Int? = null,
    val ratingAwaitCount: Int? = null,
    val ratigRfCritics: Int? = null,
    val ratingRfCriticsVoteCount: Int? = null,
)