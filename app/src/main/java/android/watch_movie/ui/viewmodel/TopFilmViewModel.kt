package android.watch_movie.ui.viewmodel

import android.watch_movie.model.Film
import android.watch_movie.repository.TopFilmRepository
import android.watch_movie.ui.viewmodel.FilmStateEvent.GetFilmsEvent
import android.watch_movie.ui.viewmodel.FilmStateEvent.None
import android.watch_movie.util.DataState
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class FilmViewModule
@ViewModelInject
constructor(
    private val topFilmRepository: TopFilmRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Film>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Film>>>
        get() = _dataState


    fun setStateEvent(filmStateEvent: FilmStateEvent) {
        viewModelScope.launch {
            when (filmStateEvent) {
                is GetFilmsEvent -> {
                    topFilmRepository.getTopFilms()
                        .onEach { dataState ->
                            _dataState.value = dataState

                        }.launchIn(viewModelScope)
                }
                None -> {

                }

            }
        }
    }
    fun setSaveFavorites(isSave: Boolean, item: Film) {
        viewModelScope.launch { topFilmRepository.saveFavorites(isSave, item) }

    }

    fun setEvaluated(item: Film) {
        viewModelScope.launch { topFilmRepository.setEvaluated(item) }
    }

}
//MVI
sealed class FilmStateEvent {
    object GetFilmsEvent : FilmStateEvent()

    object None : FilmStateEvent()
}