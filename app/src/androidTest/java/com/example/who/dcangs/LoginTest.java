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
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by who on 10/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.DEFAULT)


public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<>(Login.class, true, false);

    private String email = "rifaihabib29@gmail.com";
    private String password = "1234abcd";

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

    @Test
    public void testLoginNoEmailNoPassword(){
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.tvError)).check(matches(withText("Please enter email and password")));
    }

    @Test
    public void testLoginWithEmailNoPassword(){
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.etPassword)).check(matches(hasErrorText("Please enter password")));
    }

    @Test
    public void testLoginWithPasswordNoEmail(){
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.etEmail)).check(matches(hasErrorText("Please enter email")));
    }

    @Test
    public void testFalsePassFalseEmail(){
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText("rifaihabib28@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(2500);
        onView(withId(R.id.tvError)).check(matches(withText("Username or Password Incorrect")));
    }

}