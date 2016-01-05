package com.hendraanggrian.circularreveallayout.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.hendraanggrian.circularrevealactivitydialog.R;
import com.hendraanggrian.circularreveallayout.Location;
import com.hendraanggrian.circularreveallayout.animation.SupportAnimator;
import com.hendraanggrian.circularreveallayout.animation.ViewAnimationUtils;

/**
 * Created by victorleonardo on 11/29/15.
 */
public class RevealFrameLayout extends FrameLayout {

    private boolean isDialog;
    private SupportAnimator ANIMATOR;

    // from java
    private int REVEAL_X;
    private int REVEAL_Y;
    // from xml
    private int REVEAL_LOCATION;
    private final int REVEAL_LOCATION_DEFAULT = 0;
    private int REVEAL_EXIT_DURATION;
    private int REVEAL_OPEN_DURATION;
    private final int REVEAL_DURATION_DEFAULT = 500;

    public RevealFrameLayout(Context context) {
        super(context);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        animateOpen(context);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        animateOpen(context);
    }

    private void setLocation(final int location) {
        switch (location) {
            case Location.TOP_LEFT:
                REVEAL_X = 0;
                REVEAL_Y = 0;
                break;
            case Location.TOP:
                REVEAL_X = getWidth() / 2;
                REVEAL_Y = 0;
                break;
            case Location.TOP_RIGHT:
                REVEAL_X = getWidth();
                REVEAL_Y = 0;
                break;
            case Location.LEFT:
                REVEAL_X = 0;
                REVEAL_Y = getHeight() / 2;
                break;
            case Location.CENTER:
                REVEAL_X = getWidth() / 2;
                REVEAL_Y = getHeight() / 2;
                break;
            case Location.RIGHT:
                REVEAL_X = getWidth();
                REVEAL_Y = getHeight() / 2;
                break;
            case Location.BOTTOM_LEFT:
                REVEAL_X = 0;
                REVEAL_Y = getHeight();
                break;
            case Location.BOTTOM:
                REVEAL_X = getWidth() / 2;
                REVEAL_Y = getHeight();
                break;
            case Location.BOTTOM_RIGHT:
                REVEAL_X = getWidth();
                REVEAL_Y = getHeight();
                break;
        }
    }

    public void setLocation(int REVEAL_X, int REVEAL_Y) {
        this.REVEAL_X = REVEAL_X;
        this.REVEAL_Y = REVEAL_Y;
    }

    public void isDialog() {
        isDialog = true;
    }

    private void init(Context ctx, AttributeSet attrs) {
        TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.CircularRevealLayout);
        REVEAL_LOCATION = typedArray.getInteger(R.styleable.CircularRevealLayout_reveal_location, REVEAL_LOCATION_DEFAULT);
        REVEAL_EXIT_DURATION = typedArray.getInteger(R.styleable.CircularRevealLayout_reveal_exit_duration, REVEAL_DURATION_DEFAULT);
        REVEAL_OPEN_DURATION = typedArray.getInteger(R.styleable.CircularRevealLayout_reveal_open_duration, REVEAL_DURATION_DEFAULT);
        typedArray.recycle();
    }

    public void animateOpen(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            addOnLayoutChangeListener(new OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);

                    if (isDialog) {
                        Display display = null;
                        if (context instanceof Activity)
                            display = ((Activity) context).getWindowManager().getDefaultDisplay();
                        else if (context instanceof ContextWrapper)
                            display = ((Activity) ((ContextWrapper) context).getBaseContext()).getWindowManager().getDefaultDisplay();

                        Point size = new Point();
                        display.getSize(size);
                        int xScalePoint = size.x / REVEAL_X;
                        int yScalePoint = size.y / REVEAL_Y;

                        // animate from custom coordinate
                        REVEAL_X = new Double((getLeft() + getRight()) / xScalePoint).intValue();
                        REVEAL_Y = new Double((getTop() + getBottom()) / yScalePoint).intValue();
                    }

                    if (REVEAL_LOCATION != REVEAL_LOCATION_DEFAULT)
                        setLocation(REVEAL_LOCATION);

                    ANIMATOR = ViewAnimationUtils.createCircularReveal(RevealFrameLayout.this, REVEAL_X, REVEAL_Y, 0, Math.max(getWidth(), getHeight()));
                    ANIMATOR.setInterpolator(new AccelerateDecelerateInterpolator());
                    ANIMATOR.setDuration(REVEAL_OPEN_DURATION);
                    ANIMATOR.start();
                }
            });
    }

    public void animateExit(final Activity activity) {
        if (!ANIMATOR.isRunning())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ANIMATOR = ANIMATOR.reverse();
                ANIMATOR.setInterpolator(new AccelerateDecelerateInterpolator());
                ANIMATOR.setDuration(REVEAL_EXIT_DURATION);
                ANIMATOR.start();
                ANIMATOR.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        setVisibility(View.GONE);
                        activity.finish();
                    }

                    @Override
                    public void onAnimationCancel() {

                    }

                    @Override
                    public void onAnimationRepeat() {

                    }
                });
            }
    }

    public void animateExit(final Dialog dialog) {
        if (!ANIMATOR.isRunning())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ANIMATOR = ANIMATOR.reverse();
                ANIMATOR.setInterpolator(new AccelerateDecelerateInterpolator());
                ANIMATOR.setDuration(REVEAL_EXIT_DURATION);
                ANIMATOR.start();
                ANIMATOR.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        setVisibility(View.GONE);
                        dialog.dismiss();
                    }

                    @Override
                    public void onAnimationCancel() {

                    }

                    @Override
                    public void onAnimationRepeat() {

                    }
                });
            }
    }

    public void animateExit(SupportAnimator.AnimatorListener animatorListener) {
        if (!ANIMATOR.isRunning())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ANIMATOR = ANIMATOR.reverse();
                ANIMATOR.setInterpolator(new AccelerateDecelerateInterpolator());
                ANIMATOR.setDuration(REVEAL_EXIT_DURATION);
                ANIMATOR.start();
                ANIMATOR.addListener(animatorListener);
            }
    }
}