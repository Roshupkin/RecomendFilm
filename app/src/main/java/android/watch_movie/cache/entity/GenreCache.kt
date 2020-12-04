package android.watch_movie.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreCache(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idGenre")
    var idGenre: Int? = null,
    @ColumnInfo(name = "genre")
    var genre: String? = null
)