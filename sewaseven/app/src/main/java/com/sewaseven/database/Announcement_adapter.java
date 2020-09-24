package com.sewaseven.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sewaseven.sewaseven.R;
import com.sewaseven.sewaseven.UpdateDeleteAnnouncement;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Announcement_adapter extends RecyclerView.Adapter<Announcement_adapter.Announcement_view_holder> {
    private Context mCtx;
    private List<Announsement> Announsement_list;

    public Announcement_adapter(Context mCtx, List<Announsement> Announsement_list) {
        this.mCtx = mCtx;
        this.Announsement_list = Announsement_list;
    }


    @NonNull
    @Override
    public Announcement_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Announcement_view_holder(
                LayoutInflater.from(mCtx).inflate(R.layout.recycle_announcements_history_single, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Announcement_view_holder holder, int position) {
        Announsement ansmnt = Announsement_list.get(position);
        Picasso.get().load(ansmnt.getImagePath()).into(holder.image);
        holder.details.setText(ansmnt.getDescription());

    }

    @Override
    public int getItemCount() {
        return Announsement_list.size();
    }

    class Announcement_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView details;
        ImageView image;

        public Announcement_view_holder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.announcements_history_single_description);
            image = itemView.findViewById(R.id.announcements_history_single_img);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Announsement ansmnt = Announsement_list.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateDeleteAnnouncement.class);
            intent.putExtra("docID", ansmnt.getDocID());
            mCtx.startActivity(intent);


        }
    }
}
