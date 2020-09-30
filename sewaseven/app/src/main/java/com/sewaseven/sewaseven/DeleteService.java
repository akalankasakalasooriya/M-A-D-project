package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class DeleteService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        //Delete Service

        Button button_Delete = findViewById(R.id.Delete_Service_Button);
        final EditText name_delete = findViewById(R.id.delete_Company_Name);

        button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore.getInstance().collection("Service")
                        .whereEqualTo("name", name_delete.getText().toString())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                WriteBatch batch = FirebaseFirestore.getInstance().batch();

                                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot snapshot: snapshotList){
                                    batch.delete(snapshot.getReference());
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
}