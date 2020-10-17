package android.watch_movie.repository

import android.watch_movie.db.BestFilmDao
import android.watch_movie.db.mapp.Best_FilmCacheMapper
import android.watch_movie.model.Best_Film
import android.watch_movie.network.mapper.Best_FilmNetworkMapper
import android.watch_movie.network.retrofit.Best_FilmGet
import android.watch_movie.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Best_FilmRepository
constructor(
    private val bestFilmDao: BestFilmDao,
    private val bestfilmCacheMapper: Best_FilmCacheMapper,
    private val bestfilmGet: Best_FilmGet,
    private val bestFilmNetworkMapper: Best_FilmNetworkMapper
) {
    suspend fun getBest_Film(): Flow<DataState<Best_Film>> = flow {
        emit(DataState.Loading)
        try {
            /*val networkBest_Film = bestfilmGet.get()
            val Best_Film = bestFilmNetworkMapper.mapFromEntity(networkBest_Film)
            bestFilmDao.insert(bestfilmCacheMapper.mapToEntity(Best_Film))
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))*/
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
