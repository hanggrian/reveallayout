package com.hendraanggrian.circularrevealactivitysample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hendraanggrian.circularrevealactivity.ActivityStaticRevealProperties;
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
    protected ActivityStaticRevealProperties getStaticCircularReveal() {
        ActivityStaticRevealProperties prop = new ActivityStaticRevealProperties();
        prop.setViewResId(R.id.layout);
        prop.setGravity(getIntent().getExtras().getInt("EXTRA_GRAVITY"));
        prop.setDuration(500);
        return prop;
    }
}