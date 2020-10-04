package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sewaseven.additional.definedFunctions;
import com.sewaseven.database.Announsement;
import com.sewaseven.database.User;

import java.util.HashMap;
import java.util.Map;

public class NewAnnouncement extends AppCompatActivity {

    private EditText description;
    private Button select_pic, publish;
    private ImageView selectedImg;
    public Uri imguri;
    private StorageReference mStorageRef;
    protected Announsement announsement = new Announsement();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);
        select_pic = findViewById(R.id.btn_select_ans_img);
        publish = findViewById(R.id.btn_publish_ans);
        selectedImg = findViewById(R.id.img_show_selected_btn);
        description = findViewById(R.id.descriptionTxt);
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");


        //
        //select img from files
        select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });


        //upload
        //current user details

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //publish description and image as link

                String txtdescription = String.valueOf(description.getText());
                //validation

                if (txtdescription.trim().equals("") || imguri == null) {
                    Toast.makeText(getApplicationContext(), "please fill data", Toast.LENGTH_SHORT).show();
                } else {

                    publishNewAnnouncement();

                }


            }
        });
    }

    private void publishNewAnnouncement() {
        final String[] imgURL = {""};
        imgURL[0] = "";
        final StorageReference Ref = mStorageRef.child(System.currentTimeMillis() + "." + getExtension(imguri));


        UploadTask uploadTask = Ref.putFile(imguri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return Ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    imgURL[0] = String.valueOf(downloadUri);

                    //getting current user details
                    final User tempUser = new User();

                    final FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("User").whereEqualTo("user_id", definedFunctions.userID())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Log.e("data", document.getId() + " => " + document.getData());
                                            tempUser.setFirstName(document.getString("f_name"));
                                            tempUser.setLastName(document.getString("l_name"));


                                            String txtdescription_value = String.valueOf(description.getText());
                                            Map<String, Object> announcement = new HashMap<>();
                                            announcement.put("description", txtdescription_value);
                                            announcement.put("imagePath", imgURL[0]);
                                            announcement.put("serverTimeStamp", System.currentTimeMillis());
                                            announcement.put("name", tempUser.getFirstName() + " " + tempUser.getLastName());
                                            announcement.put("proPicPath", "https://firebasestorage.googleapis.com/v0/b/sewa-seven.appspot.com/o/propic.jpg?alt=media&token=fde461a5-f26c-40bf-b318-b9a8e4cedb19");

// Add a new document with a generated ID
                                            db.collection("Announcement")
                                                    .add(announcement)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                            Log.e("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                            Intent dashintent = new Intent(NewAnnouncement.this, PreviousAnnouncement.class);
                                                            dashintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(dashintent);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.e("TAG", "Error adding document", e);
                                                        }
                                                    });


                                        }
                                    } else {
                                        Log.e("data", "Error getting documents.", task.getException());
                                    }
                                }
                            });


                    ///////////
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();


                } else {
                    // Handle failures
                    // ...
                }
            }
        });


    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }


    private void fileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();

            selectedImg.setImageURI(imguri);

        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent gotoList = new Intent(NewAnnouncement.this,ServiceList.class);
        startActivity(gotoList);

    }
}