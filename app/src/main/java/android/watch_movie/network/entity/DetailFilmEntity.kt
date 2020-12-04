package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailFilmEntity(
    @SerializedName("data")
    @Expose
    val dataFilm: FilmNetworkEntity? = null,
    @Expose
    val externalId: ExternalId?= null,
    @Expose
    val budget: BudgetNetworkEntity? = null,
    @Expose
    val rating: RatingNetworkEntity? = null,
    @Expose
    val review: ReviewNetworkEntity? = null,
    )