package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.sewaseven.additional.definedFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.grpc.internal.LogExceptionRunnable;

public class UpdateServiceDetails extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service_details);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Update data

        Button button_Update = findViewById(R.id.button_update);
        final EditText name_update = findViewById(R.id.update_name);
        final EditText description_update = findViewById(R.id.update_description);
        final EditText phone_update = findViewById(R.id.update_number);
        final EditText location_update = findViewById(R.id.location_update_txt);

        Intent intent=getIntent();
        final String documentID= intent.getStringExtra("docID_service");
//////////////////////////////
        DocumentReference docRef = db.collection("Service").document(documentID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                        String description = document.getString("description");
                        String phone = document.getString("tp_number");
                        String location = document.getString("location");
                        String name = document.getString("name");



                        /////////////////////////
                        description_update.setText(description);
                        phone_update.setText(phone);
                        location_update.setText(location);
                        name_update.setText(name);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });



        button_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(name_update.getText()).trim().equals("") || String.valueOf(description_update.getText()).trim().equals("") || !definedFunctions.isValidPhone(String.valueOf(phone_update.getText()))){
                    Toast.makeText(getApplicationContext(), "please enter data correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                DocumentReference updateService = db.collection("Service").document(documentID);
                updateService
                        .update(
                                "description", String.valueOf(description_update.getText()) ,
                                "tp_number", String.valueOf(phone_update.getText()) ,
                                "location", String.valueOf(location_update.getText()) ,
                                "name",String.valueOf(name_update.getText()) ,
                                "ownerID", definedFunctions.userID()

                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully updated!");

                                Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
                                Intent reviewList = new Intent(UpdateServiceDetails.this, ServiceList.class);
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

    @Override
    public void onBackPressed() {
        Intent gotoList = new Intent(UpdateServiceDetails.this,ServiceList.class);
        startActivity(gotoList);
    }
    
}