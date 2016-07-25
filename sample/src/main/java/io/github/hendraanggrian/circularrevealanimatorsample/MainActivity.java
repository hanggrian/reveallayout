package io.github.hendraanggrian.circularrevealanimatorsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.hendraanggrian.circularrevealanimator.PathPoint;
import io.github.hendraanggrian.circularrevealanimator.CircularRevealAnimator;

public class MainActivity extends AppCompatActivity implements CircularRevealAnimator.OnReveal, View.OnClickListener {

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
    public void setMaskLocation(PathPoint location) {
        maskTo.setX(location.mX);
        maskTo.setY(location.mY);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSimple:
                CircularRevealAnimator.of(this).reveal(maskSimple, maskSimple.getWidth() / 2, maskSimple.getHeight() / 2);
                break;
            case R.id.maskSimple:
                CircularRevealAnimator.of(this).reverse(maskSimple, maskSimple.getWidth() / 2, maskSimple.getHeight() / 2);
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