package com.sewaseven.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sewaseven.sewaseven.Dashboard;
import com.sewaseven.sewaseven.R;
import com.sewaseven.sewaseven.ServicePage;

import java.util.List;

public class Service_List_adapter extends RecyclerView.Adapter<Service_List_adapter.Service_view_holder> {
    private Context mCtx;
    private List<Service> Service_list;

    public Service_List_adapter(Context mCtx, List<Service> Service_list) {
        this.mCtx = mCtx;
        this.Service_list = Service_list;
    }


    @NonNull
    @Override
    public Service_List_adapter.Service_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Service_List_adapter.Service_view_holder(
                LayoutInflater.from(mCtx).inflate(R.layout.recycle_home_services,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Service_List_adapter.Service_view_holder holder, int position) {
        Service service_model = Service_list.get(position);
        holder.service_name.setText(service_model.getName());


    }

    @Override
    public int getItemCount() {
        return Service_list.size();
    }

    class Service_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView service_name;
        public Service_view_holder(@NonNull View itemView) {
            super(itemView);
            service_name =itemView.findViewById(R.id.home_services_single_name);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Service service_model= Service_list.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, Dashboard.class);
            intent.putExtra("docID", service_model.getDocID());
            mCtx.startActivity(intent);


        }
    }
}
