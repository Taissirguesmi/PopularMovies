package com.azhar.moviedb;


import android.os.Bundle;
import android.util.Log;
import com.movies.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class loginActivity extends AppCompatActivity {

    TabLayout tableLayout;
    ViewPager viewPager;
    FloatingActionButton fb,google,insta;
    float v=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tableLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewP);
        fb=findViewById(R.id.fb);
        google=findViewById(R.id.google);
        insta=findViewById(R.id.inta);

        tableLayout.addTab(tableLayout.newTab().setText("Login"));
        tableLayout.addTab(tableLayout.newTab().setText("SignUp"));
        tableLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final loginAdapter adapter=new loginAdapter(getSupportFragmentManager(),this,tableLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));

        fb.setTranslationY(300);
        google.setTranslationY(300);
        insta.setTranslationY(300);
        tableLayout.setTranslationY(300);


        fb.setAlpha(v);
        google.setAlpha(v);
        insta.setAlpha(v);
        tableLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        insta.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tableLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();


        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabReselected: " + tab.getPosition());
            }
        });

    }



}