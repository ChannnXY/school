package com.channnxy.school_bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private ViewPager mvp_index;
    private List<Fragment> mFragmentsList;
    private MenuItem mMenuItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.nav_bottom_index);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new navigationItemSelectedListener());
        initBottomNavigation();

        mvp_index = findViewById(R.id.vp_index);
        mvp_index.addOnPageChangeListener(new pageChangeListener());
        initViewPagerAdapter();
    }



    //初始化页面适配器
    private void initViewPagerAdapter() {
        mFragmentsList = new ArrayList<>();
        mFragmentsList.add(new fragment_journey());
        mFragmentsList.add(new fragment_index());
        mFragmentsList.add(new fragment_users());
        navFragmentAdapter adapter = new navFragmentAdapter(getSupportFragmentManager(),0,mFragmentsList);
        mvp_index.setAdapter(adapter);
        mvp_index.setCurrentItem(1);
    }

    //fragment适配器
    private class navFragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> mList;
        public navFragmentAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> list) {
            super(fm,behavior);
            this.mList = list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

    //    底部导航栏样式初始化
    private void initBottomNavigation() {
        mBottomNavigationView.setItemIconTintList(null);
    }

    //    底部导航栏监听
    private class navigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_bottom_index_journey:
                {
                    mvp_index.setCurrentItem(0);
                    break;
                }
                case R.id.nav_bottom_index_index:{
                    mvp_index.setCurrentItem(1);
                    break;
                }
                case R.id.nav_bottom_index_users:{
                    mvp_index.setCurrentItem(2);
                    break;
                }
                default:break;
            }
            return false;
        }
    }

//    viewPager翻页监听
    private class pageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(mMenuItem!=null){
                mMenuItem.setChecked(false);
            }else {
                mMenuItem = mBottomNavigationView.getMenu().getItem(1);
                mMenuItem.setChecked(true);
            }
            mMenuItem = mBottomNavigationView.getMenu().getItem(position);
            mMenuItem.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
