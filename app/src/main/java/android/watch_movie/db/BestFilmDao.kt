package android.watch_movie.db

import android.watch_movie.db.cacheentity.Best_FilmCacheEntity
import android.watch_movie.db.onetomany.Best_FilmWithFilm
import androidx.room.*


@Dao
    interface  BestFilmDao{
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(best_filmEntity: Best_FilmCacheEntity): Long

        @Transaction
        @Query("SELECT * FROM best_film")
        suspend fun getBestFilmWithFilm(): Best_FilmWithFilm
    }

