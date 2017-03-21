package io.github.hendraanggrian.circularreveal;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
final class RadiusUtils {

    static float calculateEnd(@NonNull View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int dx = Math.max(cx, view.getWidth() - cx);
        int dy = Math.max(cy, view.getHeight() - cy);
        return (float) Math.hypot(dx, dy);
    }
}