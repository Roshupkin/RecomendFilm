package android.watch_movie.di

import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.mapper.FilmCacheMapper
import android.watch_movie.cache.mapper.GenresCacheMapper
import android.watch_movie.cache.mapper.RandomFilmCacheMapper
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.mapper.FilterGCNetworkMapper
import android.watch_movie.network.mapper.GenreNetworkMapper
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.repository.FilmRepository
import android.watch_movie.repository.RandomFilmRepository
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
        filmsGet: FilmsApi,
        filmNetworkMapper: FilmNetworkMapper,
        filmsDao: FilmsDao,
        filmCacheMapper: FilmCacheMapper,
        listFilmsNetworkMapper: ListFilmsNetworkMapper

    ): FilmRepository = FilmRepository(
        filmsDao,
        filmNetworkMapper,
        filmCacheMapper,
        filmsGet,
        listFilmsNetworkMapper
    )

    @Singleton
    @Provides
    fun provideRandomFilmsRepository(
        filmsGet: FilmsApi,
        filmNetworkMapper: FilmNetworkMapper,
        filmsDao: FilmsDao,
        randomFilmCacheMapper: RandomFilmCacheMapper,
        listFilmsNetworkMapper: ListFilmsNetworkMapper,
        genresCacheMapper: GenresCacheMapper,
        genreNetworkMapper: GenreNetworkMapper,
        filterGCNetworkMapper: FilterGCNetworkMapper
    ): RandomFilmRepository = RandomFilmRepository(
        filmsDao,
        filmNetworkMapper,
        randomFilmCacheMapper,
        filmsGet,
        listFilmsNetworkMapper,
        genresCacheMapper,
        genreNetworkMapper, filterGCNetworkMapper
    )


}