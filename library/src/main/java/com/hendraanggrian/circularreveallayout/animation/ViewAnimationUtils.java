package com.hendraanggrian.circularreveallayout.animation;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.hendraanggrian.circularreveallayout.animation.RevealAnimator.RevealInfo;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.lang.ref.WeakReference;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static com.hendraanggrian.circularreveallayout.animation.RevealAnimator.CLIP_RADIUS;

public class ViewAnimationUtils {

    private final static boolean LOLLIPOP_PLUS = SDK_INT >= LOLLIPOP;

    public static final int SCALE_UP_DURATION = 500;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static SupportAnimator createCircularReveal(View view,
                                                       int centerX, int centerY,
                                                       float startRadius, float endRadius) {

        if (!(view.getParent() instanceof RevealAnimator)) {
            throw new IllegalArgumentException("View must be inside RevealFrameLayout or RevealLinearLayout.");
        }

        RevealAnimator revealLayout = (RevealAnimator) view.getParent();
        revealLayout.attachRevealInfo(new RevealInfo(centerX, centerY, startRadius, endRadius,
                new WeakReference<>(view)));

        if (LOLLIPOP_PLUS) {
            return new SupportAnimatorLollipop(android.view.ViewAnimationUtils
                    .createCircularReveal(view, centerX, centerY, startRadius, endRadius), revealLayout);
        }

        ObjectAnimator reveal = ObjectAnimator.ofFloat(revealLayout, CLIP_RADIUS,
                startRadius, endRadius);
        reveal.addListener(getRevealFinishListener(revealLayout));

        return new SupportAnimatorPreL(reveal, revealLayout);
    }

    private static Animator.AnimatorListener getRevealFinishListener(RevealAnimator target) {
        if (SDK_INT >= 18) {
            return new RevealAnimator.RevealFinishedJellyBeanMr2(target);
        } else if (SDK_INT >= 14) {
            return new RevealAnimator.RevealFinishedIceCreamSandwich(target);
        } else {
            return new RevealAnimator.RevealFinishedGingerbread(target);
        }
    }

    @Deprecated
    public static void liftingFromBottom(View view, float baseRotation, float fromY, int duration, int startDelay) {
        ViewHelper.setRotationX(view, baseRotation);
        ViewHelper.setTranslationY(view, fromY);

        ViewPropertyAnimator
                .animate(view)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .setStartDelay(startDelay)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    @Deprecated
    public static void liftingFromBottom(View view, float baseRotation, int duration, int startDelay) {
        ViewHelper.setRotationX(view, baseRotation);
        ViewHelper.setTranslationY(view, view.getHeight() / 3);

        ViewPropertyAnimator
                .animate(view)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .setStartDelay(startDelay)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    @Deprecated
    public static void liftingFromBottom(View view, float baseRotation, int duration) {
        ViewHelper.setRotationX(view, baseRotation);
        ViewHelper.setTranslationY(view, view.getHeight() / 3);

        ViewPropertyAnimator
                .animate(view)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    static class SimpleAnimationListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }

}
