package io.github.hendraanggrian.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;

import io.codetail.animation.ViewAnimationUtils;
import io.github.hendraanggrian.circularreveal.internal.AnimatorPath;
import io.github.hendraanggrian.circularreveal.internal.PathEvaluator;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public final class CircularReveal {

    private static final String EXTRA_STARTX = "io.github.hendraanggrian.circularreveal.CircularReveal.STARTX";
    private static final String EXTRA_STARTY = "io.github.hendraanggrian.circularreveal.CircularReveal.STARTY";
    private static final String EXTRA_TARGET = "io.github.hendraanggrian.circularreveal.CircularReveal.EXTRA_TARGET";

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
    public static AnimatorSet createSet(@NonNull Context context, @NonNull View source, @NonNull View target) {
        return createSet(context, source, target, false);
    }

    @NonNull
    public static AnimatorSet createSet(@NonNull Context context, @NonNull View source, @NonNull View target, boolean reverse) {
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
        target.setX(srcRect.left - trgRect.centerX());
        target.setY(srcRect.top - trgRect.centerY());

        final float c0X = srcRect.centerX() - trgRect.centerX();
        final float c0Y = srcRect.centerY() - trgRect.centerY();

        AnimatorPath path = new AnimatorPath();
        if (!reverse) {
            path.moveTo(c0X, c0Y);
            path.curveTo(c0X, c0Y, 0, c0Y, 0, 0);
        } else {
            path.moveTo(trgRect.left, trgRect.top);
            path.curveTo(trgRect.left, trgRect.top, 0, c0Y, c0X, c0Y);
        }
        ObjectAnimator pathAnimator = ObjectAnimator.ofObject(context, "maskLocation", new PathEvaluator(), path.getPoints().toArray());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(circularReveal, pathAnimator);
        set.setInterpolator(new FastOutSlowInInterpolator());
        return set;
    }

    @NonNull
    public static Intent createIntent(@NonNull View source, @IdRes int targetId, @NonNull Intent intent) {
        return intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtra(EXTRA_STARTX, Start.CENTER.getX(source))
                .putExtra(EXTRA_STARTY, Start.CENTER.getY(source))
                .putExtra(EXTRA_TARGET, targetId);
    }

    @NonNull
    public static void onCreate(@NonNull Activity activity, @NonNull Intent intent) {
        /*Rect rect = intent.getParcelableExtra(EXTRA_RECT);
        int targetId = intent.getIntExtra(EXTRA_TARGET, -1);
        final View target = activity.findViewById(targetId);
        target.post(new Runnable() {
            @Override
            public void run() {
                create(
                        target,
                        Start.CENTER.getX()
                        );
            }
        });*/
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
}