package android.watch_movie.cache.database

import android.watch_movie.cache.entity.GenresCache
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IdForFilterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresFilter(genresCacheEntity: GenresCache): Long

    @Query("SELECT * FROM genres ")
    suspend fun getIdGenresFilter(): List<GenresCache>
}