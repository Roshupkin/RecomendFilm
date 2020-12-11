package android.watch_movie.repository

import android.content.Context
import android.util.Log
import android.watch_movie.cache.database.IDByFilterDao
import android.watch_movie.cache.database.ProfileDao
import android.watch_movie.cache.database.RandomFilmDao
import android.watch_movie.cache.mapper.EvaluatedCacheMapper
import android.watch_movie.cache.mapper.FavoritesCacheMapper
import android.watch_movie.cache.mapper.GenresCacheMapper
import android.watch_movie.cache.mapper.RandomFilmCacheMapper
import android.watch_movie.model.Film
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.mapper.GenresIDNetworkMapper
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.util.Constans
import android.watch_movie.util.DataState
import android.watch_movie.util.NetworkCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class RandomFilmRepository(
    private val filmNetworkMapper: FilmNetworkMapper,
    private val randomFilmCacheMapper: RandomFilmCacheMapper,
    private val filmsGet: FilmsApi,
    private val listFilmsNetworkMapper: ListFilmsNetworkMapper,
    private val genresCacheMapper: GenresCacheMapper,
    private val genresIDNetworkMapper: GenresIDNetworkMapper,
    private val networkCheck: NetworkCheck,
    private val context: Context,
    private val IDByFilter: IDByFilterDao,
    private val randomFilmDao: RandomFilmDao,
    private val constans: Constans,
    private val profileDao: ProfileDao,
    private val favoritesCacheMapper: FavoritesCacheMapper,
    private val evaluatedCacheMapper: EvaluatedCacheMapper
) {
    private val TAG = "Random Repository"

    private val PAGE_NUMBER = 1 //Maybe add functional random page:   (1..5).random()
    private val TYPE = "FILM"//Maybe type (FILM, ALL, TV_SHOWS)
    private var loadCount = 0
    private var countDeleted = 0

    fun orderOfSorting(): String {
        val random = (1..2).random()
        if (random == 1)
            return "RATING"
        return "NUM_VOTE"
        //  Maybe add (return "YEAR")
    }

    suspend fun getRandomFilms(): Flow<DataState<List<Film>>> = flow {
        emit(DataState.Loading)
        try {
            /*Random variables to request a random movies*/
            val MAX_YEAR_RAND = (2017..2020).random()
            val MIN_YEAR_RAND = (1960 until MAX_YEAR_RAND).random()
            val GENRE_RAND = (1..18).random()
            val RATING_TO_RAND = (8..10).random()
            val RATING_FROM_RAND = (6 until RATING_TO_RAND).random()


            if (networkCheck.isNetworkAvailable(context)) {
                /*Is exists list genres*/
                if (!IDByFilter.isExists()) {
                    /*Gets genre and cached*/
                    val networkGenresID = filmsGet.getIDGenres()
                    // TODO: 11.12.2020

                    val listGenre = genresIDNetworkMapper.mapFromEntity(networkGenresID)
                    for (genre in listGenre.genres!!) {
                        IDByFilter.insertGenresFilter(genresCacheMapper.mapToEntity(genre))
                    }
                }
                /*Read genres list */
                val genresID = IDByFilter.getIdGenresFilter()[GENRE_RAND]
                val ganreID = genresCacheMapper.mapFromEntity(genresID).idGenre
                /*Gets list film by random parameters*/
                val networkRandomFilm = filmsGet.getFilmByFilters(
                    genre = ganreID,
                    order = orderOfSorting(),
                    type = TYPE,
                    ratingFrom = RATING_FROM_RAND,
                    ratingTo = RATING_TO_RAND,
                    yearFrom = MIN_YEAR_RAND,
                    yearTo = MAX_YEAR_RAND,
                    page = PAGE_NUMBER
                )
                val listRandomFilms =
                    listFilmsNetworkMapper.mapFromEntity(networkRandomFilm)
                /*Counts number of loaded requests*/
                loadCount++
                /*Gets details to find description and cached it*/
                for (randomFilm in listRandomFilms.films!!) {
                    val detaildFilmNetwork = filmsGet.getFilmByID(randomFilm.filmID)
                    val detaildFilm =
                        filmNetworkMapper.mapFromEntity(detaildFilmNetwork.dataFilm!!)
                    detaildFilm.loadCount = loadCount
                    detaildFilm.rating = randomFilm.rating
                    val detaildFilmCache =
                        randomFilmCacheMapper.mapToEntity(detaildFilm)
                    randomFilmDao.insertRandomFilm(detaildFilmCache)
                }

                val cachedDetaildFilms = randomFilmDao.getAllRandomFilms()
                val detaildFilms =
                    randomFilmCacheMapper.mapFromEntityList(cachedDetaildFilms)

                emit(DataState.Success(detaildFilms))

            } else {
                Log.e(TAG, "Exception No internet connection ")
                emit(DataState.Error("No internet connection"))
            }

        } catch (e: HttpException) {
            Log.e(TAG, "HTTPException message: ${e.message}   code: ${e.stackTrace}")
            emit(DataState.HttpError(e))

        } catch (e: Exception) {
            randomFilmDao.deleteAllRandomFilms()
            Log.e(TAG, "Exception message: ${e.message}")
            Log.e(TAG, "Exception message: ${e.stackTraceToString()}")
            when (e) {
                is IOException -> emit(DataState.Error("Network Failure"))
                else -> emit(DataState.Error("Conversion Error"))
            }
        }
    }

    suspend fun deletAllRandomFilms() {
        randomFilmDao.deleteAllRandomFilms()
    }

    /*Deleted film by counter deleted*/
    suspend fun deletFilmsCount() {
        countDeleted++
        randomFilmDao.deletFilmsByLoadCount(countDeleted)

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
        val evaluatedFilm= evaluatedCacheMapper.mapToEntity(item)
        profileDao.insertEvaluated(evaluatedFilm)
    }

}