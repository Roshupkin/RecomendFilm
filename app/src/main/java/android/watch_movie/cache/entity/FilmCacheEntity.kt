package android.watch_movie.cache.entity

import android.watch_movie.network.entity.CountryNetworkEntity
import android.watch_movie.network.entity.GenreNetworkEntity
import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "film", primaryKeys = arrayOf("id", "filmId"))
data class FilmCacheEntity(
    /* @PrimaryKey(autoGenerate = true)*/
    val id: Int = 1,
    @ColumnInfo(name = "filmId")
    var filmId: Int,

    @ColumnInfo(name = "nameRu")
    var nameRu: String,

    @ColumnInfo(name = "nameEn")
    var nameEn: String? = null,

    @ColumnInfo(name = "year")
    var year: String,

    @ColumnInfo(name = "filmLength")
    var filmLength: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "ratingVoteCount")
    var ratingVoteCount: Int,

    @ColumnInfo(name = "posterUrl")
    var posterUrl: String,

    @ColumnInfo(name = "countries")
    var countries: List<CountryNetworkEntity>? = null,

    @ColumnInfo(name = "genres")
    var genres: List<GenreNetworkEntity>? = null,

    @ColumnInfo(name = "posterUrlPreview")
    var posterUrlPreview: String,

    @ColumnInfo(name = "ratingChange")
    var ratingChange: Any? = null
)
