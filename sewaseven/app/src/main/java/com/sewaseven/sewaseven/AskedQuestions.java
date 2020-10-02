package com.sewaseven.sewaseven;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.database.FAQModel;
import com.sewaseven.database.FAQ_adapter;

import java.util.ArrayList;
import java.util.List;

public class AskedQuestions extends AppCompatActivity {
    Button newFAQ;
    TextView faq_history_total;
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
        adapter = new FAQ_adapter(this, FAQ_list);

        fire_store_list_faq.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("FAQ").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        FAQModel model = d.toObject(FAQModel.class);
                        model.setDocID(d.getId());
                        FAQ_list.add(model);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

        });


        newFAQ = findViewById(R.id.butn_add_new_Faq);
        newFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFAQIntent = new Intent(AskedQuestions.this, AddNewFaq.class);
                startActivity(newFAQIntent);
            }
        });

        getTotalFAQ();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent gotoList = new Intent(AskedQuestions.this, ServiceList.class);
        startActivity(gotoList);


    }

    private void getTotalFAQ() {

        faq_history_total = findViewById(R.id.faq_history_total);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("FAQ")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int countTotal = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                countTotal++;
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                        faq_history_total.setText(String.valueOf(countTotal));
                    }
                });
    }


}