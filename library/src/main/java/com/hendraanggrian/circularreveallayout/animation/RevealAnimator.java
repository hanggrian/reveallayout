package com.hendraanggrian.circularreveallayout.animation;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.util.FloatProperty;

import java.lang.ref.WeakReference;

import static com.hendraanggrian.circularreveallayout.animation.ViewAnimationUtils.SimpleAnimationListener;

public interface RevealAnimator {

    RevealRadius CLIP_RADIUS = new RevealRadius();

    void onRevealAnimationStart();

    void onRevealAnimationEnd();

    void onRevealAnimationCancel();

    void setRevealRadius(float value);

    float getRevealRadius();

    void invalidate(Rect bounds);

    void attachRevealInfo(RevealInfo info);

    SupportAnimator startReverseAnimation();

    class RevealInfo {
        public final int centerX;
        public final int centerY;
        public final float startRadius;
        public final float endRadius;
        public final WeakReference<View> target;

        public RevealInfo(int centerX, int centerY, float startRadius, float endRadius,
                          WeakReference<View> target) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.startRadius = startRadius;
            this.endRadius = endRadius;
            this.target = target;
        }

        public View getTarget() {
            return target.get();
        }

        public boolean hasTarget() {
            return getTarget() != null;
        }
    }

    class RevealFinishedGingerbread extends SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;

        RevealFinishedGingerbread(RevealAnimator target) {
            mReference = new WeakReference<>(target);
        }

        @Override
        public void onAnimationStart(Animator animation) {
            RevealAnimator target = mReference.get();
            target.onRevealAnimationStart();
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            RevealAnimator target = mReference.get();
            target.onRevealAnimationCancel();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            RevealAnimator target = mReference.get();
            target.onRevealAnimationEnd();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class RevealFinishedIceCreamSandwich extends RevealFinishedGingerbread {
        int mFeaturedLayerType;
        int mLayerType;

        RevealFinishedIceCreamSandwich(RevealAnimator target) {
            super(target);

            mLayerType = ((View) target).getLayerType();
            mFeaturedLayerType = View.LAYER_TYPE_SOFTWARE;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            ((View) mReference.get()).setLayerType(mLayerType, null);
            super.onAnimationEnd(animation);
        }

        @Override
        public void onAnimationStart(Animator animation) {
            ((View) mReference.get()).setLayerType(mFeaturedLayerType, null);
            super.onAnimationStart(animation);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            ((View) mReference.get()).setLayerType(mLayerType, null);
            super.onAnimationEnd(animation);
        }
    }

    class RevealFinishedJellyBeanMr2 extends RevealFinishedIceCreamSandwich {

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        RevealFinishedJellyBeanMr2(RevealAnimator target) {
            super(target);

            mFeaturedLayerType = View.LAYER_TYPE_HARDWARE;
        }
    }

    class RevealRadius extends FloatProperty<RevealAnimator> {

        public RevealRadius() {
            super("revealRadius");
        }

        @Override
        public void setValue(RevealAnimator object, float value) {
            object.setRevealRadius(value);
        }

        @Override
        public Float get(RevealAnimator object) {
            return object.getRevealRadius();
        }
    }
}