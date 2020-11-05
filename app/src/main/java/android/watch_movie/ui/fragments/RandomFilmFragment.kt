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
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                    //   Log.e(TAG, "ERRORtype: ${filmListAdapter.itemCount}")
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.HttpError -> {
                    displayProgressBar(false)

                    viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
                    displayHttpError(dataState.httpException.code())
                    displayError(dataState.httpException.message)


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

    private fun displayHttpError(code: Int?) {
        when (code) {
            401 -> {/*Wrong or empty access token*/
            }
            404 -> {/*Films are not found*/
            }
            429 -> {/*Too Many Requests. Limit 10 req/sec*/
            }
            500 -> {/*Exception on server*/
            }

        }

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            isLoading = true
            progress_bar.visibility = View.VISIBLE
        } else {
            isLoading = false
            progress_bar.visibility = View.GONE
        }
    }

    var isLoading = false
    var isScrolling = false
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoading = !isLoading
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20
            val shouldPaginate = isNotLoading && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
                isScrolling = false
                Log.e(TAG, "SCROLLING!!")
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }


        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoratio = TopSpacingItemDecoratio(20)
            addItemDecoration(topSpacingItemDecoratio)
            addOnScrollListener(this@RandomFilmFragment.scrollListener)
            filmListAdapter = FilmListAdapter()
            adapter = filmListAdapter

        }
    }

}


