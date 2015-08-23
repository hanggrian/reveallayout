package com.hendraanggrian.circularreveal;

public class RevealProperties {

    private int viewResId;
    private int gravity;
    private int x;
    private int y;
    private int duration;
    private boolean animateExit;

    public RevealProperties() {

    }

    public int getViewResId() {
        return viewResId;
    }

    public void setViewResId(int viewResId) {
        this.viewResId = viewResId;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isAnimateExit() {
        return animateExit;
    }

    public void setAnimateExit(boolean animateExit) {
        this.animateExit = animateExit;
    }
}