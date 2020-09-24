package com.sewaseven.additional;

import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UID {

    public static String userID() {
        String userid = "";
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentFirebaseUser != null) {
            userid = currentFirebaseUser.getUid();
        } else {

        }
        return userid;
    }

    public static String convert_user_id_to_user_name(String user_id) {
        String user_name = "";
        final List<String> nameList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.e("xxxxxxxxxxxxx", db.toString());

        db.collection("user")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        nameList.clear();

                        for (DocumentSnapshot snapshot : value) {
                            nameList.add(snapshot.getString("user_id"));
                            Log.e("xxxxxxxxxxxxx", snapshot.getString("user_id"));
                        }

                    }
                });
        if (!nameList.isEmpty()) {
            user_name = nameList.get(0);
        }
        return user_name;
    }


}
