package com.example.who.dcangs;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by who on 10/10/17.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.DEFAULT)

public class LoginTest {
    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<>(Login.class, true, false);

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

//    @Test
//    public void testLoginNoEmail(){
//        loginActivityTestRule.launchActivity(null);
//        onView(withId(R.id.etEmail));
//        onView(withId(R.id.etEmail))
//    }

}
