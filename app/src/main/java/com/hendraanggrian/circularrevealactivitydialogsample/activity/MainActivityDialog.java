package com.hendraanggrian.circularrevealactivitydialogsample.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.hendraanggrian.circularrevealactivitydialogsample.R;
import com.hendraanggrian.circularreveallayout.view.RevealLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityDialog extends Fragment {
    private View rootView;

    private int x, y;

    @Bind(R.id.view) View view;
    @Bind(R.id.checkBox) CheckBox checkBox;
    RevealLinearLayout revealLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_main_dialog, container, false);
        ButterKnife.bind(this, rootView);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*RevealProperties prop = new RevealProperties();
                prop.setViewResId(R.id.layout);
                prop.setX(x);
                prop.setY(y);
                prop.setDuration(500); // default value if not defined is 500
                prop.setAnimateExit(true); //default value if not defined is false

                final CircularRevealDialog dialog = new CircularRevealDialog(rootView.getContext(), R.style.DialogTheme, R.layout.dialog_helloworld, prop);

                Button button = (Button) dialog.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();*/

                final Dialog dialog = new Dialog(rootView.getContext(), R.style.DialogTheme) {
                    @Override
                    public void cancel() {
                        if (checkBox.isChecked())
                            revealLayout.animateExit(this);
                        else
                            super.dismiss();
                    }
                };
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_helloworld);

                revealLayout = ButterKnife.findById(dialog, R.id.revealLayout);
                revealLayout.setLocation(x, y);
                revealLayout.isDialog();

                Button button = ButterKnife.findById(dialog, R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        return rootView;
    }
}