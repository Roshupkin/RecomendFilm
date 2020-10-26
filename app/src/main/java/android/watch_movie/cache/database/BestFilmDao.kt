package android.watch_movie.cache.database

import android.watch_movie.cache.entity.Best_FilmCacheEntity
import android.watch_movie.cache.entity.FilmCacheEntity
import androidx.room.*


@Dao
interface BestFilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBestFilm(bestFilmEntity: Best_FilmCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmEntity: FilmCacheEntity): Long

    @Query("SELECT * FROM best_film")
    suspend fun getBestFilms(): List<Best_FilmCacheEntity>

    @Query("SELECT * FROM films")
    suspend fun getAllBestFilms(): List<FilmCacheEntity>
}

