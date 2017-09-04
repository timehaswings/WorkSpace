package com.wings.halfface_beta2;

import com.wings.widget.ListViewFragment;
import com.wings.widget.RecyclerViewFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Oleksii Shliama.
 */
public class PullToRefreshActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);


        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ListViewFragment();
                case 1:
                default:
//                    return new RecyclerViewFragment();
                    return new ListViewFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}
