package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.additional.definedFunctions;

public class ChangeYourInformation extends AppCompatActivity {
    EditText user_name_txt;
    EditText user_last_name;
    Button change_user_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_your_information);
        user_name_txt = findViewById(R.id.user_name);
        change_user_data = findViewById(R.id.change_user_data_btn);
        user_last_name = findViewById(R.id.user_last_name);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final String[] documentID = new String[1];
        ///////////////////////////
        //getting user document id
        db.collection("User").whereEqualTo("user_id", definedFunctions.userID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                documentID[0] = document.getId();
                                user_name_txt.setText(document.getString("f_name"));
                                user_last_name.setText(document.getString("l_name"));


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });


        //////////////////////////

        change_user_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DocumentReference updateService = db.collection("User").document(documentID[0]);
                updateService
                        .update(
                                "f_name", String.valueOf(user_name_txt.getText()),
                                "l_name", String.valueOf(user_last_name.getText())


                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully updated!");

                                Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
                                Intent reviewList = new Intent(ChangeYourInformation.this, Mainmenu.class);
                                reviewList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                reviewList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(reviewList);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error updating document", e);
                            }
                        });


            }
        });

    }
}