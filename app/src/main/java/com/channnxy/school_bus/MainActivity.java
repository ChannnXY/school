package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.nav_bottom_index);
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        mBottomNavigationView.setItemIconTintList(null);
    }
}
