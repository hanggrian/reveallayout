package io.github.hendraanggrian.circularrevealdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.hendraanggrian.circularreveal.CircularReveal;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.layout) ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        layout.post(new Runnable() {
            @Override
            public void run() {
                CircularReveal.createFromIntent(getIntent(), layout).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Animator animator = CircularReveal.createFromIntent(getIntent(), layout, true);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                layout.setVisibility(View.INVISIBLE);
                finish();
                overridePendingTransition(0, 0);
            }
        });
        animator.start();
    }
}