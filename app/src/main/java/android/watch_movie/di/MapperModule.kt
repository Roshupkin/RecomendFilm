package android.watch_movie.di

import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.network.mapper.FilmNetworkMapper
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
    fun providesBestFilmNetworkMapper(
        filmNetworkMapper: FilmNetworkMapper
    ): ListFilmsNetworkMapper = ListFilmsNetworkMapper(filmNetworkMapper)
}