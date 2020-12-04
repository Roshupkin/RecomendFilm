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
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_films.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomFilmFragment : Fragment(R.layout.random_fragment_film), FilmListAdapter.Interaction {
    private val TAG = "RandomFilmFragment"
    private val viewModule: RandomFilmViewModel by viewModels()
    lateinit var filmListAdapter: FilmListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModule.setStateEvent(RandomFilmStateEvent.ReplaceRandomEntity)
        viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscreibeObsirevers()
        initRecyclerView()
        Log.e(TAG, "onViewCreated")
    }

    private fun subscreibeObsirevers() {
        viewModule.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Film>> -> {
                    displayProgressBar(false)
                    isLoading = false
                    filmListAdapter.submitList(dataState.data)
                    //   Log.e(TAG, "ERRORtype: ${filmListAdapter.itemCount}")
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    isLoading = true
                }
                is DataState.HttpError -> {
                    displayProgressBar(false)
                    isLoading = false
                    when (dataState.httpException.code()) {
                        401 -> displayError("Wrong or empty access token")
                        404 -> {
                            viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
                            // displayError("Films are not found")
                        }
                        429 -> displayError("Too Many Requests. Limit 10 req/sec")
                        500 -> displayError("Exception on server")
                    }
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    isLoading = false
                    displayError(dataState.exceptionMessage)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            Log.e(TAG, message)
        } else
            Toast.makeText(context, "Unknown error", Toast.LENGTH_SHORT).show()

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    var isLoading = false
    var isScrolling = false
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition =
                layoutManager.findFirstVisibleItemPosition()// первый элемент по индексу - 0 Default
            val visibleItemCount =
                layoutManager.childCount//всего существующих контейнеров - 10 Default
            //  Log.e(TAG, "visibleItemCount: $visibleItemCount")
            val totalItemCount =
                layoutManager.itemCount//Элементов в Recycler view по индексу - 20  Default

            val isNotLoading = !isLoading
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount - 4
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val shouldPaginate = isNotLoading && isAtLastItem && isNotAtBeginning && isScrolling
            /*Log.e(TAG, "$firstVisibleItemPosition")
            Log.e(TAG, "$visibleItemCount")
            Log.e(TAG, "$totalItemCount")*/

            if (isAtLastItem)
                displayProgressBar(true)
            else
                displayProgressBar(false)

            if (shouldPaginate || visibleItemCount <= 1) {
                viewModule.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
                isScrolling = false
                if (layoutManager.itemCount > 500)
                    viewModule.setStateEvent(RandomFilmStateEvent.DeliteFilmsCount)
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
            filmListAdapter = FilmListAdapter(this@RandomFilmFragment)
            adapter = filmListAdapter

        }
    }

    override fun onItemSelected(position: Int, item: Film) {
        //  Toast.makeText(context, "On Click !", Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("itemFilm" to item.filmId)
        findNavController().navigate(R.id.detailFilmFragment, bundle)
    }

}


