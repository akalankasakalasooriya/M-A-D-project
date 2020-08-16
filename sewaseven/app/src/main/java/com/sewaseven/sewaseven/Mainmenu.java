package com.sewaseven.sewaseven;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Mainmenu extends AppCompatActivity {
    private LinearLayout changeYrInfobtn;
    private LinearLayout regNewSevicebtn;
    private LinearLayout dashBoardbtn;
    private LinearLayout logoutbtn;
    private LinearLayout aboutAppbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        logoutbtn = findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent loginintent = new Intent(Mainmenu.this, Login.class);
                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginintent);

            }
        });

        changeYrInfobtn = findViewById(R.id.btnMenueChangeInfo);
        changeYrInfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent changeInfoIntent = new Intent(Mainmenu.this, ChangeYourInformation.class);
                startActivity(changeInfoIntent);

            }
        });


        regNewSevicebtn = findViewById(R.id.btnMenueRegNewService);
        regNewSevicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regNewServiceIntent = new Intent(Mainmenu.this, RegisterNewService.class);
                startActivity(regNewServiceIntent);

            }
        });


        dashBoardbtn= findViewById(R.id.btnMenueDashboard);
        dashBoardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dashboardIntent = new Intent(Mainmenu.this, Dashboard.class);
                startActivity(dashboardIntent);

            }
        });


        aboutAppbtn= findViewById(R.id.aboutApp);
        aboutAppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aboutIntent = new Intent(Mainmenu.this, About.class);
                startActivity(aboutIntent);

            }
        });



    }
}