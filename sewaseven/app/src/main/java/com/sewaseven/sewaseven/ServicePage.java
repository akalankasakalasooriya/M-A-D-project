package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ServicePage extends AppCompatActivity {


    private ImageButton makeCallToService;
    private ImageButton ratingsAndReviews;
    private ImageButton btnSeeFAQ;
    private TextView avgRating;

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
                Intent ratingsAndReviewsIntent = new Intent(ServicePage.this, RatingsAndReviews.class);
                startActivity(ratingsAndReviewsIntent);

            }
        });


        btnSeeFAQ = findViewById(R.id.btnSeeFAQ);
        btnSeeFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeFAQIntent = new Intent(ServicePage.this, SeeFAQ.class);
                startActivity(seeFAQIntent);

            }
        });

        //get service avg
        avgRating = findViewById(R.id.service_page_avg_rating);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Feedback").limit(20).orderBy("serverTimeStamp")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            float sum = (float) 0.0, average = (float) 0.0;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("TAG", document.getId() + " => " + document.getData());
                                //
                                count++;
                                sum += Float.parseFloat(document.getString("rating"));
                                average = sum / (float) count;
                            }
                            avgRating.setText("Ratings " + String.valueOf(average));
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

    }


    protected void makeCall() {


        String d = "tel:" + "0772637357";

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