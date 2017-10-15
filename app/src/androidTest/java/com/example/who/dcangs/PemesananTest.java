package com.example.who.dcangs;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by who on 15/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PemesananTest {

    @Rule
    public FragmentTestRule<?, Pemesanan> pemesananFragmentTestRule = FragmentTestRule.create(Pemesanan.class);

    @Rule
    public ActivityTestRule<Dashboard> dashboardActivityTestRule = new ActivityTestRule<>(Dashboard.class, true, false);

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
    public void test1Pesan() throws Exception{
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.btnPesan)).perform(click());
        pauseTestFor(500);
        onView(withText("Pesanan Sedang Diproses"))
                .inRoot(withDecorView(not(pemesananFragmentTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test2Batal() throws Exception{
        pemesananFragmentTestRule.launchActivity(null);
        onView(withId(R.id.btnCancel)).perform(click());
        pauseTestFor(500);
        onView(withText("Pesanan Dibatalkan"))
                .inRoot(withDecorView(not(pemesananFragmentTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}