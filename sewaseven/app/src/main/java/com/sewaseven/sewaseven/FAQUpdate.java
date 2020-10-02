package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FAQUpdate extends AppCompatActivity {
    private TextView update_q, update_a;
    private ImageButton done, delete;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q_update);
        update_a = findViewById(R.id.txt_update_faq_a);
        update_q = findViewById(R.id.txt_update_faq_q);
        done = findViewById(R.id.FAQ_done);
        delete = findViewById(R.id.FAQ_cancel);

//getting  and setting data
        String documentID = "";
        documentID = (String) getIntent().getSerializableExtra("docID");
        //Log.e("xxxxxxxxx","xx   -- "+documentID);

        DocumentReference docRef = db.collection("FAQ").document(documentID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        //Log.e("TAG", "DocumentSnapshot data: " + document.get("question")+ document.get("answer"));
                        update_a.setText(String.valueOf(document.get("answer")));
                        update_q.setText(String.valueOf(document.get("question")));
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });


        //done-> save data
        final String finalDocumentID = documentID;
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(update_q.getText()).trim().equals("") || String.valueOf(update_a.getText()).trim().equals("")){
                    Toast.makeText(getApplicationContext(), "please enter data correctly", Toast.LENGTH_SHORT).show();
                    finish();
                }


                DocumentReference updateFAQ = db.collection("FAQ").document(finalDocumentID);

                updateFAQ
                        .update(
                                "question", String.valueOf(update_q.getText()),
                                "answer", String.valueOf(update_a.getText())

                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.e("xxxxxxxxxxxxxxxxxxxxx", String.valueOf(update_q.getText()));
                                Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
                                Intent faqlist = new Intent(FAQUpdate.this, AskedQuestions.class);
                                startActivity(faqlist);
                            }
                        });


            }
        });


        // //cancel-> datete data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("FAQ").document(finalDocumentID)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!");
                                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                                Intent faqlist = new Intent(FAQUpdate.this, AskedQuestions.class);
                                startActivity(faqlist);
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
        Intent faqlist = new Intent(FAQUpdate.this, AskedQuestions.class);
        faqlist.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(faqlist);

    }
}