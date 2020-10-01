package com.sewaseven.sewaseven;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.StorageReference;
import com.sewaseven.additional.CircleTransform;
import com.sewaseven.database.Announsement;
import com.squareup.picasso.Picasso;


public class HomeAnnouncements extends Fragment {
    FirebaseFirestore firebaseFirestore;
    private View announcement_view;
    private RecyclerView my_announcement_list;

    //////////

    TextView name_;
    TextView description_;
    ImageView announcement_pic_;
    ImageView profile_pic_;
    private FirestoreRecyclerAdapter adapter;

    public HomeAnnouncements() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        announcement_view = inflater.inflate(R.layout.fragment_announcements_home, container, false);
        View announcement_single = inflater.inflate(R.layout.recycle_announcement_single, container, false);
        my_announcement_list = (RecyclerView) announcement_view.findViewById(R.id.home_recycle_announcement);
        my_announcement_list.setLayoutManager(new LinearLayoutManager(getContext()));
        ///////////////////////
        name_ = announcement_single.findViewById(R.id.home_recycle_announcement_pro_name);
        description_ = announcement_single.findViewById(R.id.home_recycle_announcement_pro_caption);
        announcement_pic_ = announcement_single.findViewById(R.id.home_recycle_announcement_pic);
        profile_pic_ = announcement_single.findViewById(R.id.home_recycle_announcement_pro_pic);

        return announcement_view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }

    private class accouncements_view_holder extends RecyclerView.ViewHolder {


        TextView name = name_;
        TextView description = description_;
        ImageView announcement_pic = announcement_pic_;
        ImageView profile_pic = profile_pic_;

        public accouncements_view_holder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.home_recycle_announcement_pro_name);
            description = (TextView) view.findViewById(R.id.home_recycle_announcement_pro_caption);
            announcement_pic = (ImageView) view.findViewById(R.id.home_recycle_announcement_pic);
            profile_pic = (ImageView) view.findViewById(R.id.home_recycle_announcement_pro_pic);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ////////////////////////////
        ////////////////////////
        firebaseFirestore = FirebaseFirestore.getInstance();
        //RecyclerView  = getView().findViewById(R.id.home_recycle_announcement);


        //query
        Query query = firebaseFirestore.collection("Announcement");

        //options
        FirestoreRecyclerOptions<Announsement> options = new FirestoreRecyclerOptions.Builder<Announsement>()
                .setQuery(query, Announsement.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Announsement, accouncements_view_holder>(options) {
            @NonNull
            @Override
            public accouncements_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_announcement_single, parent, false);
                accouncements_view_holder retHolder = new accouncements_view_holder(view);

                return retHolder;
            }

            @Override
            protected void onBindViewHolder(@NonNull accouncements_view_holder holder, int position, @NonNull Announsement model) {
                Picasso.get().setLoggingEnabled(true);
                //StorageReference storageReference =;
                holder.name.setText(model.getName());
                holder.description.setText(model.getDescription());
                Log.e("---------", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");


                Picasso.get().load(model.getImagePath()).into(holder.announcement_pic);

                //Log.e("---------",model.getImagePath());

                Picasso.get().load(model.getProPicPath()).transform(new CircleTransform()).into(holder.profile_pic);

            }
        };

        my_announcement_list.setHasFixedSize(true);
        // home_announcements_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        my_announcement_list.setAdapter(adapter);


        ////////////////////
        //////////////
        ///////////////
        ////////////
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
