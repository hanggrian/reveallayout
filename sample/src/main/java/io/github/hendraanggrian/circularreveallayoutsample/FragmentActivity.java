package io.github.hendraanggrian.circularreveallayoutsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentActivity extends Fragment {
    private View rootView;
    private Bundle bundle = new Bundle();

    @Bind(R.id.layout) FrameLayout layout;
    @Bind(R.id.checkBox) CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this, rootView);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                bundle.putInt("EXTRA_X", (int) event.getRawX());
                bundle.putInt("EXTRA_Y", (int) event.getRawY());
                return false;
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putBoolean("EXTRA_ANIMATE_EXIT", checkBox.isChecked());

                Intent intent = new Intent(rootView.getContext(), HelloWorldActivity.class);
                intent.putExtras(bundle);
                rootView.getContext().startActivity(intent);
            }
        });

        return rootView;
    }
}