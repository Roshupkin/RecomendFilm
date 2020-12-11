package android.watch_movie.cache.database

import android.watch_movie.cache.entity.RandomFilmCache
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RandomFilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRandomFilm(filmEntity: RandomFilmCache): Long

    @Query("SELECT * FROM random_film ORDER BY loadCount ")
    suspend fun getAllRandomFilms(): List<RandomFilmCache>

    @Query("DELETE FROM random_film")
    suspend fun deleteAllRandomFilms()

    @Query("DELETE from random_film WHERE loadCount IN (:loadCount)")
    suspend fun deletFilmsByLoadCount(loadCount: Int)
}