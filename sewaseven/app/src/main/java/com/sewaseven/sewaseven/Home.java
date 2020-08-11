package com.sewaseven.sewaseven;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sewaseven.sewaseven.R;

public class Home extends AppCompatActivity {
    private Button goToMenue;
    private TabLayout homeTabs;
    private TabItem announcements;
    private TabItem services;
    private ViewPager homeViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        homeTabs= findViewById(R.id.homeTabLayout);
//        announcements = findViewById(R.id.home_announcements);
//        services = findViewById(R.id.home_services);
//        homeViewPager = findViewById(R.id.home_viewpager);



        goToMenue = (Button) findViewById(R.id.profile);
        goToMenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoMainMenue = new Intent(Home.this, Mainmenue.class);
                startActivity(gotoMainMenue);

            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}