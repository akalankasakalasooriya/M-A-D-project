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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.additional.definedFunctions;
import com.sewaseven.database.FeedbackAndRatingsModel;
import com.sewaseven.database.User;

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
        final DocumentReference newFeedbackRef = firebaseFirestore.collection("Feedback").document();
        btnDone = findViewById(R.id.my_feedback_done);
        btnCancel = findViewById(R.id.my_feedback_cancel);
        ratingBar = findViewById(R.id.my_feedback_ratingBar);
        myComment = findViewById(R.id.my_feedback_comment);


        //update
        final FeedbackAndRatingsModel temp_feedbackAndRatings = new FeedbackAndRatingsModel();
        final String[] docID = new String[1];
        docID[0] = "";
        firebaseFirestore.collection("Feedback").whereEqualTo("posted_user_id", definedFunctions.userID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.e("data", document.getId() + " => " + document.getData());
                                temp_feedbackAndRatings.setPosted_user_id(document.getString("posted_user_id"));
                                temp_feedbackAndRatings.setComment(document.getString("comment"));
                                temp_feedbackAndRatings.setRating(document.getString("rating"));
                                docID[0] = document.getId();
                                //setting data if already submitted
                                String currentUserID = definedFunctions.userID();
                                if (currentUserID.equals(temp_feedbackAndRatings.getPosted_user_id())) {
                                    ratingBar.setRating(Float.parseFloat(temp_feedbackAndRatings.getRating()));
                                    myComment.setText(temp_feedbackAndRatings.getComment());

                                }


                            }
                        } else {
                            Log.e("data", "Error getting documents.", task.getException());
                        }
                    }
                });


        //getting current user details
        final User tempUser = new User();


        firebaseFirestore.collection("User").whereEqualTo("user_id", definedFunctions.userID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.e("data", document.getId() + " => " + document.getData());
                                tempUser.setFirstName(document.getString("f_name"));
                                tempUser.setLastName(document.getString("l_name"));

                            }
                        } else {
                            Log.e("data", "Error getting documents.", task.getException());
                        }
                    }
                });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = Float.toString(ratingBar.getRating());
                String comment = String.valueOf(myComment.getText());
                //validation
                if (comment.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "please enter data correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                String posted_user_id = definedFunctions.userID();
                final FeedbackAndRatingsModel feedbackAndRatingsModel = new FeedbackAndRatingsModel();
                feedbackAndRatingsModel.setRating(rating);
                feedbackAndRatingsModel.setComment(comment);
                feedbackAndRatingsModel.setPosted_user_id(posted_user_id);
                feedbackAndRatingsModel.setPosted_user_name(tempUser.getFirstName() + " " + tempUser.getLastName());


                if (docID[0] != "") {
                    //update
                    Log.e("----TAG-----", docID[0]);
                    DocumentReference updateFeedback = firebaseFirestore.collection("Feedback").document(docID[0]);

                    updateFeedback
                            .update(
                                    "rating", feedbackAndRatingsModel.getRating(),
                                    "comment", feedbackAndRatingsModel.getComment(),
                                    "serverTimeStamp", FieldValue.serverTimestamp()

                            )
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "DocumentSnapshot successfully updated!");

                                    Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
                                    Intent reviewList = new Intent(CreateUpdateMyFeedback.this, RatingsAndReviews.class);
                                    startActivity(reviewList);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error updating document", e);
                                }
                            });
                } else {
                    //new
                    newFeedbackRef.set(feedbackAndRatingsModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                                Intent reviewList = new Intent(CreateUpdateMyFeedback.this, RatingsAndReviews.class);
                                startActivity(reviewList);
                            } else {
                            }
                        }
                    });
                }

            }
        });


        //delete
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("Feedback").document(docID[0])
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!");
                                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                                Intent reviewList = new Intent(CreateUpdateMyFeedback.this, RatingsAndReviews.class);
                                startActivity(reviewList);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error deleting document", e);
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent servicePage = new Intent(CreateUpdateMyFeedback.this, RatingsAndReviews.class);
        servicePage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(servicePage);
    }
}

