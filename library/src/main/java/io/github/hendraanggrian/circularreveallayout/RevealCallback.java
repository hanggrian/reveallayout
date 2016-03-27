package io.github.hendraanggrian.circularreveallayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

/**
 * Created by hendraanggrian on 3/28/16.
 */
public interface RevealCallback {

    void animateOpen(Context context);

    void animateExit(Activity activity);

    void animateExit(Dialog dialog);

    boolean isDialog();

    void setRevealLocation(int x, int y);

    int getRevealX();

    int getRevealY();

    int getOpenDuration();

    int getExitDuration();

}