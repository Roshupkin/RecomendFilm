package android.watch_movie.repository

import android.content.Context
import android.util.Log
import android.watch_movie.cache.database.FilmsDao
import android.watch_movie.cache.database.IdForFilterDao
import android.watch_movie.cache.database.RandomFilmDao
import android.watch_movie.cache.mapper.GenresCacheMapper
import android.watch_movie.cache.mapper.RandomFilmCacheMapper
import android.watch_movie.model.Film
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.network.mapper.FilmNetworkMapper
import android.watch_movie.network.mapper.GenreIDNetworkMapper
import android.watch_movie.network.mapper.GenreNetworkMapper
import android.watch_movie.network.mapper.ListFilmsNetworkMapper
import android.watch_movie.util.DataState
import android.watch_movie.util.NetworkCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class RandomFilmRepository(
    private val filmsDao: FilmsDao,
    private val filmNetworkMapper: FilmNetworkMapper,
    private val randomFilmCacheMapper: RandomFilmCacheMapper,
    private val filmsGet: FilmsApi,
    private val listFilmsNetworkMapper: ListFilmsNetworkMapper,
    private val genresCacheMapper: GenresCacheMapper,
    private val genreNetworkMapper: GenreNetworkMapper,
    private val genreIDNetworkMapper: GenreIDNetworkMapper,
    private val networkCheck: NetworkCheck,
    private val context: Context,
    private val idForFilter: IdForFilterDao,
    private val randomFilmDao: RandomFilmDao,
) {
    private val TAG = "Random Repository"

    private val PAGE_NUMBER = 1 /*(1..5).random()*/
    private val TYPE = "FILM"//type FILM, ALL, TV_SHOWS
    private var count = 0
    private var countDel = 1

    fun orderOfSortingString(): String {
        val random = (1..2).random()
        //  Log.e(TAG, "random: $random")
        if (random == 1)
            return "RATING"
        return "NUM_VOTE"
        //   return "YEAR"
    }

    suspend fun getRandomFilms(): Flow<DataState<List<Film>>> = flow {
        emit(DataState.Loading)
        try {
            val MAX_YEAR_RAND = (2017..2020).random()
            val MIN_YEAR_RAND = (1960 until MAX_YEAR_RAND).random()
            val GENRE_RAND = (1..19).random()
            val RATING_TO_RAND = (8..10).random()
            val RATING_FROM_RAND = (6 until RATING_TO_RAND).random()

            /*  Log.e(TAG, "ORDER_OF_SORTING_RAND!: ${orderOfSortingString()}")
                 Log.e(TAG, "RATING_FROM_RAND!: $RATING_FROM_RAND")
                 Log.e(TAG, "RATING_FROM_RAND!: $RATING_TO_RAND")
                 Log.e(TAG, "MIN_YEAR_RAND!: $MIN_YEAR_RAND")
                 Log.e(TAG, "MAX_YEAR_RAND!: $MAX_YEAR_RAND")
                 Log.e(TAG, "PAGE_NUMBER_RAND!: $PAGE_NUMBER")
                 Log.e(TAG, "GENRE_RAND!: $GENRE_RAND")*/

            if (networkCheck.isNetworkAvailable(context)) {
                val networkFilms = filmsGet.getGenres()
                val listGenre = genreIDNetworkMapper.mapFromEntity(networkFilms)
                for (genre in listGenre.genres!!) {
                    idForFilter.insertGenresFilter(genresCacheMapper.mapToEntity(genre))
                }
                val randomGaere = idForFilter.getIdGenresFilter()[GENRE_RAND - 1]

                val randomGaereId = genresCacheMapper.mapFromEntity(randomGaere).idGenre
                //  Log.e(TAG, "randomGaereId $randomGaereId")
                val networkRandomFilm = filmsGet.getFiltersFilm(
                    genre = randomGaereId,
                    order = orderOfSortingString(),
                    type = TYPE,
                    ratingFrom = RATING_FROM_RAND,
                    ratingTo = RATING_TO_RAND,
                    yearFrom = MIN_YEAR_RAND,
                    yearTo = MAX_YEAR_RAND,
                    page = PAGE_NUMBER
                )
                val listRandomFilms =
                    listFilmsNetworkMapper.mapFromEntity(networkRandomFilm)

                count++
                for (randomFilm in listRandomFilms.films!!) {
                    randomFilm.countFilm = count
                    val randomFilmCacheEntity = randomFilmCacheMapper.mapToEntity(randomFilm)
                    randomFilmDao.insertRandomFilm(randomFilmCacheEntity)
                }
                val cachedRandomFilm = randomFilmDao.getAllRandomFilms()
                val randomFilms = randomFilmCacheMapper.mapFromEntityList(cachedRandomFilm)
                emit(DataState.Success(randomFilms))

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
            when (e) {
                is IOException -> emit(DataState.Error("Network Failure"))
                else -> emit(DataState.Error("Conversion Error"))
            }
        }
    }

    suspend fun delitAllRandomFilms() {
        randomFilmDao.deleteAllRandomFilms()
    }

    suspend fun delitFilmsCount() {
        randomFilmDao.delitFilmsForId(countDel)
        countDel++
        Log.e(TAG, "$countDel")
    }





}