package android.watch_movie.cache.entity

import android.watch_movie.network.entity.CountryNetworkEntity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_film")
data class FavoritesFilmCache(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "filmID")
    var filmID: Int,

    @ColumnInfo(name = "loadCount")
    var countFilm: Int? = 0,

    @ColumnInfo(name = "nameRu")
    var nameRu: String? = null,

    @ColumnInfo(name = "nameEn")
    var nameEn: String? = null,

    @ColumnInfo(name = "webUrl")
    var webUrl: String? = null,

    @ColumnInfo(name = "posterUrl")
    var posterUrl: String? = null,

    @ColumnInfo(name = "posterUrlPreview")
    var posterUrlPreview: String? = null,

    @ColumnInfo(name = "year")
    var year: String? = null,

    @ColumnInfo(name = "filmLength")
    var filmLength: String? = null,

    @ColumnInfo(name = "slogan")
    var slogan: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "ratingMpa")
    var ratingMpa: String? = null,

    @ColumnInfo(name = "ratingAgeLimits")
    var ratingAgeLimits: Int? = null,

    @ColumnInfo(name = "premiereRu")
    var premiereRu: String? = null,

    @ColumnInfo(name = "distributors")
    var distributors: String? = null,

    @ColumnInfo(name = "premiereWorld")
    var premiereWorld: String? = null,

    @ColumnInfo(name = "premiereDigital")
    var premiereDigital: String? = null,

    @ColumnInfo(name = "premiereWorldCountry")
    var premiereWorldCountry: String? = null,

    @ColumnInfo(name = "premiereDvd")
    var premiereDvd: String? = null,

    @ColumnInfo(name = "premiereBluRay")
    var premiereBluRay: String? = null,

    @ColumnInfo(name = "distributorRelease")
    var distributorRelease: String? = null,

    @ColumnInfo(name = "countries")
    var countries: List<CountryNetworkEntity>? = null,

    @ColumnInfo(name = "genres")
    var genres: List<String>? = null,

    @ColumnInfo(name = "facts")
    val facts: List<String>? = null,

    @ColumnInfo(name = "seasons")
    val seasons: List<Int>? = null,

    @ColumnInfo(name = "rating")
    var rating: String? = null,

    @ColumnInfo(name = "ratingVoteCount")
    var ratingVoteCount: Int? = null,

    @ColumnInfo(name = "ratingChange")
    var ratingChange: Any? = null
)