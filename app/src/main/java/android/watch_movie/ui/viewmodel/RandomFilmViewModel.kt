package android.watch_movie.ui.viewmodel

import android.watch_movie.model.Film
import android.watch_movie.repository.RandomFilmRepository
import android.watch_movie.ui.viewmodel.RandomFilmStateEvent.*
import android.watch_movie.util.DataState
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RandomFilmViewModel
@ViewModelInject
constructor(
    private val randomFilmRepository: RandomFilmRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val TAG = "RandomViewModule"
    private val _dataState: MutableLiveData<DataState<List<Film>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Film>>>
        get() = _dataState


    fun setStateEvent(randomFilmStateEvent: RandomFilmStateEvent) {
        viewModelScope.launch {
            when (randomFilmStateEvent) {
                is GetFilmsEvent -> {
                    randomFilmRepository.getRandomFilms()
                        .onEach { dataState ->
                            _dataState.value = dataState

                        }.launchIn(viewModelScope)
                }
                ReplaceRandomEntity -> {
                    randomFilmRepository.deletAllRandomFilms()
                }
                DeletFilmsCount -> {
                    randomFilmRepository.deletFilmsCount()
                }


            }
        }
    }

    fun setFavorites(isSave: Boolean, item: Film) {
        viewModelScope.launch { randomFilmRepository.saveFavorites(isSave, item) }

    }

    fun setEvaluated(item: Film) {
        viewModelScope.launch { randomFilmRepository.setEvaluated(item) }
    }


}

// MVI
sealed class RandomFilmStateEvent {
    object GetFilmsEvent : RandomFilmStateEvent()

    object ReplaceRandomEntity : RandomFilmStateEvent()

    object DeletFilmsCount : RandomFilmStateEvent()


}
