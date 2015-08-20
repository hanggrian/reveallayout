package com.hendraanggrian.circularrevealactivitysample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hendraanggrian.circularrevealactivity.DynamicCircularReveal;
import com.hendraanggrian.circularrevealactivity.DynamicCircularRevealActivity;

public class HelloWorldDynamicActivity extends DynamicCircularRevealActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do not setContentView(...) here, instead put the layout res id below

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("This is Dynamic");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hello_world;
    }

    @Override
    protected DynamicCircularReveal getDynamicCircularReveal() {
        DynamicCircularReveal spec = new DynamicCircularReveal();
        spec.setViewResId(R.id.layout);
        spec.setX(getIntent().getExtras().getInt("EXTRA_X"));
        spec.setY(getIntent().getExtras().getInt("EXTRA_Y"));
        spec.setDuration(500);
        return spec;
    }
}