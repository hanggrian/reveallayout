package com.hendraanggrian.circularreveallayoutsample;

import android.view.View;

/**
 * Created by hendraanggrian on 05/01/16.
 */
public class ViewUtils {

    public static void setOnClickListeners(View.OnClickListener onClickListener, View... views) {
        for (View view : views)
            view.setOnClickListener(onClickListener);
    }

}
