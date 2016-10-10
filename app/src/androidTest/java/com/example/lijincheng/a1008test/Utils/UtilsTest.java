package com.example.lijincheng.a1008test.Utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lijincheng on 10/9/16.
 */
public class UtilsTest {

    Context contx;
    @Before
    public void setUp() throws Exception {
        contx = InstrumentationRegistry.getTargetContext();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getProperity() throws Exception {
        String value1 = Utils.getProperity("AlbumAPIAdr", contx);
        String value2 = Utils.getProperity("PhotoAPIAdr", contx);

        assertEquals("https://jsonplaceholder.typicode.com/albums",value1);
        assertEquals("https://jsonplaceholder.typicode.com/photos",value2);
    }

}