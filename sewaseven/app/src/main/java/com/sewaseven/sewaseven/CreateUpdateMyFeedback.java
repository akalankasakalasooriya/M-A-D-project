package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateUpdateMyFeedback extends AppCompatActivity {
    ImageButton btnCancel;
    ImageButton btnDone;
    RatingBar ratingBar;
    TextView myComment;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_update_my_feedback);
        firebaseFirestore = FirebaseFirestore.getInstance();
        btnDone = findViewById(R.id.my_feedback_done);
        btnCancel = findViewById(R.id.my_feedback_cancel);
        ratingBar = findViewById(R.id.my_feedback_ratingBar);
        myComment = findViewById(R.id.my_feedback_comment);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = Float.toString(ratingBar.getRating());
                String comment = String.valueOf(myComment.getText());
                String myName = "akalanka sakalasooriya";
                Map<String,Object> feedback = new HashMap<>();
                feedback.put("rating",rating);
                feedback.put("comment",comment);
                feedback.put("posted_user_id",myName);

                firebaseFirestore.collection("Feedback")
                        .add(feedback)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("MSG---", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Intent reviewList = new Intent(CreateUpdateMyFeedback.this,RatingsAndReviews.class);
                                startActivity(reviewList);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("MSG---", "Error adding document", e);
                            }
                        });

            }
        });


    }


}