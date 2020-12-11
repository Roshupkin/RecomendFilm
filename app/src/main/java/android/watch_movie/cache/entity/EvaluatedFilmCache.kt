package android.watch_movie.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evaluated_film")
data class EvaluatedFilmCache(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "filmID")
    var filmID: Int,

    @ColumnInfo(name = "nameRu")
    var nameRu: String? = null,

    @ColumnInfo(name = "nameEn")
    var nameEn: String? = null,

    @ColumnInfo(name = "rating")
    var rating: String? = null,

    @ColumnInfo(name = "posterUrl")
    var posterUrl: String? = null,

    @ColumnInfo(name = "evaluated")
    var evaluated:Int? = null
    )