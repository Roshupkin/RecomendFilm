package android.watch_movie.di

import android.watch_movie.cache.mapper.*
import android.watch_movie.network.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MapperModule {
    @Singleton
    @Provides
    fun providesListFilmsNetworkMapper(
        filmNetworkMapper: FilmNetworkMapper
    ): ListFilmsNetworkMapper = ListFilmsNetworkMapper(filmNetworkMapper)

    @Singleton
    @Provides
    fun providesGenreIDNetworkMapper(
        genrIDNetworkMapper: GenrIDNetworkMapper
    ): GenreIDNetworkMapper = GenreIDNetworkMapper(genrIDNetworkMapper)

   @Singleton
    @Provides
    fun providesFilmNetworkMapper(
        genreNetworkMapper: GenreNetworkMapper
    ): FilmNetworkMapper= FilmNetworkMapper(genreNetworkMapper)

    @Singleton
    @Provides
    fun providesFilmCacheMapper(
        genreCacheMapper: GenreCacheMapper
    ): FilmCacheMapper = FilmCacheMapper(genreCacheMapper)
    @Singleton
    @Provides
    fun providesRandomFilmCacheMapper(
        genreCacheMapper: GenreCacheMapper
    ): RandomFilmCacheMapper= RandomFilmCacheMapper( genreCacheMapper)

    @Singleton
    @Provides
    fun providesTopFilmCacheMapper(
        genreCacheMapper: GenreCacheMapper
    ): TopFilmCacheMapper= TopFilmCacheMapper( genreCacheMapper)
}