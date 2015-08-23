package com.hendraanggrian.circularrevealactivitydialogsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.hendraanggrian.circularrevealactivitydialogsample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DynamicFragment extends Fragment {
    private View rootView;
    private Bundle bundle = new Bundle();

    @Bind(R.id.view) View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dynamic, container, false);
        ButterKnife.bind(this, rootView);

        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                bundle.putInt("EXTRA_X", (int) event.getRawX());
                bundle.putInt("EXTRA_Y", (int) event.getRawY());
                return false;
            }
        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), HelloWorldDynamicActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return rootView;
    }
}