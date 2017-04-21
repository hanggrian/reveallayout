package com.hendraanggrian.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public interface CircularRevealLayout {

    @NonNull
    Animator create(@NonNull View view);

    @NonNull
    Animator create(@NonNull View view, @NonNull RevealCenter revealCenter);

    @NonNull
    Animator create(@NonNull View view, int startX, int startY);

    @NonNull
    Animator create(@NonNull View view, boolean reverse);

    @NonNull
    Animator create(@NonNull View view, @NonNull RevealCenter revealCenter, boolean reverse);

    @NonNull
    Animator create(@NonNull View view, int startX, int startY, boolean reverse);

    @NonNull
    AnimatorSet createSet(@NonNull View source, @NonNull View target);

    @NonNull
    AnimatorSet createSet(@NonNull View source, @NonNull final View target, boolean reverse);
}