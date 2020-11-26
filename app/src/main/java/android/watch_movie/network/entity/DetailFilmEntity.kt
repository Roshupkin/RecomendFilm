package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailFilmEntity(
    @SerializedName("data")
    @Expose
    val dataFilm: FilmNetworkEntity? = null,

    @SerializedName("externalId")
    @Expose
    val externalId: ExternalId?= null ,

    @SerializedName("budget")
    @Expose
    val budget: BudgetNetworkEntity? = null,

    @SerializedName("rating")
    @Expose
    val rating: RatingNetworkEntity? = null,

    @SerializedName("review")
    @Expose
    val review: ReviewNetworkEntity? = null,

    )