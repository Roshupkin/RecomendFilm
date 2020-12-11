package android.watch_movie.ui.viewmodel


import android.content.ClipData.Item
import android.watch_movie.network.entity.DetaildFilmEntity
import android.watch_movie.repository.DetaildFilmRepository
import android.watch_movie.ui.viewmodel.DetaildFilmStateEvent.GetFilmsEvent
import android.watch_movie.util.DataState
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class DetaildFilmViewModel
@ViewModelInject
constructor(
    private val detaildFilmRepository: DetaildFilmRepository,
) : ViewModel() {
    private var TAG = "DetaildFilmViewModel"
    private val _dataState: MutableLiveData<DataState<DetaildFilmEntity>> = MutableLiveData()
    val dataState: LiveData<DataState<DetaildFilmEntity>>
        get() = _dataState



    var filmID: Int? = 301

    fun setStateEvent(filmStateEvent: DetaildFilmStateEvent) {

        viewModelScope.launch {
            when (filmStateEvent) {
                is GetFilmsEvent -> {
                    detaildFilmRepository.getDetailsFilm(filmID)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }

            }
        }
    }



}

//MVI
sealed class DetaildFilmStateEvent {
    object GetFilmsEvent : DetaildFilmStateEvent()
}
