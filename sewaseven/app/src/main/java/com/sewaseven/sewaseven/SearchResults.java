package com.sewaseven.sewaseven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.database.Service;
import com.sewaseven.database.Service_frag_adapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_search;
    private List<Service> search_list;
    private Service_frag_adapter adapter;
    private View service_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        fire_store_list_search = (RecyclerView) findViewById(R.id.search_result_recycle);
        fire_store_list_search.setHasFixedSize(true);
        fire_store_list_search.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        Log.e("xxxxxxxx",intent.toString());
        String search_key_word = intent.getStringExtra("searchData");
        //search_key_word.trim();
        Log.e("xxxxxxxx",search_key_word);

        search_list = new ArrayList<>();
        adapter = new Service_frag_adapter(this, search_list);

        fire_store_list_search.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Service").orderBy("name").startAt(search_key_word).endAt(search_key_word+"\uf8ff")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Service model = d.toObject(Service.class);
                        model.setDocID(d.getId());
                        search_list.add(model);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

        });



    }
}