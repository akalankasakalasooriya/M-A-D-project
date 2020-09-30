package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sewaseven.database.FAQModel;

public class SeeFAQ extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_faq;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_f_a_q);

        firebaseFirestore = FirebaseFirestore.getInstance();
        fire_store_list_faq = findViewById(R.id.see_FAQ_list);

        //query
        Query query = firebaseFirestore.collection("FAQ");

        //options
        FirestoreRecyclerOptions<FAQModel> options = new FirestoreRecyclerOptions.Builder<FAQModel>()
                .setQuery(query, FAQModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<FAQModel, FAQ_view_holder>(options) {
            @NonNull
            @Override
            public FAQ_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_faq_single, parent, false);

                return new FAQ_view_holder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FAQ_view_holder holder, int position, @NonNull FAQModel model) {
                holder.list_question.setText(model.getQuestion());
                holder.list_answer.setText(model.getAnswer());

            }
        };

        fire_store_list_faq.setHasFixedSize(true);
        fire_store_list_faq.setLayoutManager(new LinearLayoutManager(this));
        fire_store_list_faq.setAdapter(adapter);

        //holder

    }

    private class FAQ_view_holder extends RecyclerView.ViewHolder {
        private TextView list_question;
        private TextView list_answer;

        public FAQ_view_holder(@NonNull View itemView) {
            super(itemView);
            list_question = itemView.findViewById(R.id.faq_single_question);
            list_answer = itemView.findViewById(R.id.faq_single_answer);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}