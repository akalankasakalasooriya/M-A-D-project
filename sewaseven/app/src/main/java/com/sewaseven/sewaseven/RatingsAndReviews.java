package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RatingsAndReviews extends AppCompatActivity {
    private Button btnCreateUpdateFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_and_reviews);


        btnCreateUpdateFeedback = findViewById(R.id.btnCreateUpdateFeedback);
        btnCreateUpdateFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUpdateFeedbackIntent = new Intent(RatingsAndReviews.this,CreateUpdateMyFeedback.class);
                startActivity(createUpdateFeedbackIntent);
            }
        });

    }
}