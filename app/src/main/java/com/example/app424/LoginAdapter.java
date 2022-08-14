package com.example.app424;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginAdapter extends FragmentStatePagerAdapter {

    Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fM,  int totalTabs) {
        super(fM);
        //this.context = context;
        this.totalTabs = totalTabs;

    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                loginTapFragment loginTapFragment = new loginTapFragment();
                return loginTapFragment;
            case 0:
                signupTapFragment signupTapFragment = new signupTapFragment();
                return signupTapFragment;

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
