package android.watch_movie.ui.uiutill

import android.watch_movie.ui.fragments.DetaildFilmFragment
import android.watch_movie.ui.fragments.TopFilmFragment
import android.watch_movie.ui.fragments.RandomFilmFragment
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
            TopFilmFragment::class.java.name -> {
                TopFilmFragment(/*someString*/)
            }
            RandomFilmFragment::class.java.name -> {
                RandomFilmFragment()
            }
            DetaildFilmFragment::class.java.name ->{
                DetaildFilmFragment()
            }
            else -> super.instantiate(classLoader, className)
        }

    }

}