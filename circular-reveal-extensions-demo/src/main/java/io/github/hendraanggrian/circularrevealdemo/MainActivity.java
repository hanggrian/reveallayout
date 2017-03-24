package io.github.hendraanggrian.circularrevealdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.hendraanggrian.circularreveal.CircularReveal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.buttonSimple) View buttonSimple;
    @BindView(R.id.buttonFrom) View buttonFrom;
    @BindView(R.id.buttonFull) View buttonFull;

    @BindView(R.id.maskSimple) View maskSimple;
    @BindView(R.id.maskTo) View maskTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonSimple.setOnClickListener(this);
        buttonFrom.setOnClickListener(this);
        buttonFull.setOnClickListener(this);
        maskSimple.setOnClickListener(this);
        maskTo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSimple:
                Animator animator1 = CircularReveal.create(maskSimple);
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        maskSimple.setVisibility(View.VISIBLE);
                    }
                });
                animator1.start();
                break;
            case R.id.maskSimple:
                Animator animator2 = CircularReveal.create(maskSimple, true);
                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        maskSimple.setVisibility(View.INVISIBLE);
                    }
                });
                animator2.start();
                break;
            case R.id.buttonFrom:
                AnimatorSet animatorSet1 = CircularReveal.createSet(buttonFrom, maskTo);
                animatorSet1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        buttonFrom.setVisibility(View.INVISIBLE);
                        maskTo.setVisibility(View.VISIBLE);
                    }
                });
                animatorSet1.start();
                break;
            case R.id.maskTo:
                AnimatorSet animatorSet2 = CircularReveal.createSet(buttonFrom, maskTo, true);
                animatorSet2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        buttonFrom.setVisibility(View.VISIBLE);
                        maskTo.setVisibility(View.INVISIBLE);
                    }
                });
                animatorSet2.start();
                break;
            case R.id.buttonFull:
                Intent intent = CircularReveal.createIntent(buttonFull, this, TestActivity.class);
                startActivity(intent);
                break;
        }
    }
}