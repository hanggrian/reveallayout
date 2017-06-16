package com.hendraanggrian.reveallayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.codetail.animation.ViewAnimationUtils;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class RevealableLayoutImpl implements RevealableLayout {

    private final int revealId;
    private final int revealDuration;
    private final RevealCenter revealCenter;

    public RevealableLayoutImpl(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RevealLayout);
        revealId = a.getResourceId(R.styleable.RevealLayout_revealId, -1);
        revealDuration = a.getInt(R.styleable.RevealLayout_revealDuration, -1);
        revealCenter = RevealCenter.valueOf(a.getInt(R.styleable.RevealLayout_revealCenter, RevealCenter.CENTER.attrId));
        a.recycle();
    }

    public void addView(@NonNull final View child) {
        if (child.getId() == revealId) {
            child.post(new Runnable() {
                @Override
                public void run() {
                    Animator animator = animate(child, revealCenter);
                    if (revealDuration != -1) {
                        animator.setDuration(revealDuration);
                    }
                    animator.start();
                }
            });
        }
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view) {
        return animate(view, false);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter) {
        return animate(view, revealCenter, false);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, int startX, int startY) {
        return animate(view, startX, startY, false);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, boolean reverse) {
        return animate(view, RevealCenter.CENTER, reverse);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, @NonNull RevealCenter revealCenter, boolean reverse) {
        return animate(view, revealCenter.getX(view), revealCenter.getY(view), reverse);
    }

    @NonNull
    @Override
    public Animator animate(@NonNull View view, int startX, int startY, boolean reverse) {
        return ViewAnimationUtils.createCircularReveal(
                view,
                startX,
                startY,
                reverse ? getEndRadius(view) : 0,
                reverse ? 0 : getEndRadius(view)
        );
    }

    @NonNull
    @Override
    public Collection<Animator> animateTo(@NonNull View source, @NonNull View target) {
        return animateTo(source, target, false);
    }

    @NonNull
    @Override
    public Collection<Animator> animateTo(@NonNull View source, @NonNull final View target, boolean reverse) {
        List<Animator> animators = new ArrayList<>();
        // Cancel all concurrent events on view
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            source.cancelPendingInputEvents();
        }

        // Coordinates of circle initial point
        final ViewGroup parent = (ViewGroup) target.getParent();
        final Rect srcRect = createRect(parent, source);
        final Rect trgRect = createRect(parent, target);

        animators.add(ViewAnimationUtils.createCircularReveal(
                target,
                trgRect.centerX(),
                trgRect.centerY(),
                reverse ? getEndRadius(target) : srcRect.width() / 2,
                reverse ? srcRect.width() / 2 : getEndRadius(target),
                View.LAYER_TYPE_HARDWARE
        ));

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
        animators.add(ObjectAnimator.ofObject(new OnSetListener() {
            @Override
            public void setMaskLocation(@NonNull PathPoint location) {
                target.setX(location.mX);
                target.setY(location.mY);
            }
        }, "maskLocation", new PathEvaluator(), path.getPoints().toArray()));

        return animators;
    }

    private interface OnSetListener {
        void setMaskLocation(@NonNull PathPoint location);
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