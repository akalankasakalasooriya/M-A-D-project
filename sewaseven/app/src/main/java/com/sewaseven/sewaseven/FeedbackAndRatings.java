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
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sewaseven.database.FeedbackAndRatingsModel;

public class FeedbackAndRatings extends AppCompatActivity {
    private RecyclerView recyclerViewFeedBack;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.i("info","-----------------------------oncreate called--------------------------------------------------------------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_and_ratings);
        recyclerViewFeedBack = findViewById(R.id.feedbacklist);

        firebaseFirestore = FirebaseFirestore.getInstance();

        //query
        Query query = firebaseFirestore.collection("Feedback");
        //recycle
        FirestoreRecyclerOptions<FeedbackAndRatingsModel> options = new FirestoreRecyclerOptions.Builder<FeedbackAndRatingsModel>()
                .setQuery(query, FeedbackAndRatingsModel.class)
                .build();


        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<FeedbackAndRatingsModel, FeedbackAndRating_viewHolder>(options) {

            @NonNull
            @Override
            public FeedbackAndRating_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_feedbacklist_ratings_feedback, parent, false);
                return new FeedbackAndRating_viewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FeedbackAndRating_viewHolder feedbackAndRating_viewHolder, int i, @NonNull FeedbackAndRatingsModel feedbackAndRating) {
                feedbackAndRating_viewHolder.name.setText(feedbackAndRating.getPosted_user_name());
                feedbackAndRating_viewHolder.rating.setText(feedbackAndRating.getRating());
                feedbackAndRating_viewHolder.comment.setText(feedbackAndRating.getComment());
                //Log.i("info","data bind called"+feedbackAndRating.getPosted_user_id());
            }
        };

        //view holder
        recyclerViewFeedBack.setHasFixedSize(true);
        recyclerViewFeedBack.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFeedBack.setAdapter(firestoreRecyclerAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent gotoList = new Intent(FeedbackAndRatings.this,ServiceList.class);
        startActivity(gotoList);
    }

    private class FeedbackAndRating_viewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView rating;
        private TextView comment;

        public FeedbackAndRating_viewHolder(@NonNull View itemView) {
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
}