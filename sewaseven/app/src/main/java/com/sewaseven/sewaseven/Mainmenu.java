package com.sewaseven.sewaseven;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Mainmenu extends AppCompatActivity {
    private LinearLayout logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        logoutbtn = findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent loginintent = new Intent(Mainmenu.this, MainActivity.class);
                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginintent);

            }
        });
    }
}