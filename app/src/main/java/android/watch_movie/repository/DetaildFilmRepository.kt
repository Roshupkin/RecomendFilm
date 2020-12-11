package android.watch_movie.repository

import android.content.Context
import android.util.Log
import android.watch_movie.network.api.FilmsApi
import android.watch_movie.util.DataState
import android.watch_movie.util.NetworkCheck
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DetaildFilmRepository(
    private val filmsApi: FilmsApi,
    private val networkCheck: NetworkCheck,
    private val context: Context,
) {
    private val TAG = "DetailFilmRepository"
    suspend fun getDetailsFilm(id: Int?) = flow {
        emit(DataState.Loading)

        try {
            Log.e(TAG,"$id")
            if (networkCheck.isNetworkAvailable(context)) {
                /*Get film by id */
                val film = filmsApi.getAllInfoFilm(id)
                emit(DataState.Success(film))
            } else {
                emit(DataState.Error("No internet connection"))
                Log.e(TAG, "Exception No internet connection ")
            }

        } catch (e: HttpException) {
            emit(DataState.HttpError(e))
            Log.e(TAG, "HTTPException message: ${e.message}   code: ${e.stackTrace}")
        } catch (e: Exception) {
            Log.e(TAG, "Exception message: ${e.message}")
            when (e) {
                is IOException -> emit(DataState.Error("Network Failure"))
                else -> {
                    emit(DataState.Error("Conversion Error"))
                    Log.e(TAG,e.toString())
                }
            }
        }


    }
}
