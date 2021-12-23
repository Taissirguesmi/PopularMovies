package com.azhar.moviedb;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class loginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public loginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                login_tab login_tab=new login_tab();
                return login_tab;
            case 1:
                signup_tab signup_tab=new signup_tab();
                return signup_tab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
