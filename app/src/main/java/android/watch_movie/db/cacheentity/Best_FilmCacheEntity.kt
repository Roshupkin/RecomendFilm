package android.watch_movie.db.cacheentity

import android.watch_movie.model.Film
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "best_film")
data class Best_FilmCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pagesCount")
    var pagesCount: Int
)