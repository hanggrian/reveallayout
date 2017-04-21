package com.hendraanggrian.reveallayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public interface RevealLayout {

    @NonNull
    Animator animate(@NonNull View view);

    @NonNull
    Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter);

    @NonNull
    Animator animate(@NonNull View view, int startX, int startY);

    @NonNull
    Animator animate(@NonNull View view, boolean reverse);

    @NonNull
    Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter, boolean reverse);

    @NonNull
    Animator animate(@NonNull View view, int startX, int startY, boolean reverse);

    @NonNull
    AnimatorSet animateTo(@NonNull View source, @NonNull View target);

    @NonNull
    AnimatorSet animateTo(@NonNull View source, @NonNull final View target, boolean reverse);
}