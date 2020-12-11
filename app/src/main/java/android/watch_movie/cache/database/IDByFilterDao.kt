package android.watch_movie.cache.database

import android.watch_movie.cache.entity.GenresIDCache
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IDByFilterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresFilter(genresIDCacheEntity: GenresIDCache): Long

    @Query("SELECT * FROM genres ")
    suspend fun getIdGenresFilter(): List<GenresIDCache>

    @Query("SELECT EXISTS(SELECT * FROM genres)")
    suspend fun isExists(): Boolean

}