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
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        //Delete Service
        final String documentID = (String) getIntent().getSerializableExtra("docID_service_del");

        Button button_Delete = findViewById(R.id.Delete_Service_Button);
        final EditText name_delete = findViewById(R.id.delete_Company_Name);

        button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String checkertxt = String.valueOf(name_delete.getText());
                checkertxt = checkertxt.trim();
                //validation
                if ((checkertxt.equals("delete") || checkertxt.equals("Delete") || checkertxt.equals("DELETE")))
                {

                }
                else {
                    Toast.makeText(getApplicationContext(), "please fill the text box correctly", Toast.LENGTH_SHORT).show();
                    return;

                }
                db.collection("Service").document(documentID)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!");
                                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                                Intent deletedone = new Intent(DeleteService.this, ServiceList.class);
                                deletedone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                deletedone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(deletedone);
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
       // super.onBackPressed();
        Intent gotoList = new Intent(DeleteService.this,ServiceList.class);
        startActivity(gotoList);
    }
}