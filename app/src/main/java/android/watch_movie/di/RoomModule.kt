package android.watch_movie.di

import android.content.Context
import android.watch_movie.cache.database.*
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
    fun provideDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context, DataBase::class.java,
            DataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFilmsDAO(dataBase: DataBase): FilmsDao = dataBase.filmsDao()

    @Singleton
    @Provides
    fun provideRandomFilmDAO(dataBase: DataBase): RandomFilmDao = dataBase.randomFilmDao()

    @Singleton
    @Provides
    fun provideIDByFilterDAO(dataBase: DataBase): IDByFilterDao = dataBase.iDByFilterDao()

    @Singleton
    @Provides
    fun provideProfileDAO(dataBase: DataBase): ProfileDao = dataBase.profileDao()

}