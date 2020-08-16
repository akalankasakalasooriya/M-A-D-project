package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AskedQuestions extends AppCompatActivity {
    TextView txtFAQUpdate;
    Button newFAQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_questions);

        txtFAQUpdate =findViewById(R.id.FAQUpdate);
        txtFAQUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent FAQUpdateIntent = new Intent(AskedQuestions.this,FAQUpdate.class);
                startActivity(FAQUpdateIntent);
            }
        });


        newFAQ =findViewById(R.id.butn_add_new_Faq);
        newFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFAQIntent = new Intent(AskedQuestions.this,AddNewFaq.class);
                startActivity(newFAQIntent);
            }
        });

    }
}