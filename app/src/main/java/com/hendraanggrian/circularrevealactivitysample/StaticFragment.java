package com.hendraanggrian.circularrevealactivitysample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hendraanggrian.circularrevealactivity.Gravity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StaticFragment extends Fragment {
    private View rootView;
    private Bundle bundle = new Bundle();

    @Bind(R.id.button_TopLeft) Button mButton_TopLeft;
    @Bind(R.id.button_Top) Button mButton_Top;
    @Bind(R.id.button_TopRight) Button mButton_TopRight;
    @Bind(R.id.button_CenterLeft) Button mButton_CenterLeft;
    @Bind(R.id.button_Center) Button mButton_Center;
    @Bind(R.id.button_CenterRight) Button mButton_CenterRight;
    @Bind(R.id.button_BottomLeft) Button mButton_BottomLeft;
    @Bind(R.id.button_Bottom) Button mButton_Bottom;
    @Bind(R.id.button_BottomRight) Button mButton_BottomRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_static, container, false);
        ButterKnife.bind(this, rootView);

        mButton_TopLeft.setOnClickListener(mOnClickListener);
        mButton_Top.setOnClickListener(mOnClickListener);
        mButton_TopRight.setOnClickListener(mOnClickListener);
        mButton_CenterLeft.setOnClickListener(mOnClickListener);
        mButton_Center.setOnClickListener(mOnClickListener);
        mButton_CenterRight.setOnClickListener(mOnClickListener);
        mButton_BottomLeft.setOnClickListener(mOnClickListener);
        mButton_Bottom.setOnClickListener(mOnClickListener);
        mButton_BottomRight.setOnClickListener(mOnClickListener);

        return rootView;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_TopLeft:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.TOP_LEFT);
                    break;
                case R.id.button_Top:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.TOP);
                    break;
                case R.id.button_TopRight:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.TOP_RIGHT);
                    break;
                case R.id.button_CenterLeft:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.CENTER_LEFT);
                    break;
                case R.id.button_Center:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.CENTER);
                    break;
                case R.id.button_CenterRight:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.CENTER_RIGHT);
                    break;
                case R.id.button_BottomLeft:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.BOTTOM_LEFT);
                    break;
                case R.id.button_Bottom:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.BOTTOM);
                    break;
                case R.id.button_BottomRight:
                    bundle.putInt("EXTRA_GRAVITY", Gravity.BOTTOM_RIGHT);
                    break;
            }

            Intent intent = new Intent(rootView.getContext(), HelloWorldStaticActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}