package android.watch_movie.repository

import android.watch_movie.cache.database.ProfileDao
import android.watch_movie.cache.mapper.EvaluatedCacheMapper
import android.watch_movie.cache.mapper.FavoritesCacheMapper
import android.watch_movie.model.Film
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.util.DataState
import kotlinx.coroutines.flow.flow

class RequestIDRepository(
    private val profileDao: ProfileDao,
    private val favoritesCacheMapper: FavoritesCacheMapper,
    private val evaluatedCacheMapper: EvaluatedCacheMapper,
    private val filmGet: FilmsApi
) {


    suspend fun getFilmByID(_ID: Int)  = flow {
        val film = filmGet.getFilmByID(_ID)
        emit(DataState.Success(film))

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