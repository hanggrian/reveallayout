package com.example.reveallayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.widget.RevealFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomActivity1 extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.circularrevealframelayout) RevealFrameLayout layout;
    @BindView(R.id.buttonSimple) View buttonSimple;
    @BindView(R.id.buttonFrom) View buttonFrom;
    @BindView(R.id.buttonFull) View buttonFull;

    @BindView(R.id.maskSimple) View maskSimple;
    @BindView(R.id.maskTo) View maskTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom1);
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
                Animator animator1 = layout.animate(maskSimple);
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        maskSimple.setVisibility(View.VISIBLE);
                    }
                });
                animator1.start();
                break;
            case R.id.maskSimple:
                Animator animator2 = layout.animate(maskSimple, true);
                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        maskSimple.setVisibility(View.INVISIBLE);
                    }
                });
                animator2.start();
                break;
            case R.id.buttonFrom:
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.setInterpolator(new FastOutSlowInInterpolator());
                animatorSet1.playTogether(layout.animateTo(buttonFrom, maskTo));
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
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(layout.animateTo(buttonFrom, maskTo, true));
                animatorSet2.setInterpolator(new FastOutSlowInInterpolator());
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
                Intent intent = new Intent(this, CustomActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra(CustomActivity2.EXTRA_RECT, createRect((ViewGroup) buttonFull.getParent(), buttonFull));
                startActivity(intent);
                break;
        }
    }

    @NonNull
    private static Rect createRect(@NonNull ViewGroup parent, @NonNull View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        parent.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }
}