package com.example.musicplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 30781 on 2017/1/3.
 */
public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public FragAdapter(FragmentManager frgManager,List<Fragment> fragments)
    {
        super(frgManager);
        mFragments=fragments;
    }

    @Override
    public Fragment getItem(int arg0)
    {
        return mFragments.get(arg0);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }
}
