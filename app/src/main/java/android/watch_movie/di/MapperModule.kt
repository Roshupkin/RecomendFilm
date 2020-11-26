package android.watch_movie.di

import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.mapper.GenreIDNetworkMapper
import android.watch_movie.network.mapper.GenreNetworkMapper
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
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
    fun providesFilmNetworkMapper(
        filmNetworkMapper: FilmNetworkMapper
    ): ListFilmsNetworkMapper = ListFilmsNetworkMapper(filmNetworkMapper)

    @Singleton
    @Provides
    fun providesGenreNetworkMapper(
        genreNetworkMapper: GenreNetworkMapper
    ): GenreIDNetworkMapper = GenreIDNetworkMapper(genreNetworkMapper)

}