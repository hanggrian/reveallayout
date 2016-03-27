package com.hendraanggrian.circularreveallayoutsample;

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
import android.widget.FrameLayout;

import com.hendraanggrian.circularreveallayout.view.RevealLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentDialog extends Fragment {
    private View rootView;

    private int x, y;

    @Bind(R.id.layout) FrameLayout layout;
    @Bind(R.id.checkBox) CheckBox checkBox;
    RevealLinearLayout revealLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, rootView);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                return false;
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                revealLayout.setRevealLocation(x, y);

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