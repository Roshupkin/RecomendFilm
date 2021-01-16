package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.adapters.CardListAdapter
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
class RandomFilmFragment : Fragment(R.layout.fragment_random_film), CardListAdapter.Interaction {
    private val TAG = "RandomFilmFragment"
    private val viewModel: RandomFilmViewModel by viewModels()
    lateinit var cardListAdapter: CardListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setStateEvent(RandomFilmStateEvent.ReplaceRandomEntity)
        viewModel.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscreibeObsirevers()
        initRecyclerView()
        Log.e(TAG, "onViewCreated")
    }

    private fun subscreibeObsirevers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Film>> -> {
                    displayProgressBar(false)
                    isLoading = false
                    cardListAdapter.submitList(dataState.data)
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
                            viewModel.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
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
            val totalItemCount =
                layoutManager.itemCount//Элементов в Recycler view по индексу - 20  Default

            val isNotLoading = !isLoading
            /*Item that the download starts with*/
            val isLoadedItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount - 4
            /*Lats item in list*/
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount

            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val shouldPaginate = isNotLoading && isLoadedItem && isNotAtBeginning && isScrolling

            if (isAtLastItem)
                displayProgressBar(true)
            else
                displayProgressBar(false)

            if (shouldPaginate || visibleItemCount <= 1) {
                viewModel.setStateEvent(RandomFilmStateEvent.GetFilmsEvent)
                isScrolling = false
                /*if item in Recycler View >500 then deletes several movies based on the deletion counter */
                if (layoutManager.itemCount > 500)
                    viewModel.setStateEvent(RandomFilmStateEvent.DeletFilmsCount)
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
        recycler_film_history.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoratio = TopSpacingItemDecoratio(20)
            addItemDecoration(topSpacingItemDecoratio)
            addOnScrollListener(this@RandomFilmFragment.scrollListener)
            cardListAdapter = CardListAdapter(this@RandomFilmFragment)
            adapter = cardListAdapter

        }
    }

    override fun onItemSelected(position: Int, item: Film) {
        val bundle = bundleOf("itemFilm" to item.filmID)
        findNavController().navigate(R.id.detailFilmFragment, bundle)
    }

    override fun onSaveFavorites(isSave: Boolean, item: Film) {
        Log.e(TAG,"$isSave       ssss      ${item}")
        viewModel.setFavorites(isSave,item)

    }

    override fun onSetEvaluated(item: Film) {
        viewModel.setEvaluated(item)
    }

}


