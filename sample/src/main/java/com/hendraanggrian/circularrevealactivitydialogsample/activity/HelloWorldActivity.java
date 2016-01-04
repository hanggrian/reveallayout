package com.hendraanggrian.circularrevealactivitydialogsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hendraanggrian.circularreveallayout.view.RevealFrameLayout;
import com.hendraanggrian.circularrevealactivitydialogsample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HelloWorldActivity extends AppCompatActivity {

    @Bind(R.id.revealLayout) RevealFrameLayout revealLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloworld);
        ButterKnife.bind(this);

        revealLayout.setLocation(getIntent().getExtras().getInt("EXTRA_X"), getIntent().getExtras().getInt("EXTRA_Y"));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hello World!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (getIntent().getExtras().getBoolean("EXTRA_ANIMATE_EXIT"))
            revealLayout.animateExit(this);
        else
            super.onBackPressed();
    }
}