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
import com.sewaseven.additional.definedFunctions;
import com.sewaseven.database.Service;
import com.sewaseven.database.Service_List_adapter;
import com.sewaseven.database.Service_frag_adapter;

import java.util.ArrayList;
import java.util.List;

public class ServiceList extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_search;
    private List<Service> service_list;
    private Service_List_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        fire_store_list_search = (RecyclerView) findViewById(R.id.service_list_recycle);
        fire_store_list_search.setHasFixedSize(true);
        fire_store_list_search.setLayoutManager(new LinearLayoutManager(this));


        service_list = new ArrayList<>();
        adapter = new Service_List_adapter(this, service_list);

        fire_store_list_search.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Service").whereEqualTo("ownerID", definedFunctions.userID())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Service model = d.toObject(Service.class);
                                model.setDocID(d.getId());
                                service_list.add(model);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }

                });

    }

    @Override
    public void onBackPressed() {
        Intent gotoList = new Intent(ServiceList.this,Mainmenu.class);
        startActivity(gotoList);
    }
}