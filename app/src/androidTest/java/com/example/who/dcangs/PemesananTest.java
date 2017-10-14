package com.example.who.dcangs;

import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * Created by who on 15/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PemesananTest {

//    @Rule
//    public ActivityTestRule<Pemesanan> pemesananActivityTestRule = new ActivityTestRule<>(Pemesanan.class, true, false);

    private void pauseTestFor(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}