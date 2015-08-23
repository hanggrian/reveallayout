package com.hendraanggrian.circularreveal.dialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.hendraanggrian.circularreveal.RevealProperties;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by victorleonardo on 8/22/15.
 */
public abstract class CircularRevealDialog extends Dialog {

    private int REVEAL_VIEW_RES_ID;
    private int REVEAL_X;
    private int REVEAL_Y;
    private int REVEAL_DURATION = 500; //default
    private boolean REVEAL_ANIMATE_EXIT = false; //default

    private SupportAnimator mAnimator;

    public CircularRevealDialog(final Context ctx, int theme) {
        super(ctx, theme);

        REVEAL_VIEW_RES_ID = getProperties().getViewResId();
        REVEAL_X = getProperties().getX();
        REVEAL_Y = getProperties().getY();
        REVEAL_DURATION = getProperties().getDuration();
        REVEAL_ANIMATE_EXIT = getProperties().isAnimateExit();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(REVEAL_VIEW_RES_ID).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);

                    /*Log.w("left", String.valueOf(findViewById(REVEAL_VIEW_RES_ID).getLeft()));
                    Log.w("right", String.valueOf(findViewById(REVEAL_VIEW_RES_ID).getRight()));
                    Log.w("top", String.valueOf(findViewById(REVEAL_VIEW_RES_ID).getTop()));
                    Log.w("bottom", String.valueOf(findViewById(REVEAL_VIEW_RES_ID).getBottom()));

                    Log.w("X", String.valueOf(REVEAL_X));
                    Log.w("Y", String.valueOf(REVEAL_Y));*/

                    Display display = ((Activity) ctx).getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int xScalePoint = size.x / REVEAL_X;
                    int yScalePoint = size.y / REVEAL_Y;

                    // animate from custom coordinate
                    int mX = (findViewById(REVEAL_VIEW_RES_ID).getLeft() + findViewById(REVEAL_VIEW_RES_ID).getRight()) / xScalePoint;
                    int mY = (findViewById(REVEAL_VIEW_RES_ID).getTop() + findViewById(REVEAL_VIEW_RES_ID).getBottom()) / yScalePoint;


                    // get the final radius for the clipping circle
                    int finalRadius = Math.max(findViewById(REVEAL_VIEW_RES_ID).getWidth(), findViewById(REVEAL_VIEW_RES_ID).getHeight());

                    mAnimator = ViewAnimationUtils.createCircularReveal(findViewById(REVEAL_VIEW_RES_ID), mX, mY, 0, finalRadius);
                    mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    mAnimator.setDuration(REVEAL_DURATION);
                    mAnimator.start();
                }
            });
        }
    }

    @Override
    public void dismiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && REVEAL_ANIMATE_EXIT) {
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
                    CircularRevealDialog.super.dismiss();
                }

                @Override
                public void onAnimationCancel() {

                }

                @Override
                public void onAnimationRepeat() {

                }
            });

        } else
            CircularRevealDialog.super.dismiss();
    }

    public abstract int getLayoutResId();

    public abstract RevealProperties getProperties();

}