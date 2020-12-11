package android.watch_movie.cache.database

import android.watch_movie.cache.entity.EvaluatedFilmCache
import android.watch_movie.cache.entity.FavoritesFilmCache
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvaluated(evaluated:EvaluatedFilmCache)

    @Query("SELECT * FROM evaluated_film")
    suspend fun getEvaluatedFilms():List<EvaluatedFilmCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritrsFilm(favoritesFilm: FavoritesFilmCache)

    @Query("SELECT * FROM favorites_film")
    suspend fun getFavoritesFilms():List<FavoritesFilmCache>

    @Query("DELETE FROM favorites_film WHERE filmID in (:filmID) ")
    suspend fun  deleteFavoritesFilmsByID(filmID:Int)

}