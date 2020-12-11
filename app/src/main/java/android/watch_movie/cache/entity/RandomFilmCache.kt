package android.watch_movie.cache.entity

import android.watch_movie.network.entity.CountryNetworkEntity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "random_film")
data class RandomFilmCache(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "filmID")
    var filmID: Int,

    @ColumnInfo(name = "loadCount")
    var loadCount:Int? = 0,

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
    var genres: List<String>? = null,

    @ColumnInfo(name = "posterUrlPreview")
    var posterUrlPreview: String? = null,

    @ColumnInfo(name = "ratingChange")
    var ratingChange: Any? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null
)