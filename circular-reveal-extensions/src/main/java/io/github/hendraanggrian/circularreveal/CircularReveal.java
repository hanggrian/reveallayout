package io.github.hendraanggrian.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;

import io.codetail.animation.ViewAnimationUtils;
import io.github.hendraanggrian.circularreveal.internal.AnimatorPath;
import io.github.hendraanggrian.circularreveal.internal.PathEvaluator;
import io.github.hendraanggrian.circularreveal.internal.PathPoint;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public final class CircularReveal {

    private static final String EXTRA_RECT = "io.github.hendraanggrian.circularreveal.CircularReveal.EXTRA_RECT";

    private CircularReveal() {
    }

    @NonNull
    public static Animator create(@NonNull View view) {
        return create(view, false);
    }

    @NonNull
    public static Animator create(@NonNull View view, @NonNull Start start) {
        return create(view, start, false);
    }

    @NonNull
    public static Animator create(@NonNull View view, int startX, int startY) {
        return create(view, startX, startY, false);
    }

    @NonNull
    public static Animator create(@NonNull View view, boolean reverse) {
        return create(view, Start.CENTER, reverse);
    }

    @NonNull
    public static Animator create(@NonNull View view, @NonNull Start start, boolean reverse) {
        return create(view, start.getX(view), start.getY(view), reverse);
    }

    @NonNull
    public static Animator create(@NonNull View view, int startX, int startY, boolean reverse) {
        return ViewAnimationUtils.createCircularReveal(
                view,
                startX,
                startY,
                reverse ? getEndRadius(view) : 0,
                reverse ? 0 : getEndRadius(view)
        );
    }

    @NonNull
    public static AnimatorSet createSet(@NonNull View source, @NonNull View target) {
        return createSet(source, target, false);
    }

    @NonNull
    public static AnimatorSet createSet(@NonNull View source, @NonNull final View target, boolean reverse) {
        // Cancel all concurrent events on view
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            source.cancelPendingInputEvents();

        // Coordinates of circle initial point
        final ViewGroup parent = (ViewGroup) target.getParent();
        final Rect srcRect = createRect(parent, source);
        final Rect trgRect = createRect(parent, target);

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                target,
                trgRect.centerX(),
                trgRect.centerY(),
                reverse ? getEndRadius(target) : srcRect.width() / 2,
                reverse ? srcRect.width() / 2 : getEndRadius(target),
                View.LAYER_TYPE_HARDWARE
        );

        // Put Mask view at circle 8initial points
        //target.setX(srcRect.left - trgRect.centerX());
        //target.setY(srcRect.top - trgRect.centerY());

        final float c0X = srcRect.centerX() - trgRect.centerX();
        final float c0Y = srcRect.centerY() - trgRect.centerY();
        AnimatorPath path = new AnimatorPath();
        if (!reverse) {
            path.moveTo(c0X, c0Y);
            path.curveTo(c0X, c0Y, 0, c0Y, trgRect.left, trgRect.top);
        } else {
            path.moveTo(trgRect.left, trgRect.top);
            path.curveTo(trgRect.left, trgRect.top, 0, c0Y, c0X, c0Y);
        }
        ObjectAnimator pathAnimator = ObjectAnimator.ofObject(new OnSetListener() {
            @Override
            public void setMaskLocation(@NonNull PathPoint location) {
                target.setX(location.mX);
                target.setY(location.mY);
            }
        }, "maskLocation", new PathEvaluator(), path.getPoints().toArray());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(circularReveal, pathAnimator);
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.setDuration(400);
        return set;
    }

    @NonNull
    public static Intent createIntent(@NonNull View source, @NonNull Context context, @NonNull Class<?> cls) {
        return createIntent(source, new Intent(context, cls));
    }

    @NonNull
    public static Intent createIntent(@NonNull View source, @NonNull Intent intent) {
        return intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra(EXTRA_RECT, createRect((ViewGroup) source.getParent(), source));
    }

    @NonNull
    public static Animator createFromIntent(@NonNull Intent intent, @NonNull View target) {
        return createFromIntent(intent, target, false);
    }

    @NonNull
    public static Animator createFromIntent(@NonNull Intent intent, @NonNull final View target, boolean reverse) {
        if (!intent.hasExtra(EXTRA_RECT))
            throw new RuntimeException("This activity is not started using CircularReveal.createIntent()!");
        Rect rect = intent.getParcelableExtra(EXTRA_RECT);
        return create(
                target,
                rect.centerX(),
                rect.centerY(),
                reverse
        );
    }

    @NonNull
    private static Rect createRect(@NonNull ViewGroup parent, @NonNull View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        parent.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }

    private static float getEndRadius(@NonNull View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int dx = Math.max(cx, view.getWidth() - cx);
        int dy = Math.max(cy, view.getHeight() - cy);
        return (float) Math.hypot(dx, dy);
    }

    private interface OnSetListener {
        void setMaskLocation(@NonNull PathPoint location);
    }
}