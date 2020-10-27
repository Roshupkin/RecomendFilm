package android.watch_movie.cache.database

import android.watch_movie.cache.entity.ListFilmsCacheEntity
import android.watch_movie.cache.entity.FilmCacheEntity
import androidx.room.*


@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(FilmsEntity: ListFilmsCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmEntity: FilmCacheEntity): Long

    @Query("SELECT * FROM listfilms")
    suspend fun getBestFilms(): List<ListFilmsCacheEntity>

    @Query("SELECT * FROM film")
    suspend fun getAllBestFilms(): List<FilmCacheEntity>
}

