package android.watch_movie.di

import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.mapper.FilmCacheMapper
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.api.TopFilmsGet
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
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
        topFilmsGet: TopFilmsGet,
        filmNetworkMapper: FilmNetworkMapper,
        filmsDao: FilmsDao,
        filmCacheMapper: FilmCacheMapper,
        topFilmsNetworkMapper: ListFilmsNetworkMapper

    ): FilmRepository {
        return FilmRepository(filmsDao, filmNetworkMapper, filmCacheMapper, topFilmsGet,topFilmsNetworkMapper)
    }


}