package android.watch_movie.cache.database

import android.watch_movie.cache.entity.FilmCacheEntity
import android.watch_movie.cache.entity.ListFilmsCacheEntity
import android.watch_movie.cache.entity.GenresCacheEntity
import android.watch_movie.cache.entity.RandomFilmCacheEntity
import androidx.room.*


@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(FilmsEntity: ListFilmsCacheEntity): Long
    @Query("SELECT * FROM listfilms")
    suspend fun getBestFilms(): List<ListFilmsCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmEntity: FilmCacheEntity): Long
    @Query("SELECT * FROM film")
    suspend fun getAllBestFilms(): List<FilmCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresFilter(genresCacheEntity: GenresCacheEntity): Long
    @Query("SELECT * FROM genres ")
    suspend fun getIdGenresFilter(/*id:Int*/):List< GenresCacheEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRandomFilm(filmEntity: RandomFilmCacheEntity): Long
    @Query("SELECT * FROM randomFilm ORDER BY countFilm ")
    suspend fun getAllRandomFilms(): List<RandomFilmCacheEntity>
    @Query("DELETE FROM randomFilm")
    suspend fun deleteAllRandomFilms()

}

