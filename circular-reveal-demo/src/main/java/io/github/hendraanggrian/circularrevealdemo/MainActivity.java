package io.github.hendraanggrian.circularrevealdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.hendraanggrian.circularreveal.CircularReveal;
import io.github.hendraanggrian.circularreveal.CircularRevealAnimator;
import io.github.hendraanggrian.circularreveal.PathPoint;
import io.github.hendraanggrian.circularreveal.Reversible;

public class MainActivity extends AppCompatActivity implements CircularRevealAnimator.OnReveal, View.OnClickListener {

    @BindView(R.id.buttonSimple) View buttonSimple;
    @BindView(R.id.buttonFrom) View buttonFrom;
    @BindView(R.id.buttonFull) View buttonFull;

    @BindView(R.id.maskSimple) View maskSimple;
    @BindView(R.id.maskTo) View maskTo;

    private Reversible reversible;

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
    public void setMaskLocation(PathPoint location) {
        maskTo.setX(location.mX);
        maskTo.setY(location.mY);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSimple:
                reversible = CircularReveal.with(maskSimple)
                        .onStart(new CircularReveal.OnStart() {
                            @Override
                            public void onStart(@NonNull View view) {
                                view.setVisibility(View.VISIBLE);
                            }
                        })
                        .reveal();
                break;
            case R.id.maskSimple:
                reversible.onEnd(new CircularReveal.OnEnd() {
                    @Override
                    public void onEnd(@NonNull View view) {
                        view.setVisibility(View.GONE);
                    }
                }).reverse();
                break;
            case R.id.buttonFrom:
                CircularRevealAnimator.of(this).reveal(buttonFrom, maskTo);
                break;
            case R.id.maskTo:
                CircularRevealAnimator.of(this).reverse(buttonFrom, maskTo);
                break;
            case R.id.buttonFull:
                CircularRevealAnimator.of(this).startActivity(new Intent(this, TestActivity.class), buttonFull);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (CircularRevealAnimator.of(this).isRevealed(maskSimple))
            CircularRevealAnimator.of(this).reverse(maskSimple, maskSimple.getWidth() / 2, maskSimple.getHeight() / 2);
        else if (CircularRevealAnimator.of(this).isRevealed(buttonFrom, maskTo))
            CircularRevealAnimator.of(this).reverse(buttonFrom, maskTo);
        else
            super.onBackPressed();
    }
}