package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class launchActivity extends AppCompatActivity {
    private int[] launchImageArray = {R.drawable.bg_launch_first,R.drawable.bg_launch_second,R.drawable.bg_launch_third};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ViewPager mvp_launch = findViewById(R.id.vp_launch);
        launchAdapter mAdapter = new launchAdapter(getSupportFragmentManager(),0,launchImageArray);
        mvp_launch.setAdapter(mAdapter);
        mvp_launch.setCurrentItem(0);
    }
}
