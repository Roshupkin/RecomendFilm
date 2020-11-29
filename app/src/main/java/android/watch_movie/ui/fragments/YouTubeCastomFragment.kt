package android.watch_movie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment


class YouTubeCastomFragment : YouTubePlayerFragment() {
    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        initialize(
            "AIzaSyCnL67LXRQ-5EN5K4Dv5tedN-T0-L1TmsI",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    player.loadVideo("Gc3Xjy9292c")
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                }
            })
    }

}