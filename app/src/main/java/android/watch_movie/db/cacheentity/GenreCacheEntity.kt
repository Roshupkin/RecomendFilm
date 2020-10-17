package android.watch_movie.db.cacheentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreCacheEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var filmId:Int = 0,
    @ColumnInfo(name = "genre")
    var genre: String

)