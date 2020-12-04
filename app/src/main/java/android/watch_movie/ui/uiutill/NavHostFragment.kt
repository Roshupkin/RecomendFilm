package android.watch_movie.ui.uiutill

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class NavHostFragment : NavHostFragment() {
    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }

    companion object {
        fun create(navGraphId: Int): NavHostFragment {
            return NavHostFragment.create(navGraphId)
        }
    }
}
