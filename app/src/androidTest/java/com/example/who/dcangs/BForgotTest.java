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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by who on 12/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BForgotTest {

    @Rule
    public ActivityTestRule<Forgot> forgotActivityTestRule = new ActivityTestRule<>(Forgot.class, true, false);

    private void pauseTestFor(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1ForgotNoEmail(){
        forgotActivityTestRule.launchActivity(null);
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnReset)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.email)).check(matches(hasErrorText("Enter your registered email")));
    }

    @Test
    public void test2ForgotEmailNotRegistered(){
        forgotActivityTestRule.launchActivity(null);
        onView(withId(R.id.email)).perform(typeText("blackwhite@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.btnReset)).perform(click());
        pauseTestFor(500);
        onView(withText("Failed to send reset email!"))
                .inRoot(withDecorView(not(forgotActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test3SuccessForgotPassword(){
        forgotActivityTestRule.launchActivity(null);
        onView(withId(R.id.email)).perform(typeText("daud.muhajir11@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.btnReset)).perform(click());
        pauseTestFor(1500);
        onView(withText("We have sent you instructions to reset your password!"))
                .inRoot(withDecorView(not(forgotActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
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