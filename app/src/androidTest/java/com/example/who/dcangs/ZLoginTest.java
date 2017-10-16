package com.example.who.dcangs;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by who on 10/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class ZLoginTest {

    public FirebaseAuth mAuth;

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<>(Login.class, true, false);
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
    public void test1LoginNoEmailNoPassword() throws Exception{
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.tvError)).check(matches(withText("Please enter email and password")));
    }

    @Test
    public void test2LoginWithEmailNoPassword() throws Exception{
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.etPassword)).check(matches(hasErrorText("Please enter password")));
    }

    @Test
    public void test3LoginWithPasswordNoEmail() throws Exception{
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.etEmail)).check(matches(hasErrorText("Please enter email")));
    }

    @Test
    public void test4FalsePassEmail() throws Exception{
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText("rifaihabib28@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(2500);
        onView(withId(R.id.tvError)).check(matches(withText("Username or Password Incorrect")));
    }

    @Test
    public void test5IntentRegister() throws Exception{
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.tvRegister1)).perform(click());
        pauseTestFor(500);
        intended(hasComponent(Register.class.getName()));
    }

    @Test
    public void test6IntentForgotPassword() throws Exception{
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.tvForgotPassword)).perform(click());
        pauseTestFor(500);
        intended(hasComponent(Forgot.class.getName()));
    }

    @Test
    public void test7PasswordLessThan8Char(){
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText("rifaihabib28@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.tvError)).check(matches(withText("Password lenght must have atleast 8 character !!")));
    }

    @Test
    public void test8LoginSuccess(){
        loginActivityTestRule.launchActivity(null);
        onView(withId(R.id.etEmail)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());
        pauseTestFor(3000);
        onView(withText("Login success..."))
                .inRoot(withDecorView(not(loginActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        intended(hasComponent(Dashboard.class.getName()));
    }

    @Test
    public void test9LoginSessionNotNull(){
        splashScreenActivityTestRule.launchActivity(null);
        pauseTestFor(1000);
        intended(hasComponent(Dashboard.class.getName()));
        pauseTestFor(1000);
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}