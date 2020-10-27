package android.watch_movie.repository

import android.util.Log
import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.mapper.FilmCacheMapper
import android.watch_movie.model.Film
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.api.TopFilmsGet
import android.watch_movie.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FilmRepository(
    private val filmsDao: FilmsDao,
    private val filmNetworkMapper: FilmNetworkMapper,
    private val filmCacheMapper: FilmCacheMapper,
    private val topFilmsGet: TopFilmsGet,
    private val listFilmsNetworkMapper: ListFilmsNetworkMapper
) {
    private val TAG = "Repository"
    suspend fun getFilms(): Flow<DataState<List<Film>>> = flow {
        emit(DataState.Loading)
        try {
            val networksFilms = topFilmsGet.get()
            val listFilms = listFilmsNetworkMapper.mapFromEntity(networksFilms)
            for (film in listFilms.films!!) {
                filmsDao.insertFilm(filmCacheMapper.mapToEntity(film))
            }
            val cachedFilm = filmsDao.getAllBestFilms()
            emit(DataState.Success(filmCacheMapper.mapFromEntityList(cachedFilm)))


        } catch (e: Exception) {
            emit(DataState.Error(e))
            Log.e(TAG, "Hey look! $e")
        }
    }


}