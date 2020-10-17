package android.watch_movie.db.database

import android.watch_movie.db.BestFilmDao
import android.watch_movie.db.cacheentity.Best_FilmCacheEntity
import android.watch_movie.db.cacheentity.CountryCacheEntity
import android.watch_movie.db.cacheentity.Film_CacheEntity
import android.watch_movie.db.cacheentity.GenreCacheEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Best_FilmCacheEntity::class,Film_CacheEntity::class,CountryCacheEntity::class,GenreCacheEntity::class], version = 1)
abstract class Best_FilmDataBase : RoomDatabase() {

    abstract fun best_filmDao(): BestFilmDao

    companion object {
        val DATABASE_NAME: String = "bestfilm_db"
    }


}