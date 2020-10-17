package android.watch_movie.db

import android.watch_movie.db.cacheentity.Best_FilmCacheEntity
import android.watch_movie.db.cacheentity.Film_CacheEntity
import android.watch_movie.db.onetomany.Best_FilmWithFilm
import androidx.room.*

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmEntity: Film_CacheEntity): Long

    @Transaction
    @Query("SELECT * FROM films")
    suspend fun getFilm(): Film_CacheEntity
}