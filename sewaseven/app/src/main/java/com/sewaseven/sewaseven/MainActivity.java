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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button signupbtn,signinbtn;
    private EditText emailId , passwordId;
     FirebaseAuth FbaseAuth;
    private  FirebaseAuth.AuthStateListener authListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FbaseAuth = FirebaseAuth.getInstance();
        authListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               // FirebaseUser fireUser = new FbaseAuth.getCurrentUser();
                if (FbaseAuth.getCurrentUser() != null){
                    Intent homeIntent = new Intent(MainActivity.this,Home.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        };

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
                String password =passwordId.getText().toString();
                if (email.isEmpty())
                {
                    emailId.setError("Enater Email");
                    emailId.requestFocus();
                }
                if (password.isEmpty())
                {
                    emailId.setError("Enater Password");
                    emailId.requestFocus();
                }
                if(!email.isEmpty() && !password.isEmpty())
                {
                    FbaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //startActivity(MainActivity.this,Home.class);
                                Intent homeIntent = new Intent(MainActivity.this,Home.class);
                                startActivity(homeIntent);
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public  void openSignup(){
        Intent singupIntent =new Intent(MainActivity.this, Signup.class);
        startActivity(singupIntent);
    }
}