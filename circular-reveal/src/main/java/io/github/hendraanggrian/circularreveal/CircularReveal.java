package io.github.hendraanggrian.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import io.codetail.animation.ViewAnimationUtils;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public final class CircularReveal {

    public static final int DURATION_LONG = 350;
    public static final int DURATION_SHORT = 200;
    static int DURATION_DEFAULT = DURATION_LONG;

    @NonNull private final View source;
    private int duration;

    @Nullable private OnStart onStart;
    @Nullable private OnEnd onEnd;

    private CircularReveal(@NonNull View source) {
        this.source = source;
        this.duration = DURATION_DEFAULT;
    }

    @NonNull
    public CircularReveal duration(int duration) {
        this.duration = duration;
        return this;
    }

    @NonNull
    public CircularReveal onStart(@NonNull OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    @NonNull
    public CircularReveal onEnd(@NonNull OnEnd onEnd) {
        this.onEnd = onEnd;
        return this;
    }

    @NonNull
    public RevealReversible reveal() {
        return reveal(Start.CENTER);
    }

    @NonNull
    public RevealReversible reveal(@NonNull Start start) {
        return reveal(start.getX(source), start.getY(source));
    }

    @NonNull
    public RevealReversible reveal(int startX, int startY) {
        Animator animator = ViewAnimationUtils.createCircularReveal(source, startX, startY, 0, RadiusUtils.calculateEnd(source));
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(duration);
        if (onStart != null || onEnd != null)
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (onStart != null)
                        onStart.onStart(source);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (onEnd != null)
                        onEnd.onEnd(source);
                }
            });
        animator.start();
        return new RevealReversible(source, duration, startX, startY);
    }

    @NonNull
    public RevealToReversible revealTo(@NonNull View target) {

        return new RevealToReversible(source);
    }

    public static void setDefaultDuration(int duration) {
        DURATION_DEFAULT = duration;
    }

    @NonNull
    public static CircularReveal with(@NonNull View source) {
        return new CircularReveal(source);
    }

    public interface OnStart {
        void onStart(@NonNull View view);
    }

    public interface OnEnd {
        void onEnd(@NonNull View view);
    }
}