package android.watch_movie.repository

import android.util.Log
import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.database.ProfileDao
import android.watch_movie.cache.mapper.EvaluatedCacheMapper
import android.watch_movie.cache.mapper.FavoritesCacheMapper
import android.watch_movie.cache.mapper.TopFilmCacheMapper
import android.watch_movie.model.Film
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TopFilmRepository(
    private val filmsDao: FilmsDao,
    private val filmCacheMapper: TopFilmCacheMapper,
    private val filmsGet: FilmsApi,
    private val listFilmsNetworkMapper: ListFilmsNetworkMapper,
    private val profileDao: ProfileDao,
    private val favoritesCacheMapper: FavoritesCacheMapper,
    private val evaluatedCacheMapper: EvaluatedCacheMapper
) {
    private val TAG = "TopFilmRepository"
    suspend fun getTopFilms(): Flow<DataState<List<Film>>> = flow {
        emit(DataState.Loading)
        try {
            val networksFilms = filmsGet.getTop250Film()
            val listFilms = listFilmsNetworkMapper.mapFromEntity(networksFilms)
            for (film in listFilms.films!!) {
                filmsDao.insertFilm(filmCacheMapper.mapToEntity(film))
            }
            val cachedFilm = filmsDao.getAllBestFilms()
            emit(DataState.Success(filmCacheMapper.mapFromEntityList(cachedFilm)))

        } catch (e: Exception) {
            emit(DataState.Error(e.message.toString()))
            Log.e(TAG, "Hey look! $e")
        }
    }

    suspend fun saveFavorites(isSave: Boolean, item: Film) {
        if (isSave) {
            val favotritsFilmCache = favoritesCacheMapper.mapToEntity(item)
            profileDao.insertFavoritrsFilm(favotritsFilmCache)
        } else {
            val filmID = item.filmID
            profileDao.deleteFavoritesFilmsByID(filmID)
        }
    }

    suspend fun setEvaluated(item: Film) {
        val evaluatedFilm = evaluatedCacheMapper.mapToEntity(item)
        profileDao.insertEvaluated(evaluatedFilm)
    }
}