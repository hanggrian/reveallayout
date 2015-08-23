package com.hendraanggrian.circularrevealactivitydialogsample.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.hendraanggrian.circularreveal.RevealProperties;
import com.hendraanggrian.circularreveal.dialog.CircularRevealDialog;
import com.hendraanggrian.circularrevealactivitydialogsample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {

    private int x, y;

    @Bind(R.id.view) View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                return false;
            }
        });

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CircularRevealDialog dialog = new CircularRevealDialog(MainActivity2.this, R.style.DialogTheme) {
                    @Override
                    public int getLayoutResId() {
                        return R.layout.dialog_hello_world;
                    }

                    @Override
                    public RevealProperties getProperties() {
                        RevealProperties prop = new RevealProperties();
                        prop.setViewResId(R.id.layout);
                        prop.setX(x);
                        prop.setY(y);
                        prop.setDuration(500); // default value if not defined is 500
                        prop.setAnimateExit(true); //default value if not defined is false
                        return prop;
                    }
                };

                Button button = (Button) dialog.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}