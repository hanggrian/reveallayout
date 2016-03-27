package io.github.hendraanggrian.circularreveallayout.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import io.github.hendraanggrian.circularreveallayout.Reveal;
import io.github.hendraanggrian.circularreveallayout.RevealCallback;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class RevealFrameLayout extends FrameLayout implements RevealCallback {

    private Reveal reveal;

    public RevealFrameLayout(Context context) {
        super(context);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.reveal = new Reveal(context, attrs, getWidth(), getHeight());
        animateOpen(context);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.reveal = new Reveal(context, attrs, getWidth(), getHeight());
        animateOpen(context);
    }

    @Override
    public void animateOpen(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            addOnLayoutChangeListener(new OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);

                    if (reveal.isDialog()) {
                        Display display = null;
                        if (context instanceof Activity)
                            display = ((Activity) context).getWindowManager().getDefaultDisplay();
                        else if (context instanceof ContextWrapper)
                            display = ((Activity) ((ContextWrapper) context).getBaseContext()).getWindowManager().getDefaultDisplay();

                        Point size = new Point();
                        display.getSize(size);
                        int xScalePoint = size.x / reveal.getX();
                        int yScalePoint = size.y / reveal.getY();

                        // animate from custom coordinate
                        reveal.setX(new Double((getLeft() + getRight()) / xScalePoint).intValue());
                        reveal.setY(new Double((getTop() + getBottom()) / yScalePoint).intValue());
                    }

                    reveal.setAnimator(ViewAnimationUtils.createCircularReveal(RevealFrameLayout.this, reveal.getX(), reveal.getY(), 0, Math.max(getWidth(), getHeight())));
                    reveal.getAnimator().setInterpolator(new AccelerateDecelerateInterpolator());
                    reveal.getAnimator().setDuration(reveal.getDurationOpen());
                    reveal.getAnimator().start();
                }
            });
    }

    @Override
    public void animateExit(final Activity mActivity) {
        if (!reveal.getAnimator().isRunning())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                reveal.setAnimator(reveal.getAnimator().reverse());
                reveal.getAnimator().setInterpolator(new AccelerateDecelerateInterpolator());
                reveal.getAnimator().setDuration(reveal.getDurationExit());
                reveal.getAnimator().start();
                reveal.getAnimator().addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        setVisibility(View.GONE);
                        mActivity.finish();
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

    @Override
    public void animateExit(final Dialog mDialog) {
        if (!reveal.getAnimator().isRunning())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                reveal.setAnimator(reveal.getAnimator().reverse());
                reveal.getAnimator().setInterpolator(new AccelerateDecelerateInterpolator());
                reveal.getAnimator().setDuration(reveal.getDurationExit());
                reveal.getAnimator().start();
                reveal.getAnimator().addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        setVisibility(View.GONE);
                        mDialog.dismiss();
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

    @Override
    public boolean isDialog() {
        return this.reveal.isDialog();
    }

    @Override
    public void setRevealLocation(int x, int y) {
        this.reveal.setX(x);
        this.reveal.setY(y);
    }

    @Override
    public int getRevealX() {
        return this.reveal.getX();
    }

    @Override
    public int getRevealY() {
        return this.reveal.getY();
    }

    @Override
    public int getOpenDuration() {
        return this.reveal.getDurationOpen();
    }

    @Override
    public int getExitDuration() {
        return this.reveal.getDurationExit();
    }
}