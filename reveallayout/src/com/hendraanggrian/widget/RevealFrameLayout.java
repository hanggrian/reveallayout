package com.hendraanggrian.widget;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.reveallayout.RevealCenter;
import com.hendraanggrian.reveallayout.RevealableLayout;
import com.hendraanggrian.reveallayout.RevealableLayoutImpl;

import java.util.Collection;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class RevealFrameLayout extends io.codetail.widget.RevealFrameLayout implements RevealableLayout {

    @NonNull private final RevealableLayoutImpl impl;

    public RevealFrameLayout(Context context) {
        this(context, null);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        impl = new RevealableLayoutImpl(context, attrs);
    }

    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        impl.addView(child);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view) {
        return impl.animate(view);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter) {
        return impl.animate(view, revealCenter);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, int startX, int startY) {
        return impl.animate(view, startX, startY);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, boolean reverse) {
        return impl.animate(view, reverse);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter, boolean reverse) {
        return impl.animate(view, revealCenter, reverse);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, int startX, int startY, boolean reverse) {
        return impl.animate(view, startX, startY, reverse);
    }

    @NonNull
    @Override
    public Collection<Animator> animateTo(@NonNull View source, @NonNull View target) {
        return impl.animateTo(source, target);
    }

    @NonNull
    @Override
    public Collection<Animator> animateTo(@NonNull View source, @NonNull View target, boolean reverse) {
        return impl.animateTo(source, target, reverse);
    }
}