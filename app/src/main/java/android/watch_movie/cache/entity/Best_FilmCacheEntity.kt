package android.watch_movie.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "best_film", primaryKeys = arrayOf("id", "pagesCount"))
data class Best_FilmCacheEntity(
    /*@PrimaryKey(autoGenerate = true)*/
    val id: Int = 1,
    @ColumnInfo(name = "pagesCount")
    val pagesCount: Int
)