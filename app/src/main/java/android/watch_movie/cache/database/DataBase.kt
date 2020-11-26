package android.watch_movie.cache.database

import android.watch_movie.cache.entity.GenresCache
import android.watch_movie.cache.entity.ListFilmsCache
import android.watch_movie.cache.entity.RandomFilmCache
import android.watch_movie.cache.entity.TopFilmCache
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converters::class)
@Database(
    entities = [ListFilmsCache::class, TopFilmCache::class, GenresCache::class, RandomFilmCache::class],
    version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao
    abstract fun randomFilmDao(): RandomFilmDao
    abstract fun idForFilterDao(): IdForFilterDao

    companion object {
        const val DATABASE_NAME: String = "films_database"
    }


}