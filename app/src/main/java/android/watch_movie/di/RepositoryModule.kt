package android.watch_movie.di

import android.watch_movie.cache.database.BestFilmDao
import android.watch_movie.cache.mapper.FilmCacheMapper
import android.watch_movie.network.mapper.Best_FilmNetworkMapper
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.api.Best_FilmGet
import android.watch_movie.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideFilmsRepository(
        best_FilmGet: Best_FilmGet,
        filmNetworkMapper: FilmNetworkMapper,
        bestFilmDao: BestFilmDao,
        filmCacheMapper: FilmCacheMapper,
        bestFilmNetworkMapper:Best_FilmNetworkMapper

    ): FilmRepository {
        return FilmRepository(bestFilmDao, filmNetworkMapper, filmCacheMapper, best_FilmGet,bestFilmNetworkMapper)
    }


}