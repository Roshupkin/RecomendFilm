package android.watch_movie.di


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
    fun providesGenresIDNetworkMapper(
        genreIDNetworkMapper: GenreIDNetworkMapper
    ): GenresIDNetworkMapper = GenresIDNetworkMapper(genreIDNetworkMapper)

   @Singleton
    @Provides
    fun providesFilmNetworkMapper(
        genreNetworkMapper: GenreNetworkMapper
    ): FilmNetworkMapper= FilmNetworkMapper(genreNetworkMapper)

}