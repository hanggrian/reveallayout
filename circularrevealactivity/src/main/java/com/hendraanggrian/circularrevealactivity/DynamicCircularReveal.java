package com.hendraanggrian.circularrevealactivity;

public class DynamicCircularReveal {

    private int viewResId;
    private int x;
    private int y;
    private int duration;

    public DynamicCircularReveal() {

    }

    public DynamicCircularReveal(int viewResId, int x, int y, int duration) {
        this.setViewResId(viewResId);
        this.setX(x);
        this.setY(y);
        this.setDuration(duration);
    }

    public int getViewResId() {
        return viewResId;
    }

    public void setViewResId(int viewResId) {
        this.viewResId = viewResId;
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
}