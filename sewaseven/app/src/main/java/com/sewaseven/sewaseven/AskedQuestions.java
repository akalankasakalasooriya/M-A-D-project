package com.sewaseven.sewaseven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.database.FAQModel;
import com.sewaseven.database.FAQ_adapter;

import java.util.ArrayList;
import java.util.List;

public class AskedQuestions extends AppCompatActivity {
    TextView txtFAQUpdate;
    Button newFAQ;
    ///////
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_faq;
    private List<FAQModel> FAQ_list;
    private FAQ_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_questions);
        /////////

        fire_store_list_faq = (RecyclerView) findViewById(R.id.asked_questions_list);
        fire_store_list_faq.setHasFixedSize(true);
        fire_store_list_faq.setLayoutManager(new LinearLayoutManager(this));

        FAQ_list = new ArrayList<>();
        adapter = new FAQ_adapter(this,FAQ_list);

        fire_store_list_faq.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("FAQ").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(!queryDocumentSnapshots.isEmpty()){
                                List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot d : list){
                                    FAQModel model =  d.toObject(FAQModel.class);
                                    model.setDocID(d.getId());
                                    FAQ_list.add(model);
                                }
                                adapter.notifyDataSetChanged();

                            }
                    }

                });




        newFAQ =findViewById(R.id.butn_add_new_Faq);
        newFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFAQIntent = new Intent(AskedQuestions.this,AddNewFaq.class);
                startActivity(newFAQIntent);
            }
        });

        /////////////


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent homeintent = new Intent(AskedQuestions.this, Home.class);
        homeintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeintent);

    }
}