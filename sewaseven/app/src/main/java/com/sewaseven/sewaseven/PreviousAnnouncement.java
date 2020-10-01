package com.sewaseven.sewaseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.database.Announcement_adapter;
import com.sewaseven.database.Announsement;
import com.sewaseven.database.FAQModel;
import com.sewaseven.database.FAQ_adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PreviousAnnouncement extends AppCompatActivity {
    TextView announcements_history_total;
    TextView announcements_history_this_week;

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_announsement;
    private List<Announsement> announsement_list;
    private Announcement_adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_announcement);


        fire_store_list_announsement = (RecyclerView) findViewById(R.id.announcements_history_list);
        fire_store_list_announsement.setHasFixedSize(true);
        fire_store_list_announsement.setLayoutManager(new LinearLayoutManager(this));

        announsement_list = new ArrayList<>();
        adapter = new Announcement_adapter(this, announsement_list);

        fire_store_list_announsement.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Announcement").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Announsement model = d.toObject(Announsement.class);
                        model.setDocID(d.getId());
                        announsement_list.add(model);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

        });


        getTotalAnnouncement();
        getTotalAnnouncement_week();


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent gotoList = new Intent(PreviousAnnouncement.this,ServiceList.class);
        startActivity(gotoList);
    }

    private void getTotalAnnouncement_week() {
        announcements_history_this_week = findViewById(R.id.announcements_history_this_week);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        long start = cal.getTimeInMillis();
        long end = System.currentTimeMillis();
        db.collection("Announcement").orderBy("serverTimeStamp").startAt(start).endAt(end)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int countTotal = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Log.e("TAG", document.getId() + " => " + document.get("serverTimeStamp"));
                                countTotal++;
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                        announcements_history_this_week.setText(String.valueOf(countTotal));
                    }
                });

    }

    private void getTotalAnnouncement() {
        announcements_history_total = findViewById(R.id.announcements_history_total);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Announcement")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int countTotal = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                countTotal++;
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                        announcements_history_total.setText(String.valueOf(countTotal));
                    }
                });
    }
}