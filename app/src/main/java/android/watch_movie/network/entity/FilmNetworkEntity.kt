package android.watch_movie.network.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmNetworkEntity(
    @SerializedName("filmId")
    @Expose
    val filmId: Int,

    @SerializedName("nameRu")
    @Expose
    val nameRu: String?,

    @SerializedName("nameEn")
    @Expose
    val nameEn: String?,

    @SerializedName("webUrl")
    @Expose
    val webUrl: String?,

    @SerializedName("posterUrl")
    @Expose
    val posterUrl: String?,

    @SerializedName("posterUrlPreview")
    @Expose
    val posterUrlPreview: String?,

    @SerializedName("year")
    @Expose
    val year: String?,

    @SerializedName("filmLength")
    @Expose
    val filmLength: String?,

    @SerializedName("slogan")
    @Expose
    val slogan: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("type")
    @Expose
    var type: String?,

    @SerializedName("ratingMpa")
    @Expose
    val ratingMpa: String?,

    @SerializedName("ratingAgeLimits")
    @Expose
    val ratingAgeLimits: Int?,

    @SerializedName("premiereRu")
    @Expose
    val premiereRu: String?,

    @SerializedName("distributors")
    @Expose
    val distributors: String?,

    @SerializedName("premiereWorld")
    @Expose
    val premiereWorld: String?,

    @SerializedName("premiereDigital")
    @Expose
    val premiereDigital: String?,

    @SerializedName("premiereWorldCountry")
    @Expose
    val premiereWorldCountry: String?,

    @SerializedName("premiereDvd")
    @Expose
    val premiereDvd: String?,

    @SerializedName("premiereBluRay")
    @Expose
    val premiereBluRay: String?,

    @SerializedName("distributorRelease")
    @Expose
    val distributorRelease: String?,

    @SerializedName("countries")
    @Expose
    val countries: List<CountryNetworkEntity>?,

    @SerializedName("genres")
    @Expose
    val genres: List<GenreNetworkEntity>?,

    @SerializedName("facts")
    @Expose
    val facts: List<String>?,

    @SerializedName("seasons")
    @Expose
    val seasons: List<Int>?,

    @SerializedName("rating")
    @Expose
    val rating: String?,

    @SerializedName("ratingVoteCount")
    @Expose
    val ratingVoteCount: Int?,

    @SerializedName("ratingChange")
    @Expose
    val ratingChange: Any?
)