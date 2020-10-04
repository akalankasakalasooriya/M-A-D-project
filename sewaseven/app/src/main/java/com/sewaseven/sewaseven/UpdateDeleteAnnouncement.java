package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class UpdateDeleteAnnouncement extends AppCompatActivity {
    TextView info;
    ImageView img;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_announcement);
        img = findViewById(R.id.announcements_history_img);
        info = findViewById(R.id.del_ansment_data);
        delete = findViewById(R.id.announcements_history_delete);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        //getting id
        String documentID = "";
        documentID = (String) getIntent().getSerializableExtra("docID");

        //setting data
        DocumentReference docRef = db.collection("Announcement").document(documentID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        //Log.e("TAG", "DocumentSnapshot data: " + document.get("question")+ document.get("answer"));
                        info.setText(String.valueOf(document.get("description")));
                        Picasso.get().load(String.valueOf(document.get("imagePath"))).into(img);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });


        // //cancel-> datete data
        final String finalDocumentID = documentID;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Announcement").document(finalDocumentID)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!");
                                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                                Intent ansment_list = new Intent(UpdateDeleteAnnouncement.this, PreviousAnnouncement.class);
                                startActivity(ansment_list);
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
}