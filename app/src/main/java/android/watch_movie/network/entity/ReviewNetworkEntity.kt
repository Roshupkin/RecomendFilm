package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class ReviewNetworkEntity(
    @Expose
    val reviewsCount: Int? = null,
    @Expose
    val ratingGoodReview: String? = null,
    @Expose
    val ratingGoodReviewVoteCount: Int? = null,
)