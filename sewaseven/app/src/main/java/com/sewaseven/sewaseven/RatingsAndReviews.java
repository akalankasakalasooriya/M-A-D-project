package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sewaseven.database.FeedbackAndRatingsModel;

public class RatingsAndReviews extends AppCompatActivity {
    private Button btnCreateUpdateFeedback;
    private RecyclerView recyclerViewFeedBack;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_and_reviews);

        recyclerViewFeedBack = findViewById(R.id.service_feedback_list);

        firebaseFirestore = FirebaseFirestore.getInstance();


        //btn
        btnCreateUpdateFeedback = findViewById(R.id.btnCreateUpdateFeedback);
        btnCreateUpdateFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUpdateFeedbackIntent = new Intent(RatingsAndReviews.this, CreateUpdateMyFeedback.class);
                startActivity(createUpdateFeedbackIntent);
            }
        });
        //end of btn


        //query
        Query query = firebaseFirestore.collection("Feedback");
        //recycle
        FirestoreRecyclerOptions<FeedbackAndRatingsModel> options = new FirestoreRecyclerOptions.Builder<FeedbackAndRatingsModel>()
                .setQuery(query, FeedbackAndRatingsModel.class)
                .build();


        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<FeedbackAndRatingsModel, RatingsAndReviews.service_feedbackAndRating_viewHolder>(options) {

            @NonNull
            @Override
            public RatingsAndReviews.service_feedbackAndRating_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_feedbacklist_ratings_feedback, parent, false);
                Log.e("info", "------------------onCreateViewHolder------------------------------------");
                return new RatingsAndReviews.service_feedbackAndRating_viewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull RatingsAndReviews.service_feedbackAndRating_viewHolder feedbackAndRating_viewHolder, int i, @NonNull FeedbackAndRatingsModel feedbackAndRating) {
                feedbackAndRating_viewHolder.name.setText(feedbackAndRating.getPosted_user_name());
                feedbackAndRating_viewHolder.rating.setText(feedbackAndRating.getRating());
                feedbackAndRating_viewHolder.comment.setText(feedbackAndRating.getComment());
                Log.e("info", "------------------onBindViewHolder------------------------------------");
            }
        };

        //view holder
        recyclerViewFeedBack.setHasFixedSize(true);
        recyclerViewFeedBack.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFeedBack.setAdapter(firestoreRecyclerAdapter);

    }


    private class service_feedbackAndRating_viewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView rating;
        private TextView comment;

        public service_feedbackAndRating_viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recycle_feedback_and_ratings_name);
            rating = itemView.findViewById(R.id.recycle_feedback_and_ratings_value);
            comment = itemView.findViewById(R.id.recycle_feedback_and_ratings_comment);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firestoreRecyclerAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent servicePage = new Intent(RatingsAndReviews.this, Home.class);
        servicePage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(servicePage);
    }
}


