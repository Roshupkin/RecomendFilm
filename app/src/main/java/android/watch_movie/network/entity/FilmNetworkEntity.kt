package android.watch_movie.network.entity

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmNetworkEntity(
    @SerializedName("countries")
    @Expose
    val countries: List<CountryNetworkEntity>?,

    @SerializedName("filmId")
    @Expose
    val filmId: Int,

    @SerializedName("filmLength")
    @Expose
    val filmLength: String,

    @SerializedName("genres")
    @Expose
    val genres: List<GenreNetworkEntity>?,

    @SerializedName("nameEn")
    @Expose
    val nameEn: String?,

    @SerializedName("nameRu")
    @Expose
    val nameRu: String,

    @SerializedName("posterUrl")
    @Expose
    val posterUrl: String,

    @SerializedName("posterUrlPreview")
    @Expose
    val posterUrlPreview: String,

    @SerializedName("rating")
    @Expose
    val rating: String,

    @SerializedName("ratingChange")
    @Expose
    val ratingChange: Any? ,

    @SerializedName("ratingVoteCount")
    @Expose
    val ratingVoteCount: Int,

    @SerializedName("year")
    @Expose
    val year: String


) {
    init {
        val TAG = "Network Entity"
        Log.e(TAG,"ERROR: $year")
    }
}