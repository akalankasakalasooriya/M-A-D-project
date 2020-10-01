package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.database.Announcement_adapter;
import com.sewaseven.database.Announsement;

import java.util.ArrayList;
import java.util.List;

public class ServicePage extends AppCompatActivity {


    private ImageButton makeCallToService;
    private ImageButton ratingsAndReviews;
    private ImageButton btnSeeFAQ;
    private TextView avgRating;
    private TextView servicename; //kanchila
    private TextView locationobj; //Kanchila
    private TextView phoneobj; //Kanchila
    ///////////////
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_announsement;
    private List<Announsement> announsement_list;
    private Announcement_adapter adapter;

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
                            float sum =(float) 0.0, average = (float) 0.0;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("TAG", document.getId() + " => " + document.getData());
                                //
                                count++;
                                sum += Float.parseFloat(document.getString("rating"));
                                average = sum/(float)count;
                            }
                            avgRating.setText("Ratings "+String.valueOf(average));
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        //get service name

        servicename = findViewById(R.id.service_page_service_name);

        db.collection("Service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String name = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d("", document.getId() + " => " + document.getData());
                                name = document.getString("name");
                            }
                            servicename.setText(name);
                        } else {
                            Log.w("", "Error getting documents.", task.getException());
                        }
                    }
                });

        //get location

        locationobj = findViewById(R.id.service_page_location);

        db.collection("Service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String location = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d("", document.getId() + " => " + document.getData());
                                location = document.getString("location");
                            }
                            locationobj.setText(location);
                        } else {
                            Log.w("", "Error getting documents.", task.getException());
                        }
                    }
                });

        //get phone number

        phoneobj = findViewById(R.id.service_page_phone_number);

        db.collection("Service")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String phone = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d("", document.getId() + " => " + document.getData());
                                phone = document.getString("tp_number");
                            }
                            phoneobj.setText(phone);
                        } else {
                            Log.w("", "Error getting documents.", task.getException());
                        }
                    }
                });


        ///////////////
        fire_store_list_announsement = (RecyclerView) findViewById(R.id.service_page_announcements);
        fire_store_list_announsement.setHasFixedSize(true);
        fire_store_list_announsement.setLayoutManager(new LinearLayoutManager(this));

        announsement_list = new ArrayList<>();
        adapter = new Announcement_adapter(this, announsement_list);

        fire_store_list_announsement.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Announcement").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Announsement model = d.toObject(Announsement.class);
                        model.setDocID(d.getId());
                        announsement_list.add(model);
                    }
                    adapter.notifyDataSetChanged();

                }
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