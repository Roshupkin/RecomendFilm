package android.watch_movie.di

import android.watch_movie.db.BestFilmDao
import android.watch_movie.db.mapp.Best_FilmCacheMapper
import android.watch_movie.network.mapper.Best_FilmNetworkMapper
import android.watch_movie.network.retrofit.Best_FilmGet
import android.watch_movie.repository.Best_FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Best_FilmRepositoryModule {

    @Singleton
    @Provides
    fun provideBest_FilmRepository(
        bestFilmDao: BestFilmDao,
        cacheMapper: Best_FilmCacheMapper,
        bestFilmGet: Best_FilmGet,
        bestFilmNetworkMapper: Best_FilmNetworkMapper
    ):Best_FilmRepository{
        return Best_FilmRepository(bestFilmDao,cacheMapper,bestFilmGet,bestFilmNetworkMapper)

    }

}