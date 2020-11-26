package android.watch_movie.ui.uiutill

import android.watch_movie.adapters.FilmListAdapter
import android.watch_movie.ui.fragments.DetailFilmFragment
import android.watch_movie.ui.fragments.FilmFragment
import android.watch_movie.ui.fragments.RandomFilmFragment
import android.watch_movie.util.NetworkCheck
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FragmentFactory
@Inject
constructor(
    private val someString: String
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            FilmFragment::class.java.name -> {
                FilmFragment(/*someString*/)
            }
            RandomFilmFragment::class.java.name -> {
                RandomFilmFragment()
            }
            DetailFilmFragment::class.java.name ->{
                DetailFilmFragment()
            }
            else -> super.instantiate(classLoader, className)
        }

    }

}