package android.watch_movie.repository

import android.util.Log
import android.watch_movie.cache.database.BestFilmDao
import android.watch_movie.cache.mapper.FilmCacheMapper
import android.watch_movie.model.Film
import android.watch_movie.network.mapper.Best_FilmNetworkMapper
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.api.Best_FilmGet
import android.watch_movie.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FilmRepository(
    private val bestFilmDao: BestFilmDao,
    private val filmNetworkMapper: FilmNetworkMapper,
    private val filmCacheMapper: FilmCacheMapper,
    private val bestFilmget: Best_FilmGet,
    private val bestFilmNetworkMapper: Best_FilmNetworkMapper
) {
    private val TAG = "Repository"
    suspend fun getFilms(): Flow<DataState<List<Film>>> = flow {
        emit(DataState.Loading)
        try {
            val networksFilm = bestFilmget.get()
            Log.e(TAG, "Hey look! NetworksFilm $networksFilm")

            val bestFilm = bestFilmNetworkMapper.mapFromEntity(networksFilm)
            Log.e(TAG, "Hey look! bestFilm ${bestFilm.films}")

            for (film in bestFilm.films!!) {
                Log.e(TAG, "Hey look!film BEFOR DAO $film")
                bestFilmDao.insertFilm(filmCacheMapper.mapToEntity(film))
                Log.e(TAG, "Hey look!film AFTER DAO $film")
            }
            val cachedFilm = bestFilmDao.getAllBestFilms()
            Log.e(TAG, "Hey look!cachedFilm $cachedFilm")
            emit(DataState.Success(filmCacheMapper.mapFromEntityList(cachedFilm)))


        } catch (e: Exception) {
            emit(DataState.Error(e))
            Log.e(TAG, "Hey look! $e")
        }
    }


}