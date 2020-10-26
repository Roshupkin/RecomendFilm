package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.model.Film
import android.watch_movie.ui.viewmodel.FilmStateEvent
import android.watch_movie.ui.viewmodel.FilmViewModule
import android.watch_movie.util.DataState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_films.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FilmFragment
constructor(
    private val someString: String
) : Fragment(R.layout.fragment_films) {
    private val viewModel: FilmViewModule by viewModels()
    private val TAG = "FilmFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        viewModel.setStateEvent(FilmStateEvent.GetFilmsEvent)

        Log.d(TAG, "Hey look! $someString")
    }

    private fun subscribeObservers() {

        viewModel.dataState.observe(viewLifecycleOwner,  { dataState ->
            when (dataState) {
                is DataState.Success<List<Film>> -> {
                    displayProgressBar(false)
                    appendFilmTitles(dataState.data)
                    Log.e(TAG, "ERRORtype: ${dataState.data}")
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)

                }

            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null)
            text.text = message
        else
            text.text = "Unknown error"
        Log.e(TAG, "ERROR: $message")
    }

    private fun appendFilmTitles(films: List<Film>) {
        val sb = StringBuilder()
        for (film in films) {
            sb.append(film.nameRu + "\n")
        }
        text.text = sb.toString()

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility =
            if (isDisplayed) View.VISIBLE
            else View.GONE

    }


}