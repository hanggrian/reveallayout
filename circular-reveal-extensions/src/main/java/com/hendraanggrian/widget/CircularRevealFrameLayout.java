package com.hendraanggrian.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.circularreveal.CircularRevealLayout;
import com.hendraanggrian.circularreveal.CircularRevealLayoutAttacher;
import com.hendraanggrian.circularreveal.RevealCenter;

import io.codetail.widget.RevealFrameLayout;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class CircularRevealFrameLayout extends RevealFrameLayout implements CircularRevealLayout {

    @NonNull private final CircularRevealLayoutAttacher attacher;

    public CircularRevealFrameLayout(Context context) {
        this(context, null);
    }

    public CircularRevealFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularRevealFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attacher = new CircularRevealLayoutAttacher(context, attrs);
    }

    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        attacher.addView(child);
    }

    @NonNull
    @Override
    public Animator create(@NonNull View view) {
        return attacher.create(view);
    }

    @NonNull
    @Override
    public Animator create(@NonNull View view, @NonNull RevealCenter revealCenter) {
        return attacher.create(view, revealCenter);
    }

    @NonNull
    @Override
    public Animator create(@NonNull View view, int startX, int startY) {
        return attacher.create(view, startX, startY);
    }

    @NonNull
    @Override
    public Animator create(@NonNull View view, boolean reverse) {
        return attacher.create(view, reverse);
    }

    @NonNull
    @Override
    public Animator create(@NonNull View view, @NonNull RevealCenter revealCenter, boolean reverse) {
        return attacher.create(view, revealCenter, reverse);
    }

    @NonNull
    @Override
    public Animator create(@NonNull View view, int startX, int startY, boolean reverse) {
        return attacher.create(view, startX, startY, reverse);
    }

    @NonNull
    @Override
    public AnimatorSet createSet(@NonNull View source, @NonNull View target) {
        return attacher.createSet(source, target);
    }

    @NonNull
    @Override
    public AnimatorSet createSet(@NonNull View source, @NonNull View target, boolean reverse) {
        return attacher.createSet(source, target, reverse);
    }
}