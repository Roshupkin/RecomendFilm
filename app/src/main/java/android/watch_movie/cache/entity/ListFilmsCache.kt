package android.watch_movie.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "listfilms", primaryKeys = arrayOf("id", "pagesCount"))
data class ListFilmsCache(
    /*@PrimaryKey(autoGenerate = true)*/
    val id: Int = 1,
    @ColumnInfo(name = "pagesCount")
    val pagesCount: Int
)