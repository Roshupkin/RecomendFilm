package android.watch_movie.ui.fragments

import android.bignerdranch.kosmos.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.watch_movie.network.entity.DetaildFilmEntity
import android.watch_movie.ui.viewmodel.DetaildFilmStateEvent
import android.watch_movie.ui.viewmodel.DetaildFilmViewModel
import android.watch_movie.util.DataState
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.film_details_fragment.*
import kotlinx.android.synthetic.main.fragment_films.progress_bar
import kotlinx.coroutines.ExperimentalCoroutinesApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetaildFilmFragment : Fragment( R.layout.film_details_fragment) {
    private val viewModule: DetaildFilmViewModel by viewModels()
private val youtubeFragment = YouTubeCastomFragment()

    val TAG = "DetailFilmFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*takes ID the film and requests for details*/
        viewModule.filmID= this.arguments?.getInt("itemFilm")

        subscribeObserver()
        viewModule.setStateEvent(DetaildFilmStateEvent.GetFilmsEvent)
        /*play YouTube Player*/
           activity?.fragmentManager?.beginTransaction()?.add(R.id.youtube_fragment, youtubeFragment)
               ?.commit()
    }
      override fun onDestroyView() {
          super.onDestroyView()
          /*destroy YouTube Player*/
          activity?.fragmentManager?.beginTransaction()?.remove(youtubeFragment)
              ?.commit()
      }

    private fun subscribeObserver() {
        viewModule.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.Success<DetaildFilmEntity> -> {
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
                            viewModule.setStateEvent(DetaildFilmStateEvent.GetFilmsEvent)
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
}