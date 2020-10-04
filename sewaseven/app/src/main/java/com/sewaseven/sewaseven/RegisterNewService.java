package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sewaseven.additional.definedFunctions;

import java.util.HashMap;
import java.util.Map;

public class RegisterNewService extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_service);




        //Data enter to the database

        Button button = findViewById(R.id.button2);
        final EditText name = findViewById(R.id.servicename);
        final EditText description = findViewById(R.id.description);
        final EditText phone = findViewById(R.id.phonenumber);
        final EditText spinner = findViewById(R.id.spinnernew);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validation
                if (String.valueOf(name.getText()).trim().equals("") || String.valueOf(description.getText()).trim().equals("") || !definedFunctions.isValidPhone(String.valueOf(phone.getText()))){
                    Toast.makeText(getApplicationContext(), "please enter data correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Create a new user with a first and last name
                Map<String, Object> service = new HashMap<>();
                service.put("name",name.getText().toString());
                service.put("description",description.getText().toString());
                service.put("tp_number",phone.getText().toString());
                service.put("location",spinner.getText().toString());
                service.put("ownerID", definedFunctions.userID());

// Add a new document with a generated ID
                db.collection("Service")
                        .add(service)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                                    Intent gotoList = new Intent(RegisterNewService.this,ServiceList.class);
                                    startActivity(gotoList);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("", "Error adding document", e);
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}