package com.hendraanggrian.circularrevealactivitysample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hendraanggrian.circularrevealactivity.StaticCircularReveal;
import com.hendraanggrian.circularrevealactivity.StaticCircularRevealActivity;

public class HelloWorldStaticActivity extends StaticCircularRevealActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do not setContentView(...) here, instead put the layout res id below

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("This is Static");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hello_world;
    }

    @Override
    protected StaticCircularReveal getStaticCircularReveal() {
        StaticCircularReveal spec = new StaticCircularReveal();
        spec.setViewResId(R.id.layout);
        spec.setGravity(getIntent().getExtras().getInt("EXTRA_GRAVITY"));
        spec.setDuration(500);
        return spec;
    }
}