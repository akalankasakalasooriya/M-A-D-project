package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.additional.definedFunctions;

public class Dashboard extends AppCompatActivity {
    private Button btnAskedQuestions;
    private Button btnFeedbackAndRatings;
    private ImageButton previousAnnouncements;
    private ImageButton btnAddNewAnnouncement;
    private ImageButton btnDeleteService;
    private ImageButton btnUpdateServiceDetails;
    private TextView avgServiceRating;
    private TextView servicename; //kanchila
    private TextView locationobj; //Kanchila
    private TextView phoneobj; //Kanchila
    private TextView descriptionobj; //Kanchila
    String documentID = null;


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        avgServiceRating = findViewById(R.id.dashboard_average_rating);

        btnAskedQuestions = findViewById(R.id.dashboardQuestions);
        btnAskedQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashboardQuestionsIntent = new Intent(Dashboard.this, AskedQuestions.class);
                startActivity(dashboardQuestionsIntent);
            }
        });


        previousAnnouncements = findViewById(R.id.btnPreviousAnnouncements);
        previousAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PreviousAnnouncementsIntent = new Intent(Dashboard.this, PreviousAnnouncement.class);
                startActivity(PreviousAnnouncementsIntent);
            }
        });


        btnAddNewAnnouncement = findViewById(R.id.btnAddNewAnnouncement);
        btnAddNewAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewAnnouncementIntent = new Intent(Dashboard.this, NewAnnouncement.class);
                startActivity(addNewAnnouncementIntent);
            }
        });


        btnDeleteService = findViewById(R.id.deleteService);
        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteServiceIntent = new Intent(Dashboard.this, DeleteService.class);
                deleteServiceIntent.putExtra("docID_service_del", documentID);
                startActivity(deleteServiceIntent);
            }
        });


        btnUpdateServiceDetails = findViewById(R.id.updateServiceDetails);
        btnUpdateServiceDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateServiceDetailsIntent = new Intent(Dashboard.this, UpdateServiceDetails.class);
                updateServiceDetailsIntent.putExtra("docID_service", documentID);
                startActivity(updateServiceDetailsIntent);
            }
        });


        btnFeedbackAndRatings = findViewById(R.id.feedbackAndRatings);
        btnFeedbackAndRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateServiceDetailsIntent = new Intent(Dashboard.this, FeedbackAndRatings.class);
                startActivity(updateServiceDetailsIntent);
            }
        });


        //get service avg
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
                                //avgServiceRating
                                count++;
                                sum += Float.parseFloat(document.getString("rating"));
                                average = definedFunctions.calAVG(sum, count);
                            }
                            avgServiceRating.setText(String.valueOf(average));
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });


        servicename = findViewById(R.id.dashboard_service_name);

        locationobj = findViewById(R.id.dashboard_location);

        phoneobj = findViewById(R.id.dashboard_phone_number);

        descriptionobj = findViewById(R.id.dashboard_description);


        if (documentID == null || documentID == "") {
            Intent gotoList = new Intent(Dashboard.this, ServiceList.class);
            startActivity(gotoList);

        }
        DocumentReference docRef = db.collection("Service").document(documentID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                        String description = document.getString("description");
                        String phone = document.getString("tp_number");
                        String location = document.getString("location");
                        String name = document.getString("name");


                        /////////////////////////
                        descriptionobj.setText(description);
                        phoneobj.setText(phone);
                        locationobj.setText(location);
                        servicename.setText(name);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        /**
         * getting doc id
         * **/
        Intent intent = getIntent();
        documentID = intent.getStringExtra("docID");


    }


    @Override
    protected void onResume() {
        super.onResume();

    }


}