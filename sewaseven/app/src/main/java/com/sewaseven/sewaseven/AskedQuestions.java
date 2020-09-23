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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sewaseven.database.FAQModel;

public class AskedQuestions extends AppCompatActivity {
    TextView txtFAQUpdate;
    Button newFAQ;
    ///////
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_faq;
    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_questions);
        /////////
        firebaseFirestore = FirebaseFirestore.getInstance();
        fire_store_list_faq = (RecyclerView) findViewById(R.id.asked_questions_list);

//////////////
//        txtFAQUpdate =findViewById(R.id.FAQUpdate);
//        txtFAQUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent FAQUpdateIntent = new Intent(AskedQuestions.this,FAQUpdate.class);
//                startActivity(FAQUpdateIntent);
//            }
//        });


        newFAQ =findViewById(R.id.butn_add_new_Faq);
        newFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFAQIntent = new Intent(AskedQuestions.this,AddNewFaq.class);
                startActivity(newFAQIntent);
            }
        });

        /////////////
        //getting data from data base
        Query query = firebaseFirestore.collection("FAQ");

        //options
        FirestoreRecyclerOptions<FAQModel> options = new FirestoreRecyclerOptions.Builder<FAQModel>()
                .setQuery(query,FAQModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<FAQModel, AskedQuestions.FAQ_view_holder>(options) {
            @NonNull
            @Override
            public AskedQuestions.FAQ_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_asked_questions_single,parent,false);

                return new AskedQuestions.FAQ_view_holder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull AskedQuestions.FAQ_view_holder holder, int position, @NonNull FAQModel model) {
                holder.list_question.setText(model.getQuestion());
                holder.list_answer.setText(model.getAnswer());
                String id = getSnapshots().getSnapshot(position).getId();
                holder.list_document_id.setText(id);


            }
        };

        fire_store_list_faq.setHasFixedSize(true);
        fire_store_list_faq.setLayoutManager(new LinearLayoutManager(this));
        fire_store_list_faq.setAdapter(adapter);

        //holder



        //////////////////

    }


    private class FAQ_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView list_question;
        private TextView list_answer;
        private  TextView list_document_id;

        public FAQ_view_holder(@NonNull View itemView) {
            super(itemView);
            list_question = itemView.findViewById(R.id.asked_questions_single_question);
            list_answer = itemView.findViewById(R.id.asked_questions_single_answer);
            list_document_id = itemView.findViewById(R.id.FAQdocumentID);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext() ,FAQUpdate.class);
            TextView clickdocument_id = v.findViewById(R.id.FAQdocumentID);
            String docid = (String) clickdocument_id.getText();
            Log.e("------","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+docid);
            //intent.putExtra("FAQ_data_set",)

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