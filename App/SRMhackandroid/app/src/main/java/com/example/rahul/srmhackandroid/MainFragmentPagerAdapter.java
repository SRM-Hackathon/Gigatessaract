package com.example.rahul.srmhackandroid;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.rahul.srmhackandroid.Fragments.CameraFragment;
import com.example.rahul.srmhackandroid.Fragments.ConnectFragment;
import com.example.rahul.srmhackandroid.Fragments.HistoryFragment;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HistoryFragment();
        } else if(position == 1){
            return new CameraFragment();
        } else {
            return new ConnectFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
