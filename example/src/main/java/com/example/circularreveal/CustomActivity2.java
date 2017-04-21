package com.example.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hendraanggrian.widget.CircularRevealFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomActivity2 extends AppCompatActivity {

    public final static String EXTRA_RECT = "com.example.circularreveal.CustomActivity2";

    @BindView(R.id.circularrevealframelayout) CircularRevealFrameLayout layout;
    @BindView(R.id.textview) TextView textView;
    private Rect rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom2);
        ButterKnife.bind(this);
        rect = getIntent().getParcelableExtra(EXTRA_RECT);
        textView.post(new Runnable() {
            @Override
            public void run() {
                layout.create(textView, rect.centerX(), rect.centerY()).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Animator animator = layout.create(textView, rect.centerX(), rect.centerY(), true);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textView.setVisibility(View.INVISIBLE);
                finish();
                overridePendingTransition(0, 0);
            }
        });
        animator.start();
    }
}