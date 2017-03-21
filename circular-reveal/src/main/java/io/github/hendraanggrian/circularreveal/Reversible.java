package io.github.hendraanggrian.circularreveal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public abstract class Reversible {

    public abstract void reverse();

    @NonNull final View source;
    @Nullable CircularReveal.OnStart onStart;
    @Nullable CircularReveal.OnEnd onEnd;

    Reversible(@NonNull View source) {
        this.source = source;
    }

    @NonNull
    public Reversible onStart(@NonNull CircularReveal.OnStart onStart) {
        this.onStart = onStart;
        return this;
    }

    @NonNull
    public Reversible onEnd(@NonNull CircularReveal.OnEnd onEnd) {
        this.onEnd = onEnd;
        return this;
    }
}