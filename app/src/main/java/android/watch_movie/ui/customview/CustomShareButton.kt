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

class CustomShareButton (context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageButton(
        context,
        attrs
    ) {
    init {
        background=null
        isClickable=true
        isFocusable=true

        setImageResource(R.drawable.ic_share)
        val setAnimDown = AnimatorInflater.loadAnimator(context, R.animator.anim_click_down) as AnimatorSet
        val setAnimUp = AnimatorInflater.loadAnimator(context, R.animator.anim_click_up) as AnimatorSet
        setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.action){
                    MotionEvent.ACTION_DOWN->{
                        this@CustomShareButton.imageTintList= ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.colorBasicRed))
                        setAnimDown.setTarget(this@CustomShareButton)
                        setAnimDown.start()
                    }

                    MotionEvent.ACTION_UP->{
                        this@CustomShareButton.imageTintList=
                            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorWhite))
                        setAnimUp.setTarget( this@CustomShareButton)
                        setAnimUp.start()
                    }

                    MotionEvent.ACTION_CANCEL-> {
                        this@CustomShareButton.imageTintList= ColorStateList.valueOf(
                            ContextCompat.getColor(context, R.color.colorWhite))
                        setAnimUp.setTarget(this@CustomShareButton)
                        setAnimUp.start()
                    }
                }
                return v?.onTouchEvent(event) ?:true
            }
        })

    }
}