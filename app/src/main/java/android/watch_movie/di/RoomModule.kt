package android.watch_movie.di

import android.content.Context
import android.watch_movie.cache.database.BestFilmDao
import android.watch_movie.cache.database.Best_FilmDataBase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideBest_FilmDb(@ApplicationContext context: Context): Best_FilmDataBase {
        return Room.databaseBuilder(
            context, Best_FilmDataBase::class.java,
            Best_FilmDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideBest_FilmDAO(best_filmDataBase: Best_FilmDataBase): BestFilmDao {
        return best_filmDataBase.bestfilmDao()
    }


}