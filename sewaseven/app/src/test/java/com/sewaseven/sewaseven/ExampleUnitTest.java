package com.sewaseven.sewaseven;

import android.util.Log;

import com.sewaseven.additional.definedFunctions;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     *
     * R.K.Rajapaksha - IT19044446
     * S. A. H. A Sakalasooriya - IT19051208
     * T.M.K.L.Thennakoon - IT19011462
     * E.M.A.P.De.Seram - IT19021058
     *
     *
     * **/

    @Test
    public void is_correct() {
        //by IT19044446
        assertEquals(2.5005,definedFunctions.calAVG((float)5.001,2),0.0001);
        assertEquals(true,definedFunctions.isValidEmail("mail@gmail.com"));
        assertEquals(false,definedFunctions.isValidPhone("012345678998"));

        //by IT19051208
        assertEquals(1,definedFunctions.calAVG((float)10,10),0);
        assertEquals(true,definedFunctions.isValidEmail("akalanka@gmail.lk"));
        assertEquals(true,definedFunctions.isValidPhone("0772637357"));


        //by IT19011462
        assertEquals(15000,definedFunctions.calAVG((float)60000,4),0.1);
        assertEquals(true,definedFunctions.isValidEmail("abcd@efgh.com"));
        assertEquals(false,definedFunctions.isValidPhone("12.44"));

        //by IT19021058
        assertEquals(133.367,definedFunctions.calAVG((float)400.1,3),0.01);
        assertEquals(true,definedFunctions.isValidEmail("anjana@xyz.com"));
        assertEquals(false,definedFunctions.isValidPhone("abcdefghij"));
    }
}