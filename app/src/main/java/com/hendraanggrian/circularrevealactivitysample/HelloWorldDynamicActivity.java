package com.hendraanggrian.circularrevealactivitysample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hendraanggrian.circularrevealactivity.DynamicCircularRevealActivity;
import com.hendraanggrian.circularrevealactivity.RevealProperties;

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
    protected RevealProperties getRevealProperties() {
        RevealProperties prop = new RevealProperties();
        prop.setViewResId(R.id.layout);
        prop.setX(getIntent().getExtras().getInt("EXTRA_X"));
        prop.setY(getIntent().getExtras().getInt("EXTRA_Y"));
        prop.setDuration(500); // default value if not defined is 500
        prop.setAnimateExit(true); // default value if not defined is false
        return prop;
    }
}