package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PreviousAnnouncement extends AppCompatActivity {
    TextView updateDeleteAnnouncement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_announcement);

        updateDeleteAnnouncement = findViewById(R.id.edit_announcement);
        updateDeleteAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateDeleteAnnouncementIntent = new Intent(PreviousAnnouncement.this,UpdateDeleteAnnouncement.class);
                startActivity(updateDeleteAnnouncementIntent);
            }
        });

    }
}