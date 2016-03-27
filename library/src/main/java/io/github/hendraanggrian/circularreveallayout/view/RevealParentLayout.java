package io.github.hendraanggrian.circularreveallayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import io.codetail.animation.RevealAnimator;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class RevealParentLayout extends FrameLayout implements RevealAnimator {

    private Path mRevealPath;
    private final Rect mTargetBounds = new Rect();
    private RevealInfo mRevealInfo;
    private boolean mRunning;
    private float mRadius;

    public RevealParentLayout(Context context) {
        this(context, null);
    }

    public RevealParentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealParentLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mRevealPath = new Path();
    }

    @Override
    public void onRevealAnimationStart() {
        mRunning = true;
    }

    @Override
    public void onRevealAnimationEnd() {
        mRunning = false;
        invalidate(mTargetBounds);
    }

    @Override
    public void onRevealAnimationCancel() {
        onRevealAnimationEnd();
    }

    @Override
    public void setRevealRadius(float radius) {
        mRadius = radius;
        mRevealInfo.getTarget().getHitRect(mTargetBounds);
        invalidate(mTargetBounds);
    }

    @Override
    public float getRevealRadius() {
        return mRadius;
    }

    @Override
    public void attachRevealInfo(RevealInfo info) {
        mRevealInfo = info;
    }

    @Override
    public SupportAnimator startReverseAnimation() {
        if (mRevealInfo != null && mRevealInfo.hasTarget() && !mRunning) {
            return ViewAnimationUtils.createCircularReveal(mRevealInfo.getTarget(),
                    mRevealInfo.centerX, mRevealInfo.centerY,
                    mRevealInfo.endRadius, mRevealInfo.startRadius);
        }
        return null;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (mRunning && child == mRevealInfo.getTarget()) {
            final int state = canvas.save();

            mRevealPath.reset();
            mRevealPath.addCircle(mRevealInfo.centerX, mRevealInfo.centerY, mRadius, Path.Direction.CW);

            canvas.clipPath(mRevealPath);

            boolean isInvalided = super.drawChild(canvas, child, drawingTime);

            canvas.restoreToCount(state);

            return isInvalided;
        }

        return super.drawChild(canvas, child, drawingTime);
    }

}
