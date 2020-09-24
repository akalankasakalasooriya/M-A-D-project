package com.sewaseven.sewaseven;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sewaseven.database.Announcement_adapter;
import com.sewaseven.database.Announsement;
import com.sewaseven.database.Service;
import com.sewaseven.database.Service_frag_adapter;

import java.util.ArrayList;
import java.util.List;

public class HomeServices extends Fragment {
    View view;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fire_store_list_services;
    private List<Service> services_list;
    private Service_frag_adapter adapter;
    private View service_view;


    public HomeServices() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        service_view = inflater.inflate(R.layout.fragment_announcements_home, container, false);

        return inflater.inflate(R.layout.fragment_home_services, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fire_store_list_services = (RecyclerView) view.findViewById(R.id.home_services_recycle);
        fire_store_list_services.setHasFixedSize(true);
        fire_store_list_services.setLayoutManager(new LinearLayoutManager(getContext()));

        services_list = new ArrayList<>();
        adapter = new Service_frag_adapter(getContext(), services_list);

        fire_store_list_services.setAdapter(adapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Service").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Service model = d.toObject(Service.class);
                        model.setDocID(d.getId());
                        services_list.add(model);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

        });

//        LinearLayout btnServiceProfileS;
//
//        btnServiceProfileS = getView().findViewById(R.id.linktoProfileS);
//        btnServiceProfileS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent servicePage = new Intent(getActivity(),ServicePage.class);
//                startActivity(servicePage);
//            }
//        });
    }
}