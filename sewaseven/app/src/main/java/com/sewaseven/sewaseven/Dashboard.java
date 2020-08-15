package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Dashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnAskedQuestions;
    private Button btnFeedbackAndRatings;
    private ImageButton previousAnnouncements;
    private ImageButton btnAddNewAnnouncement;
    private ImageButton btnDeleteService;
    private ImageButton btnUpdateServiceDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAskedQuestions = findViewById(R.id.dashboardQuestions);
        btnAskedQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashboardQuestionsIntent = new Intent(Dashboard.this,AskedQuestions.class);
                startActivity(dashboardQuestionsIntent);
            }
        });


        previousAnnouncements = findViewById(R.id.btnPreviousAnnouncements);
        previousAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PreviousAnnouncementsIntent = new Intent(Dashboard.this,PreviousAnnouncement.class);
                startActivity(PreviousAnnouncementsIntent);
            }
        });


        btnAddNewAnnouncement = findViewById(R.id.btnAddNewAnnouncement);
        btnAddNewAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewAnnouncementIntent = new Intent(Dashboard.this,NewAnnouncement.class);
                startActivity(addNewAnnouncementIntent);
            }
        });


        btnDeleteService = findViewById(R.id.deleteService);
        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteServiceIntent = new Intent(Dashboard.this,DeleteService.class);
                startActivity(deleteServiceIntent);
            }
        });


        btnUpdateServiceDetails = findViewById(R.id.updateServiceDetails);
        btnUpdateServiceDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateServiceDetailsIntent = new Intent(Dashboard.this,UpdateServiceDetails.class);
                startActivity(updateServiceDetailsIntent);
            }
        });



        btnFeedbackAndRatings = findViewById(R.id.feedbackAndRatings);
        btnFeedbackAndRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateServiceDetailsIntent = new Intent(Dashboard.this,FeedbackAndRatings.class);
                startActivity(updateServiceDetailsIntent);
            }
        });


//

        Spinner spinner = findViewById(R.id.dashboard_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.services, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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