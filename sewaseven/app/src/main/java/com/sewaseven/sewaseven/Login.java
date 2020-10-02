package com.sewaseven.sewaseven;
//login page

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sewaseven.additional.definedFunctions;

public class Login extends AppCompatActivity {
    private Button signupbtn, signinbtn;
    private EditText emailId, passwordId;
    FirebaseAuth FbaseAuth;
    private FirebaseAuth.AuthStateListener authListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FbaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = FbaseAuth.getCurrentUser();
        if (firebaseuser != null) {
            Intent homeIntent = new Intent(Login.this, Home.class);
            startActivity(homeIntent);
            finish();
        }


        emailId = findViewById(R.id.emailtxt);
        passwordId = findViewById(R.id.editTextTextPassword);
        signinbtn = findViewById(R.id.signin);

        signupbtn = (Button) findViewById(R.id.Signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String password = passwordId.getText().toString();
                if (email.isEmpty() && definedFunctions.isValidEmail(email)) {
                    emailId.setError("Enter Email");
                    emailId.requestFocus();
                } else if (password.isEmpty()) {
                    emailId.setError("Enter Password");
                    passwordId.requestFocus();
                }
                if (!email.isEmpty() && !password.isEmpty()) {
                    FbaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            } else {

                                Intent homeIntent = new Intent(Login.this, Home.class);
                                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(homeIntent);
                            }

                        }
                    });

                } else {
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);


    }

    public void openSignup() {
        Intent singupIntent = new Intent(Login.this, Signup.class);
        startActivity(singupIntent);
    }
}