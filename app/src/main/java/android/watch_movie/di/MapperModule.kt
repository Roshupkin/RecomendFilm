package android.watch_movie.di

import android.watch_movie.network.mapper.Best_FilmNetworkMapper
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
    ): Best_FilmNetworkMapper = Best_FilmNetworkMapper(filmNetworkMapper)
}