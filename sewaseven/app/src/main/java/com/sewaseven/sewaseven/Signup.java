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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sewaseven.additional.definedFunctions;

import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity {
    private EditText emailId, passwordId , nameTxt_f,nameTxt_l;
    private FirebaseAuth FbaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_signup);

        FbaseAuth = FirebaseAuth.getInstance();
        nameTxt_f = findViewById(R.id.signup_fname);
        nameTxt_l = findViewById(R.id.signup_lname);
        emailId = findViewById(R.id.signup_email);
        passwordId = findViewById(R.id.signup_password);
        Button signupbtn = (Button) findViewById(R.id.newUserbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String password = passwordId.getText().toString();
                if (email.isEmpty() && definedFunctions.isValidEmail(email)) {
                    emailId.setError("Enter valid Email");
                    emailId.requestFocus();
                }
                if (password.isEmpty()) {
                    passwordId.setError("Enter Password");
                    passwordId.requestFocus();
                } else if (password.length() <= 6) {
                    passwordId.setError("Weak Password");
                    Toast.makeText(Signup.this, "Weak Password. Use more than 6 characters", Toast.LENGTH_SHORT).show();
                }
                if (!email.isEmpty() && !password.isEmpty()) {
                    FbaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Signup.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                //Log.e("DEBUG", task.getException().toString());

                            } else {
                                //startActivity(Login.this,Home.class);
                                String userID="";
                                String name_f = "";String name_l = "";
                                name_f = String.valueOf(nameTxt_f.getText()); name_l = String.valueOf(nameTxt_l.getText());
                                userID = definedFunctions.userID();
                                ///////////////////////
                                // Create a new user with a first and last name
                                Map<String, Object> user = new HashMap<>();
                                user.put("f_name",name_f);
                                user.put("l_name", name_l);
                                user.put("user_id", userID);

                                // Add a new document with a generated ID
                                db.collection("User")
                                        .add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error adding document", e);
                                            }
                                        });
                                //////////////////////

                                Intent Mainpage = new Intent(Signup.this, Login.class);
                                startActivity(Mainpage);
                            }

                        }
                    });

                } else {
                    Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    public  void openSignup(){
//        Intent singupIntent =new Intent(this, Signup.class);
//        startActivity(singupIntent);
//    }
}