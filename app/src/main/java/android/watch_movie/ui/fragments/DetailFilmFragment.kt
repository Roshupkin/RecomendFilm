package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.network.entity.DetailFilmEntity
import android.watch_movie.ui.viewmodel.DetailFilmStateEvent
import android.watch_movie.ui.viewmodel.DetailFilmViewModel
import android.watch_movie.util.DataState
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.film_details_fragment.*
import kotlinx.android.synthetic.main.fragment_films.progress_bar

@AndroidEntryPoint
class DetailFilmFragment : Fragment(R.layout.film_details_fragment) {
    private val viewModule: DetailFilmViewModel by viewModels()
    private var callback: OnBackPressedCallback? = null
   // val youTubeFragment = YouTubeCastomFragment()

    val TAG = "DetailFilmFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, (this.arguments?.getInt("itemFilm") ?: 324).toString())
        viewModule.id = this.arguments?.getInt("itemFilm") ?: 324
        subscribeObserver()
        viewModule.setStateEvent(DetailFilmStateEvent.GetFilmsEvent)
        backPressed()

        val youtubefragment =
            activity?.fragmentManager?.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment

        youtubefragment.initialize(
            "AIzaSyCnL67LXRQ-5EN5K4Dv5tedN-T0-L1TmsI",
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    provaider: YouTubePlayer.Provider?,
                    player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    player.loadVideo("Gc3Xjy9292c")
                }
                override fun onInitializationFailure(
                    provaider: YouTubePlayer.Provider?,
                    error: YouTubeInitializationResult?
                ) {
                }
            })

     /*   activity?.fragmentManager?.beginTransaction()?.add(R.id.youtube_fragment, youTubeFragment)
            ?.commit()
*/    }
    override fun onDestroyView() {
        super.onDestroyView()
        val youtubefragment =
            activity?.fragmentManager?.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment

        activity?.fragmentManager?.beginTransaction()?.remove(youtubefragment)
            ?.commit()
    }


    private fun subscribeObserver() {
        viewModule.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Success<DetailFilmEntity> -> {
                    text_film_name.text = dataState.data.dataFilm?.nameRu ?: "null blin"
                    context?.let {
                        Glide.with(it).load(dataState.data.dataFilm?.posterUrl).into(film_image)
                    }
                }
                is DataState.HttpError -> {
                    displayProgressBar(false)
                    when (dataState.httpException.code()) {
                        401 -> displayError("Wrong or empty access token")
                        404 -> {
                            viewModule.setStateEvent(DetailFilmStateEvent.GetFilmsEvent)
                            displayError("Films are not found")
                        }
                        429 -> displayError("Too Many Requests. Limit 10 req/sec")
                        500 -> displayError("Exception on server")
                    }
                }
                is DataState.Error -> {
                    displayProgressBar(false)
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
        if (isDisplayed) progress_bar.visibility = View.VISIBLE
        else progress_bar.visibility = View.GONE

    }

    private fun backPressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(
                    requireActivity(),
                    R.id.fragment_container
                ).popBackStack()
                Log.e(TAG, "BackPressed")
            }
        }.also {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, it)
        }
    }


    
}