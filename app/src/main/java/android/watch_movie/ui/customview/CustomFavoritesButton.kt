package android.watch_movie.ui.customview

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.bignerdranch.kosmos.R
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

@SuppressLint("ResourceType")
class CustomFavoritesButton(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatToggleButton(
        context,
        attrs
    ) {
    init {
        isChecked = false
        isClickable = true
        isFocusable = true
        textOff=""
        textOn=""

        setBackgroundResource(R.drawable.ic_add_favorites_off)
        val setAnimDown =
            AnimatorInflater.loadAnimator(context, R.animator.anim_click_down) as AnimatorSet
        val setAnimUp =
            AnimatorInflater.loadAnimator(context, R.animator.anim_click_up) as AnimatorSet

        setOnTouchListener(object : OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        setAnimDown.setTarget(this@CustomFavoritesButton)
                        setAnimDown.start()
                    }

                    MotionEvent.ACTION_UP -> {
                        setAnimUp.setTarget(this@CustomFavoritesButton)
                        setAnimUp.start()
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        setAnimUp.setTarget(this@CustomFavoritesButton)
                        setAnimUp.start()
                    }
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

    }
}