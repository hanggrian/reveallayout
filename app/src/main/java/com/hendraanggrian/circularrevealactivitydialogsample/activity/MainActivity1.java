package com.hendraanggrian.circularrevealactivitydialogsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hendraanggrian.circularrevealactivitydialogsample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity1 extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("CircularRevealActivity Sample");

        mViewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new StaticFragment();
                case 1:
                    return new DynamicFragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Static";
                case 1:
                    return "Dynamic";
                default:
                    return "";
            }
        }
    }
}