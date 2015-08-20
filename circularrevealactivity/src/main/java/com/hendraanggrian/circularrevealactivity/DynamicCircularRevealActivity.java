package com.hendraanggrian.circularrevealactivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public abstract class DynamicCircularRevealActivity extends AppCompatActivity {

    private SupportAnimator mAnimator;

    private int REVEAL_VIEW_RES_ID;
    private int REVEAL_X;
    private int REVEAL_Y;
    private int REVEAL_DURATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        REVEAL_VIEW_RES_ID = getDynamicCircularReveal().getViewResId();
        REVEAL_X = getDynamicCircularReveal().getX();
        REVEAL_Y = getDynamicCircularReveal().getY();
        REVEAL_DURATION = getDynamicCircularReveal().getDuration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(REVEAL_VIEW_RES_ID).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);

                    // get the final radius for the clipping circle
                    int finalRadius = Math.max(findViewById(REVEAL_VIEW_RES_ID).getWidth(), findViewById(REVEAL_VIEW_RES_ID).getHeight());

                    mAnimator = ViewAnimationUtils.createCircularReveal(findViewById(REVEAL_VIEW_RES_ID), REVEAL_X, REVEAL_Y, 0, finalRadius);
                    mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    mAnimator.setDuration(REVEAL_DURATION);
                    mAnimator.start();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = mAnimator.reverse();
            mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimator.setDuration(REVEAL_DURATION);
            mAnimator.start();
            mAnimator.addListener(new SupportAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart() {

                }

                @Override
                public void onAnimationEnd() {
                    findViewById(REVEAL_VIEW_RES_ID).setVisibility(View.GONE);
                    mAnimator = null;
                    DynamicCircularRevealActivity.super.onBackPressed();
                }

                @Override
                public void onAnimationCancel() {

                }

                @Override
                public void onAnimationRepeat() {

                }
            });

        } else
            super.onBackPressed();
    }

    protected abstract int getLayoutResId();

    protected abstract DynamicCircularReveal getDynamicCircularReveal();
}