package android.watch_movie.ui.viewmodel


import android.util.Log
import android.watch_movie.network.entity.DetailFilmEntity
import android.watch_movie.repository.DetailFilmRepository
import android.watch_movie.ui.viewmodel.DetailFilmStateEvent.*
import android.watch_movie.util.DataState
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class DetailFilmViewModel
@ViewModelInject
constructor(
    private val detailFilmRepository: DetailFilmRepository,
) : ViewModel() {
    private var TAG = "DetailFilmViewModel"
    private val _dataState: MutableLiveData<DataState<DetailFilmEntity>> = MutableLiveData()
    val dataState: LiveData<DataState<DetailFilmEntity>>
        get() = _dataState

    /*val selected = MutableLiveData<Int>()

    fun select(item: Int) {
        selected.value = item
        }*/


var id:Int?=301

    fun setStateEvent(filmStateEvent: DetailFilmStateEvent) {

        Log.e(TAG, id.toString())
        viewModelScope.launch {
            when (filmStateEvent) {
                is GetFilmsEvent -> {
                    Log.e(TAG, id.toString())
                    detailFilmRepository.getDetailsFilm(id)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                None -> {
                }
                SetFilmId -> {

                }

            }
        }
    }


}

sealed class DetailFilmStateEvent {
    object GetFilmsEvent : DetailFilmStateEvent()
    object SetFilmId : DetailFilmStateEvent()
    object None : DetailFilmStateEvent()
}
