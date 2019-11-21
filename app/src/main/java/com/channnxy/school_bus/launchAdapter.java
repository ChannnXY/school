package com.channnxy.school_bus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class launchAdapter extends FragmentStatePagerAdapter {
    private int[] mImageArray;
    public launchAdapter(@NonNull FragmentManager fm, int behavior,int[] imageArray) {
        super(fm, behavior);
        mImageArray = imageArray;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return launchFragment.newInstance(position,mImageArray[position]);
    }

    @Override
    public int getCount() {
        return mImageArray.length;
    }
}
