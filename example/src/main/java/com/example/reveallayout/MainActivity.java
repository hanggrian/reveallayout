package com.example.reveallayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindViews;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindViews({R.id.button_main_simple, R.id.button_main_custom}) Button[] buttons;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (Button button : buttons)
            button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, v.getId() == R.id.button_main_simple
                ? SimpleActivity.class
                : CustomActivity1.class));
    }
}