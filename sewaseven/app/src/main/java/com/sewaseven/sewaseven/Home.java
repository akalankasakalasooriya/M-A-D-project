package com.sewaseven.sewaseven;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sewaseven.sewaseven.R;

public class Home extends AppCompatActivity {
    private Button goToMenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



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