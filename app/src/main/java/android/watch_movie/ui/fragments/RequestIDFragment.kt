package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.adapters.CardListAdapter
import android.watch_movie.model.Film
import android.watch_movie.ui.viewmodel.RequestIDViewModule
import android.watch_movie.util.TopSpacingItemDecoratio
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_film_by_id.*
import kotlinx.android.synthetic.main.fragment_films.recycler_film_history
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RequestIDFragment : Fragment(R.layout.fragment_film_by_id), CardListAdapter.Interaction {
    private val requestIDViewModel: RequestIDViewModule by viewModels()
    lateinit var cardListAdapter: CardListAdapter

    val TAG = "RequestIDFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        edittext_requested_id.setOnClickListener {

        }
        button_random_id.setOnClickListener {
            val randomID = (1..1000).random()
            button_search.text = randomID.toString()
        }

    }


    private fun initRecyclerView() {
        recycler_film_history.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecoratio = TopSpacingItemDecoratio(20)
            addItemDecoration(topSpacingItemDecoratio)
            cardListAdapter = CardListAdapter(this@RequestIDFragment)
            adapter = cardListAdapter

        }
    }

    override fun onItemSelected(position: Int, item: Film) {
        val bundle = bundleOf("itemFilm" to item.filmID)
        findNavController().navigate(R.id.detailFilmFragment, bundle)
    }

    override fun onSaveFavorites(isSave: Boolean, item: Film) {
        Log.e(TAG, "$isSave       ssss      ${item}")
        requestIDViewModel.setFavorites(isSave, item)

    }

    override fun onSetEvaluated(item: Film) {
        requestIDViewModel.setEvaluated(item)
    }
}