package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AskedQuestions extends AppCompatActivity {
    TextView txtFAQUpdate;
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
    }
}