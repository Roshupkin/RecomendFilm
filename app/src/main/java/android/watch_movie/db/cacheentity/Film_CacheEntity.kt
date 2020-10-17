package android.watch_movie.db.cacheentity

import android.watch_movie.db.mapp.CountryCacheMapper
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film_CacheEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var idPage: Int = 0,
    @ColumnInfo(name = "filmId")
    var filmId: Int,

    @ColumnInfo(name = "nameRu")
    var nameRu: String,

    @ColumnInfo(name = "nameEn")
    var nameEn: String,

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

    @ColumnInfo(name = "posterUrlPreview")
    var posterUrlPreview: String
)
