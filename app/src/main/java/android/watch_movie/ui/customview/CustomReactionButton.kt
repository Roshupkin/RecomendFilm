package android.watch_movie.ui.customview

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.bignerdranch.kosmos.R
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CustomReactionButton(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageButton(
        context,
        attrs
    ) {
    init {
        isClickable=true
        isFocusable=true
        background=null

        setImageResource(R.drawable.ic_add_reaction)
        val setAnimDown = AnimatorInflater.loadAnimator(context, R.animator.anim_click_down) as AnimatorSet
        val setAnimUp = AnimatorInflater.loadAnimator(context, R.animator.anim_click_up) as AnimatorSet
        setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.action){
                    MotionEvent.ACTION_DOWN->{
                        this@CustomReactionButton.imageTintList= ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.colorBasicRed))
                        setAnimDown.setTarget(this@CustomReactionButton)
                        setAnimDown.start()
                    }

                    MotionEvent.ACTION_UP->{
                        this@CustomReactionButton.imageTintList=
                            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorWhite))
                        setAnimUp.setTarget( this@CustomReactionButton)
                        setAnimUp.start()
                    }

                    MotionEvent.ACTION_CANCEL-> {
                        this@CustomReactionButton.imageTintList= ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.colorWhite))
                        setAnimUp.setTarget(this@CustomReactionButton)
                        setAnimUp.start()
                    }
                }
                return v?.onTouchEvent(event) ?:true
            }
        })

    }
}