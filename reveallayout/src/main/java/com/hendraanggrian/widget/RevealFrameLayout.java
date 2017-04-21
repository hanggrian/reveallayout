package com.hendraanggrian.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.reveallayout.RevealCenter;
import com.hendraanggrian.reveallayout.RevealLayout;
import com.hendraanggrian.reveallayout.RevealLayoutAttacher;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class RevealFrameLayout extends io.codetail.widget.RevealFrameLayout implements RevealLayout {

    @NonNull private final RevealLayoutAttacher attacher;

    public RevealFrameLayout(Context context) {
        this(context, null);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attacher = new RevealLayoutAttacher(context, attrs);
    }

    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        attacher.addView(child);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view) {
        return attacher.animate(view);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter) {
        return attacher.animate(view, revealCenter);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, int startX, int startY) {
        return attacher.animate(view, startX, startY);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, boolean reverse) {
        return attacher.animate(view, reverse);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter, boolean reverse) {
        return attacher.animate(view, revealCenter, reverse);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, int startX, int startY, boolean reverse) {
        return attacher.animate(view, startX, startY, reverse);
    }

    @NonNull
    @Override
    public AnimatorSet animateTo(@NonNull View source, @NonNull View target) {
        return attacher.animateTo(source, target);
    }

    @NonNull
    @Override
    public AnimatorSet animateTo(@NonNull View source, @NonNull View target, boolean reverse) {
        return attacher.animateTo(source, target, reverse);
    }
}