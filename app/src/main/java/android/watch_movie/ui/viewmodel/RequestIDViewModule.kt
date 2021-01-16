package android.watch_movie.ui.viewmodel

import android.watch_movie.model.Film
import android.watch_movie.repository.RequestIDRepository
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RequestIDViewModule
@ViewModelInject
constructor(
    private val requestIDRepository: RequestIDRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
): ViewModel(){





    fun setFavorites(isSave: Boolean, item: Film) {
        viewModelScope.launch { requestIDRepository.saveFavorites(isSave, item) }

    }

    fun setEvaluated(item: Film) {
        viewModelScope.launch { requestIDRepository.setEvaluated(item) }
    }

}