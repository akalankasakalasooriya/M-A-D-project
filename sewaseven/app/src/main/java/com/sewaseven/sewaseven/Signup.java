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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class Signup extends AppCompatActivity {
    private EditText emailId , passwordId;
    private FirebaseAuth FbaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        FbaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editTextTextemail);
        passwordId = findViewById(R.id.editTextTextPassword);
        Button signupbtn = (Button) findViewById(R.id.newUserbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String password =passwordId.getText().toString();
                if (email.isEmpty())
                {
                    emailId.setError("Enter Email");
                    emailId.requestFocus();
                }
                if (password.isEmpty())
                {
                    emailId.setError("Enter Password");
                    emailId.requestFocus();
                }
                if(!email.isEmpty() && !password.isEmpty())
                {
                    FbaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Signup.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                Log.e("DEBUG", task.getException().toString());
                            }
                            else{
                                //startActivity(MainActivity.this,Home.class);
                                Intent Mainpage = new Intent(Signup.this,MainActivity.class);
                                startActivity(Mainpage);
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(Signup.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    public  void openSignup(){
//        Intent singupIntent =new Intent(this, Signup.class);
//        startActivity(singupIntent);
//    }
}