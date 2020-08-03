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
    private Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);
//                finish();
//            }
//        };
//        new Home().getOnBackPressedDispatcher().addCallback(this, callback);


        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent loginintent = new Intent(Home.this, MainActivity.class);
                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginintent);

            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}