package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.adapters.RandomFilmListAdapter
import android.watch_movie.model.Film
import android.watch_movie.ui.viewmodel.FilmStateEvent
import android.watch_movie.ui.viewmodel.FilmViewModule
import android.watch_movie.util.DataState
import android.watch_movie.util.TopSpacingItemDecoratio
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_films.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TopFilmFragment : Fragment(R.layout.fragment_films), RandomFilmListAdapter.Interaction{
    private val viewModel: FilmViewModule by viewModels()
    private val TAG = "FilmFragment"
    lateinit var randomFilmListAdapter:RandomFilmListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        viewModel.setStateEvent(FilmStateEvent.GetFilmsEvent)
        initRecyclerView()

    }


    private fun subscribeObservers() {

        viewModel.dataState.observe(viewLifecycleOwner,  { dataState ->
            when (dataState) {
                is DataState.Success<List<Film>> -> {
                    displayProgressBar(false)
                    randomFilmListAdapter.submitList(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exceptionMessage)
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
    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoratio = TopSpacingItemDecoratio(20)
            addItemDecoration(topSpacingItemDecoratio)
            randomFilmListAdapter = RandomFilmListAdapter(this@TopFilmFragment)
            adapter = randomFilmListAdapter
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility =
            if (isDisplayed) View.VISIBLE
            else View.GONE

    }

    override fun onItemSelected(position: Int, item: Film) {
        val bundle = bundleOf("itemFilm" to item.filmID)
        findNavController().navigate(R.id.detaildFilmFragment, bundle)
    }

    override fun onSaveFavorites(isSave: Boolean, item: Film) {
        Log.e(TAG,"$isSave       ssss      $item")
        viewModel.setSaveFavorites(isSave,item)
    }

    override fun onSetEvaluated(item: Film) {
        viewModel.setEvaluated(item)
    }


}