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
final class RevealReversible extends Reversible {

    @NonNull private final Animator animator;

    RevealReversible(@NonNull View source, @Nullable Integer duration, int startX, int startY) {
        super(source);
        animator = ViewAnimationUtils.createCircularReveal(source, startX, startY, RadiusUtils.calculateEnd(source), 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(duration != null ? duration : CircularReveal.DURATION_DEFAULT);
    }

    @Override
    public void reverse() {
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
    }
}