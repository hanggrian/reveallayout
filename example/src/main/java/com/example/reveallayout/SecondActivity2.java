package com.example.reveallayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.bundler.BindExtra;
import com.hendraanggrian.bundler.Bundler;
import com.hendraanggrian.kota.content.Themes;
import com.hendraanggrian.reveallayout.Radius;
import com.hendraanggrian.reveallayout.RevealableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class SecondActivity2 extends AppCompatActivity {

    public static final String EXTRA_RECT = "com.example.circularreveal.CustomActivity2";

    @BindExtra(EXTRA_RECT) Rect rect;
    @BindView(R.id.revealLayout) RevealableLayout revealLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.layout) ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundler.bindExtras(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        layout.post(new Runnable() {
            @Override
            public void run() {
                Animator animator = revealLayout.reveal(layout, rect.centerX(), rect.centerY(), Radius.GONE_ACTIVITY);
                animator.setDuration(1000);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            getWindow().setStatusBarColor(Themes.getColor(getTheme(), R.attr.colorAccent, true));
                        }
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Animator animator = revealLayout.reveal(layout, rect.centerX(), rect.centerY(), Radius.ACTIVITY_GONE);
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(Themes.getColor(getTheme(), R.attr.colorPrimaryDark, true));
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layout.setVisibility(View.INVISIBLE);
                finish();
                overridePendingTransition(0, 0);
            }
        });
        animator.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}