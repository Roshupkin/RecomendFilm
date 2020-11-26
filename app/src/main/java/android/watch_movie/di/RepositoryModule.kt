package android.watch_movie.di

import android.content.Context
import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.database.IdForFilterDao
import android.watch_movie.cache.database.RandomFilmDao
import android.watch_movie.cache.mapper.GenresCacheMapper
import android.watch_movie.cache.mapper.RandomFilmCacheMapper
import android.watch_movie.cache.mapper.TopFilmCacheMapper
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.mapper.GenreIDNetworkMapper
import android.watch_movie.network.mapper.GenreNetworkMapper
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.repository.DetailFilmRepository
import android.watch_movie.repository.FilmRepository
import android.watch_movie.repository.RandomFilmRepository
import android.watch_movie.ui.fragments.DetailFilmFragment
import android.watch_movie.util.NetworkCheck
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
        filmCacheMapper: TopFilmCacheMapper,
        listFilmsNetworkMapper: ListFilmsNetworkMapper,

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
        filterGCNetworkMapper: GenreIDNetworkMapper,
        networkCheck: NetworkCheck,
        @ApplicationContext context: Context,
        idForFilterDao: IdForFilterDao,
        randomFilmDao: RandomFilmDao,
    ): RandomFilmRepository = RandomFilmRepository(
        filmsDao,
        filmNetworkMapper,
        randomFilmCacheMapper,
        filmsGet,
        listFilmsNetworkMapper,
        genresCacheMapper,
        genreNetworkMapper,
        filterGCNetworkMapper,
        networkCheck,
        context,
        idForFilterDao,
        randomFilmDao
    )

    @Singleton
    @Provides
    fun proviedeDetailsFilmRepository(
        filmGet: FilmsApi,
        networkCheck: NetworkCheck,
        @ApplicationContext context: Context
    ): DetailFilmRepository = DetailFilmRepository(filmGet, networkCheck, context)


}