package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewFaq extends AppCompatActivity {
    private EditText question;
    private  EditText answer;
    private Button btn_add;
    private  FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_faq);

        btn_add= findViewById(R.id.newFAQ_save_btn);
        question = findViewById(R.id.newFAQ_answer);
        answer = findViewById(R.id.newFAQ_answer);
         db = FirebaseFirestore.getInstance();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new user with a first and last name
                String txtanswer = String.valueOf(answer.getText());
                String txtquestion = String.valueOf(question.getText());

                Map<String, Object> FAQ = new HashMap<>();
                FAQ.put("answer", txtanswer);
                FAQ.put("question",txtquestion);

// Add a new document with a generated ID
                db.collection("FAQ")
                        .add(FAQ)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                                Intent reviewList = new Intent(AddNewFaq.this, AskedQuestions.class);
                                startActivity(reviewList);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });

            }
        });

    }
}