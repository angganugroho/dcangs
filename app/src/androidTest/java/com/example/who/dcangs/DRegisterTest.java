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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Daud on 12-Oct-17.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DRegisterTest {

    @Rule
    public ActivityTestRule<Register> registerActivityTestRule = new ActivityTestRule<>(Register.class, true, false);

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
    public void testARegisterNoData(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testBRegisterNoNamaEmailNohp(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
    }

    @Test
    public void testCRegisterNoNamaEmailPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087738849837"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testDRegisterNoNamaNohpPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testERegisterNoEmailNohpPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testFRegisterNoNamaEmail(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("085456678987"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
    }

    @Test
    public void testGRegisterNoNamaNohp(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
    }

    @Test
    public void testHRegisterNoNamaPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087675454323"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testIRegisterNoEmailNohp(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
    }

    @Test
    public void testJRegisterNoEmailPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087637849837"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testKRegisterNoNohpPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testLRegisterNoNama(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087637747747"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nama)).check(matches(hasErrorText("Please enter your name")));
    }

    @Test
    public void testMRegisterNoNohp(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Please enter your phone number")));
    }

    @Test
    public void testNRegisterNoEmail(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087636636636"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.email)).check(matches(hasErrorText("Please enter your email")));
    }

    @Test
    public void testORegisterNoPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087626626626"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testPRegisterNoPassword(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087626626626"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.password)).check(matches(hasErrorText("Please enter your password")));
    }

    @Test
    public void testQRegisterPasswordLength(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087626626626"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("13423er"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.password)).check(matches(hasErrorText("Password lenght must have atleast 8 character !!")));
    }

    @Test
    public void testRRegisterNohpLength(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087647839"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Your phone number invalid")));
    }

    @Test
    public void testSRegisterNohpPasswordLength(){
        registerActivityTestRule.launchActivity(null);
        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("0876478"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.password)).check(matches(hasErrorText("Password lenght must have atleast 8 character !!")));
        onView(withId(R.id.nohp)).check(matches(hasErrorText("Your phone number invalid")));
    }

    @Test
    public void testTRegisterFailed(){
        registerActivityTestRule.launchActivity(null);

        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifaihabib29@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087637747837"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.userPict)).perform(click());
        pauseTestFor(6000);
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(2500);
        onView(withText("Register Failed"))
                .inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        intended(hasComponent(Register.class.getName()));
    }

    @Test
    public void testURegisterSuccess(){
        registerActivityTestRule.launchActivity(null);

        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifai.habib@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087637747837"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.userPict)).perform(click());
        pauseTestFor(6000);
        onView(withId(R.id.btnRegistrasi)).perform(click());
        pauseTestFor(2500);
        onView(withText("Register Succesfull"))
                .inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        intended(hasComponent(Register.class.getName()));
    }

    @Test
    public void testVRegisterNoSelectedImage(){
        registerActivityTestRule.launchActivity(null);

        onView(withId(R.id.nama)).perform(typeText("Ahmad Rifai Habibullah"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rifai.habib@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText("087637747837"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.btnRegistrasi)).perform(click());
        onView(withText("Please select your image"))
                .inRoot(withDecorView(not(registerActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}