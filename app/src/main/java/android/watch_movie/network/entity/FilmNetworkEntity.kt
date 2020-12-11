package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class FilmNetworkEntity(
    @Expose
    val filmId: Int,
    @Expose
    val nameRu: String?,
    @Expose
    val nameEn: String?,
    @Expose
    val webUrl: String?,
    @Expose
    val posterUrl: String?,
    @Expose
    val posterUrlPreview: String?,
    @Expose
    val year: String?,
    @Expose
    val filmLength: String?,
    @Expose
    val slogan: String?,
    @Expose
    val description: String?,
    @Expose
    var type: String?,
    @Expose
    val ratingMpa: String?,
    @Expose
    val ratingAgeLimits: Int?,
    @Expose
    val premiereRu: String?,
    @Expose
    val distributors: String?,
    @Expose
    val premiereWorld: String?,
    @Expose
    val premiereDigital: String?,
    @Expose
    val premiereWorldCountry: String?,
    @Expose
    val premiereDvd: String?,
    @Expose
    val premiereBluRay: String?,
    @Expose
    val distributorRelease: String?,
    @Expose
    val countries: List<CountryNetworkEntity>?,
    @Expose
    val genres: List<GenreNetworkEntity>?,
    @Expose
    val facts: List<String>?,
    @Expose
    val seasons: List<Int>?,
    @Expose
    val rating: String?,
    @Expose
    val ratingVoteCount: Int?,
    @Expose
    val ratingChange: Any?
)