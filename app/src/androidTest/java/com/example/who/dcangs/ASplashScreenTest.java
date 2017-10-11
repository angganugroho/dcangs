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

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.junit.Assert.*;

/**
 * Created by who on 12/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ASplashScreenTest {

    @Rule
    public ActivityTestRule<SplashScreen> splashScreenActivityTestRule = new ActivityTestRule<>(SplashScreen.class, true, false);

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

    @Test
    public void test1TimeSplashScreen(){
        splashScreenActivityTestRule.launchActivity(null);
        pauseTestFor(3000);
        intended(hasComponent(Login.class.getName()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}