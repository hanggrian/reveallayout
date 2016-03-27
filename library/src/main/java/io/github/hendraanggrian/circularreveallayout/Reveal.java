package io.github.hendraanggrian.circularreveallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import io.github.hendraanggrian.circularrevealactivitydialog.R;

import io.codetail.animation.SupportAnimator;

/**
 * Created by hendraanggrian on 3/28/16.
 */
public class Reveal {

    public static final int DEFAULT_DURATION = 500;

    private SupportAnimator animator;
    private boolean dialog;
    private int x;
    private int y;
    private int durationOpen;
    private int durationExit;

    public Reveal(Context context, AttributeSet attrs, int width, int height) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.reveal);
        dialog = typedArray.getInteger(R.styleable.reveal_type, 0) == 1;
        int location = typedArray.getInteger(R.styleable.reveal_location, 0);
        if (location != 0)
            switch (RevealLocation.get(location)) {
                case TOP_LEFT:
                    x = 0;
                    y = 0;
                    break;
                case TOP:
                    x = width / 2;
                    y = 0;
                    break;
                case TOP_RIGHT:
                    x = width;
                    y = 0;
                    break;
                case LEFT:
                    x = 0;
                    y = height / 2;
                    break;
                case CENTER:
                    x = width / 2;
                    y = height / 2;
                    break;
                case RIGHT:
                    x = width;
                    y = height / 2;
                    break;
                case BOTTOM_LEFT:
                    x = 0;
                    y = height;
                    break;
                case BOTTOM:
                    x = width / 2;
                    y = height;
                    break;
                case BOTTOM_RIGHT:
                    x = width;
                    y = height;
                    break;
            }
        durationOpen = typedArray.getInteger(R.styleable.reveal_duration_open, DEFAULT_DURATION);
        durationExit = typedArray.getInteger(R.styleable.reveal_duration_exit, DEFAULT_DURATION);
        typedArray.recycle();
    }

    public SupportAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(SupportAnimator animator) {
        this.animator = animator;
    }

    public boolean isDialog() {
        return dialog;
    }

    public void setDialog(boolean dialog) {
        this.dialog = dialog;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDurationOpen() {
        return durationOpen;
    }

    public void setDurationOpen(int durationOpen) {
        this.durationOpen = durationOpen;
    }

    public int getDurationExit() {
        return durationExit;
    }

    public void setDurationExit(int durationExit) {
        this.durationExit = durationExit;
    }
}