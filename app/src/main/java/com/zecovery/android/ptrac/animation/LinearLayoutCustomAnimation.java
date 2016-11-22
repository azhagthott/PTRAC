package com.zecovery.android.ptrac.animation;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by moe on 08-11-16.
 */

public class LinearLayoutCustomAnimation {

    public LinearLayoutCustomAnimation() {
    }

    public void rotateImage(View v, int duration){

        int pivotX = v.getHeight()/2;
        int pivotY = v.getHeight()/2;

        RotateAnimation rotateAnimation = new RotateAnimation(0,270, pivotX, pivotY);

        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillEnabled(true);
        rotateAnimation.setFillAfter(true);
        v.startAnimation(rotateAnimation);
    }

    public void expand(final View v, int duration,int targetHeight){

        int prevHeight = v.getHeight();

        v.setVisibility(View.VISIBLE);

        ValueAnimator value = ValueAnimator.ofInt(prevHeight, targetHeight);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });

        value.setInterpolator(new DecelerateInterpolator());
        value.setDuration(duration);
        value.start();
    }

    public void collapse(final View v, int duration,int targetHeight){

        int prevHeight = v.getHeight();

        ValueAnimator value = ValueAnimator.ofInt(prevHeight, targetHeight);
        value.setInterpolator(new DecelerateInterpolator());
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height=(int)animation.getAnimatedValue();
                v.requestLayout();
            }
        });

        value.setInterpolator(new DecelerateInterpolator());
        value.setDuration(duration);
        value.start();
    }

}

