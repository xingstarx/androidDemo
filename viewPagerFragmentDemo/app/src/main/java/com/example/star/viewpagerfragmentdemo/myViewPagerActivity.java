package com.example.star.viewpagerfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;


public class myViewPagerActivity extends ActionBarActivity {

    ViewPager mViewPager;
    PagerSlidingTabStrip mPagerSlidingTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mPagerSlidingTabStrip.setViewPager(mViewPager);


        mViewPager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //    class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] title = {"项目一", "项目二", "项目三"};

        Fragment fragment1;
        Fragment fragment2;
        Fragment fragment3;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment1 = MyTestFragment.newInstance(R.layout.fragment_1);
                    return fragment1;
                case 1:
                    fragment2 = MyTestFragment.newInstance(R.layout.fragment_2);
                    return fragment2;
                case 2:
                    fragment3 = MyTestFragment.newInstance(R.layout.fragment_3);
                    return fragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return title.length;
        }
//
//        @Override
//        public int getPageIconResId(int i) {
//            return R.drawable.icon;
//        }

        @Override
        public CharSequence getPageTitle(int position) {

            return title[position];
        }

    }
}
