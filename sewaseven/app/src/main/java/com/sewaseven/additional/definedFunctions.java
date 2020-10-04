package com.sewaseven.additional;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.lang3.math.NumberUtils;

public class definedFunctions {

    public static String userID() {
        String userid = "";
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentFirebaseUser != null) {
            userid = currentFirebaseUser.getUid();
        } else {

        }
        return userid;
    }

    public static float calAVG(float sum, int count) {
        float avg = 0;
        if (count!=0)
        {
            avg = sum/(float)count;
        }
        return avg;
    }

    public static boolean isValidEmail(String email){
        boolean terurnValue= false;
        email.trim();
        if (email.contains("@") && email.contains(".") ){
            terurnValue = true;
        }
        return terurnValue;
    }

    public static boolean isValidPhone(String phone){
        boolean returnValue= false;
        phone=phone.trim();
        Log.e("test",phone);
        try {
            if(phone.length()==10 && NumberUtils.isDigits(phone))
            {
                returnValue = true;
            }
        }catch (Exception e){
            throw e;
        }
        return returnValue;
    }



}
