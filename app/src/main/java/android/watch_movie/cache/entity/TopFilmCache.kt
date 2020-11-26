package android.watch_movie.cache.entity

import android.watch_movie.network.entity.CountryNetworkEntity
import android.watch_movie.network.entity.GenreNetworkEntity
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "top_film", primaryKeys = arrayOf("id", "filmId"))
data class TopFilmCache(
    /* @PrimaryKey(autoGenerate = true)*/
    val id: Int = 1,
    @ColumnInfo(name = "filmId")
    var filmId: Int,

    @ColumnInfo(name = "nameRu")
    var nameRu: String? = null,

    @ColumnInfo(name = "nameEn")
    var nameEn: String? = null,

    @ColumnInfo(name = "year")
    var year: String? = null,

    @ColumnInfo(name = "filmLength")
    var filmLength: String? = null,

    @ColumnInfo(name = "rating")
    var rating: String? = null,

    @ColumnInfo(name = "ratingVoteCount")
    var ratingVoteCount: Int? = null,

    @ColumnInfo(name = "posterUrl")
    var posterUrl: String? = null,

    @ColumnInfo(name = "countries")
    var countries: List<CountryNetworkEntity>? = null,

    @ColumnInfo(name = "genres")
    var genres: List<GenreNetworkEntity>? = null,

    @ColumnInfo(name = "posterUrlPreview")
    var posterUrlPreview: String? = null,

    @ColumnInfo(name = "ratingChange")
    var ratingChange: Any? = null,

    @ColumnInfo(name = "type")
    var type: String? = null
)
