package android.watch_movie.cache.database

import android.watch_movie.cache.entity.Best_FilmCacheEntity
import android.watch_movie.cache.entity.FilmCacheEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converters::class)
@Database(entities = [Best_FilmCacheEntity::class,FilmCacheEntity::class], version = 1)
abstract class Best_FilmDataBase : RoomDatabase() {

    abstract fun bestfilmDao(): BestFilmDao

    companion object {
        const val DATABASE_NAME: String = "bestfilm_db"
    }


}