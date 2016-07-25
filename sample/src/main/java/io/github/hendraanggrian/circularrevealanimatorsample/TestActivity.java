package io.github.hendraanggrian.circularrevealanimatorsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.hendraanggrian.circularrevealanimator.CircularRevealAnimator;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        CircularRevealAnimator.of(this).onCreate(R.id.layout);
    }

    @Override
    public void onBackPressed() {
        CircularRevealAnimator.of(this).onBackPressed(R.id.layout);
    }
}