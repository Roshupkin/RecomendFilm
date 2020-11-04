package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.adapters.FilmListAdapter
import android.watch_movie.model.Film
import android.watch_movie.ui.viewmodel.RandomFilmStateEvent
import android.watch_movie.ui.viewmodel.RandomFilmViewModel
import android.watch_movie.util.DataState
import android.watch_movie.util.TopSpacingItemDecoratio
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_films.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomFilmFragment : Fragment(R.layout.random_fragment_film) {
    private val viewModule: RandomFilmViewModel by viewModels()
    lateinit var filmListAdapter: FilmListAdapter
    private val TAG = "RandomFilmFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModule.setStateEvent(RandomFilmStateEvent.ReplaceRandomEntity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscreibeObsirevers()
        viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
        initRecyclerView()
        Log.e(TAG, "onViewCreated")
    }


    private fun subscreibeObsirevers() {
        viewModule.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Film>> -> {
                    displayProgressBar(false)
                    filmListAdapter.submitList(dataState.data)
                    //         Log.e(TAG, "ERRORtype: ${dataState.data}")
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.HttpError -> {
                    viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
                    displayHttpEror(dataState.httpException.code())


                }
            }

        })

    }

    private fun displayError(message: String?) {
        if (message != null) {
            text.text = message
        } else
            text.text = "Unknown error"
        Log.e(TAG, "ERROR: $message")
    }

    private fun displayHttpEror(code: Int?) {
        when (code) {
            404 -> {/*Films are not found*/
            }
            401 -> {/*Wrong or empty access token*/
            }
            429 -> {/*Too Many Requests. Limit 10 req/sec*/
            }

        }

    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoratio = TopSpacingItemDecoratio(20)
            addItemDecoration(topSpacingItemDecoratio)
            filmListAdapter = FilmListAdapter()
            adapter = filmListAdapter
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility =
            if (isDisplayed) View.VISIBLE
            else View.GONE

    }


}