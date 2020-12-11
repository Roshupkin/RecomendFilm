package android.watch_movie.di

import android.content.Context
import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.database.IDByFilterDao
import android.watch_movie.cache.database.ProfileDao
import android.watch_movie.cache.database.RandomFilmDao
import android.watch_movie.cache.mapper.*
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.mapper.GenresIDNetworkMapper
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.repository.DetaildFilmRepository
import android.watch_movie.repository.RandomFilmRepository
import android.watch_movie.repository.TopFilmRepository
import android.watch_movie.util.Constans
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
    fun provideTopFilmsRepository(
        filmsGet: FilmsApi,
        filmsDao: FilmsDao,
        filmCacheMapper: TopFilmCacheMapper,
        listFilmsNetworkMapper: ListFilmsNetworkMapper,
        profileDao: ProfileDao,
        favoritesCacheMapper: FavoritesCacheMapper,
        evaluatedCacheMapper: EvaluatedCacheMapper
    ): TopFilmRepository = TopFilmRepository(
        filmsDao,
        filmCacheMapper,
        filmsGet,
        listFilmsNetworkMapper,
        profileDao,
        favoritesCacheMapper,
        evaluatedCacheMapper

    )

    @Singleton
    @Provides
    fun provideRandomFilmsRepository(
        filmsGet: FilmsApi,
        filmNetworkMapper: FilmNetworkMapper,
        randomFilmCacheMapper: RandomFilmCacheMapper,
        listFilmsNetworkMapper: ListFilmsNetworkMapper,
        genresCacheMapper: GenresCacheMapper,
        filterGCNetworkMapper: GenresIDNetworkMapper,
        networkCheck: NetworkCheck,
        @ApplicationContext context: Context,
        IDByFilterDao: IDByFilterDao,
        randomFilmDao: RandomFilmDao,
        constans: Constans,
        profileDao: ProfileDao,
        favoritesCacheMapper: FavoritesCacheMapper,
        evaluatedCacheMapper: EvaluatedCacheMapper
    ): RandomFilmRepository = RandomFilmRepository(
        filmNetworkMapper,
        randomFilmCacheMapper,
        filmsGet,
        listFilmsNetworkMapper,
        genresCacheMapper,
        filterGCNetworkMapper,
        networkCheck,
        context,
        IDByFilterDao,
        randomFilmDao,
        constans,
        profileDao,
        favoritesCacheMapper,
        evaluatedCacheMapper
    )

    @Singleton
    @Provides
    fun proviedeDetailsFilmRepository(
        filmGet: FilmsApi,
        networkCheck: NetworkCheck,
        @ApplicationContext context: Context
    ): DetaildFilmRepository = DetaildFilmRepository(filmGet, networkCheck, context)


}