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

public class HomeServices extends Fragment {
    View view;

    public  HomeServices(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_services, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        LinearLayout btnServiceProfileS;

        btnServiceProfileS = getView().findViewById(R.id.linktoProfileS);
        btnServiceProfileS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent servicePage = new Intent(getActivity(),ServicePage.class);
                startActivity(servicePage);
            }
        });
    }
}