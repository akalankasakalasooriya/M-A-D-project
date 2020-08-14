package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ServicePage extends AppCompatActivity {


    private ImageButton makeCallToService;
    private ImageButton ratingsAndReviews;
    private ImageButton btnSeeFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);

        makeCallToService = findViewById(R.id.btnMakeCall);
        makeCallToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ActivityCompat.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
//                   { makeCall();}

            }
        });

        ratingsAndReviews = findViewById(R.id.btnAddRatings);
        ratingsAndReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ratingsAndReviewsIntent = new Intent(ServicePage.this,RatingsAndReviews.class);
                startActivity(ratingsAndReviewsIntent);

            }
        });


        btnSeeFAQ = findViewById(R.id.btnSeeFAQ);
        btnSeeFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeFAQIntent = new Intent(ServicePage.this,SeeFAQ.class);
                startActivity(seeFAQIntent);

            }
        });

    }




    protected void makeCall() {


        String d = "tel:" + "0772637357" ;

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(d));
        try {
            startActivity(phoneIntent);
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}