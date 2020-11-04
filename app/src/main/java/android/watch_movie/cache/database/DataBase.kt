package android.watch_movie.cache.database

import android.watch_movie.cache.entity.FilmCacheEntity
import android.watch_movie.cache.entity.GenresCacheEntity
import android.watch_movie.cache.entity.ListFilmsCacheEntity
import android.watch_movie.cache.entity.RandomFilmCacheEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converters::class)
@Database(
    entities = [ListFilmsCacheEntity::class, FilmCacheEntity::class, GenresCacheEntity::class, RandomFilmCacheEntity::class],
    version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao

    companion object {
        const val DATABASE_NAME: String = "films_database"
    }


}