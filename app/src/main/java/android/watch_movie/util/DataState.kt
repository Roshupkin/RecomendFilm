package android.watch_movie.util

import retrofit2.HttpException

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data class HttpError(val httpException: HttpException) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}