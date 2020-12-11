package android.watch_movie.cache.database

import android.watch_movie.cache.entity.TopFilmCache
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmEntity: TopFilmCache): Long

    @Query("SELECT * FROM top_film")
    suspend fun getAllBestFilms(): List<TopFilmCache>

}

