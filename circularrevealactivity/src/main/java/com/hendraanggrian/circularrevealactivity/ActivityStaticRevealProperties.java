package com.hendraanggrian.circularrevealactivity;

public class ActivityStaticRevealProperties {

    private int viewResId;
    private int gravity;
    private int duration;

    public ActivityStaticRevealProperties() {

    }

    public ActivityStaticRevealProperties(int viewResId, int gravity, int duration) {
        this.setViewResId(viewResId);
        this.setGravity(gravity);
        this.setDuration(duration);
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}