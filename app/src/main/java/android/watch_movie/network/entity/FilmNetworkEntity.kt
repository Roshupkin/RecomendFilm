package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FilmNetworkEntity(
    @SerializedName("filmId")
    @Expose
    var filmId: Int,

    @SerializedName("nameRu")
    @Expose
    var nameRu: String,

    @SerializedName("nameEn")
    @Expose
    var nameEn: String,

    @SerializedName("year")
    @Expose
    var year: String,

    @SerializedName("filmLength")
    @Expose
    var filmLength: String,

    @SerializedName("countries")
    @Expose
    var countries: List<CountryNetworkEntiy>? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<GenreNetworkEntity>? = null,

    @SerializedName("rating")
    @Expose
    var rating: String,

    @SerializedName("ratingVoteCount")
    @Expose
    var ratingVoteCount: Int,

    @SerializedName("posterUrl")
    @Expose
    var posterUrl: String,

    @SerializedName("posterUrlPreview")
    @Expose
    var posterUrlPreview: String,

    @SerializedName("ratingChange")
    @Expose
    var ratingChange: Any
)