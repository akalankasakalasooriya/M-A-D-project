package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.grpc.internal.LogExceptionRunnable;

public class UpdateServiceDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service_details);

        Spinner spinner = findViewById(R.id.spinner_update);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Update data

        Button button_Update = findViewById(R.id.button_update);
        final EditText name_update = findViewById(R.id.update_name);
        final EditText description_update = findViewById(R.id.update_description);
        final EditText phone_update = findViewById(R.id.update_number);
        final Spinner location_update = findViewById(R.id.spinner_update);

//        button_Update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DocumentReference docRef = FirebaseFirestore.getInstance()
//                        .collection("Service")
//                        .document("UwyZETQ0nuAI5DQ0TfqV");
//
//                Map<String, Object> update = new HashMap<>();
//
//                update.put("name",name_update.getText().toString());
//                update.put("description",description_update.getText().toString());
//                update.put("tp_number",phone_update.getText().toString());
//                update.put("location",location_update.getSelectedItem().toString());
//
//                docRef.update(update)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Log.d("", "onSuccess: ");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.e("", "onFailure: ", e);
//                            }
//                        });
//            }
//        });

        button_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore.getInstance().collection("Service")
                        .whereEqualTo("name", name_update.getText().toString())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                WriteBatch batch = FirebaseFirestore.getInstance().batch();

                                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot snapshot: snapshotList){
                                    //batch.delete(snapshot.getReference());
                                    //batch.update(snapshot.get("name"),name_update.getText().toString()); //to be edit............................
                                    //batch.update(snapshot.get("name"),name_update.getText().toString()); //to be edit............................
                                }
                                batch.commit()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("", "onSuccess: ");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e("", "onFailure: ", e);
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}