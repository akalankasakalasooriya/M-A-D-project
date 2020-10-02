package com.sewaseven.sewaseven;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class Mainmenu extends AppCompatActivity {
    private LinearLayout changeYrInfobtn;
    private LinearLayout regNewSevicebtn;
    private LinearLayout dashBoardbtn;
    private LinearLayout logoutbtn;
    private LinearLayout aboutAppbtn;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        logoutbtn = findViewById(R.id.logoutbtn);

        builder = new AlertDialog.Builder(this);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseAuth.getInstance().signOut();
                                Intent loginintent = new Intent(getApplicationContext(), Login.class);
                                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(loginintent);
                                finish();
                                Toast.makeText(getApplicationContext(),"done",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Confirm");
                alert.show();

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


        dashBoardbtn = findViewById(R.id.btnMenueDashboard);
        dashBoardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dashboardIntent = new Intent(Mainmenu.this, ServiceList.class);
                startActivity(dashboardIntent);

            }
        });


        aboutAppbtn = findViewById(R.id.aboutApp);
        aboutAppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aboutIntent = new Intent(Mainmenu.this, About.class);
                startActivity(aboutIntent);

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent gotoList = new Intent(Mainmenu.this,Home.class);
        startActivity(gotoList);
    }
}