package com.hendraanggrian.circularrevealactivitysample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hendraanggrian.circularrevealactivity.ActivityDynamicRevealProperties;
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
    protected ActivityDynamicRevealProperties getDynamicCircularReveal() {
        ActivityDynamicRevealProperties prop = new ActivityDynamicRevealProperties();
        prop.setViewResId(R.id.layout);
        prop.setX(getIntent().getExtras().getInt("EXTRA_X"));
        prop.setY(getIntent().getExtras().getInt("EXTRA_Y"));
        prop.setDuration(500);
        return prop;
    }
}