package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.sewaseven.additional.UID;

public class Home extends AppCompatActivity {
    private LinearLayout btnServiceProfile;
    private ImageButton goToMenue;
    private TabLayout homeTabs;
    private TabItem announcements;
    private TabItem services;
    private ViewPager homeViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        tabs
        homeTabs = findViewById(R.id.home_tabs);
        announcements = findViewById(R.id.home_announcements);
        services = findViewById(R.id.home_services);
        homeViewPager = findViewById(R.id.home_view_pager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), homeTabs.getTabCount());
        homeViewPager.setAdapter(pagerAdapter);


        homeTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homeViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        goToMenue = findViewById(R.id.profile);
        goToMenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoMainMenue = new Intent(Home.this, Mainmenu.class);
                startActivity(gotoMainMenue);

            }
        });

//        btnServiceProfile =findViewById(R.id.linktoProfileA);
//        btnServiceProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent servicePage = new Intent(HomeAnnouncements.this,ServicePage.class);
//                startActivity(servicePage);
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}