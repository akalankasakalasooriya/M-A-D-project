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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Dashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnAskedQuestions;
    private Button btnFeedbackAndRatings;
    private ImageButton previousAnnouncements;
    private ImageButton btnAddNewAnnouncement;
    private ImageButton btnDeleteService;
    private ImageButton btnUpdateServiceDetails;
    private TextView avgServiceRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
                startActivity(deleteServiceIntent);
            }
        });


        btnUpdateServiceDetails = findViewById(R.id.updateServiceDetails);
        btnUpdateServiceDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateServiceDetailsIntent = new Intent(Dashboard.this, UpdateServiceDetails.class);
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


//

        Spinner spinner = findViewById(R.id.dashboard_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.services, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
                                average = sum / (float) count;
                            }
                            avgServiceRating.setText(String.valueOf(average));
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}