package com.example.who.dcangs;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.Gravity;

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
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by who on 13/10/2017.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class XDashboardTest {

    @Rule
    public ActivityTestRule<Dashboard> dashboardActivityTestRule = new ActivityTestRule<>(Dashboard.class, true, false);
    public FragmentTestRule<?, Pemesanan> pemesananFragmentTestRule = FragmentTestRule.create(Pemesanan.class);

    private Fragment fragment;
    private Dashboard dashboard;

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
        fragment = new LihatProduk();
    }

    @Test
    public void test1LihatProduk(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_lihatproduk));
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new LihatProduk())
                .commit();
    }

    @Test
    public void test2LihatMap(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new LihatMap())
                .commit();
    }

    @Test
    public void test3Pemesanan(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new Pemesanan())
                .commit();
        onView(withId(R.id.btnPesan)).perform(click());
        pauseTestFor(500);
        onView(withText("Pesanan Sedang Diproses"))
                .inRoot(withDecorView(not(pemesananFragmentTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test4Profile(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new Profile())
                .commit();
    }

    @Test
    public void testLogout(){
        dashboardActivityTestRule.launchActivity(null);
        openDrawer(R.id.drawer_layout);
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_logout));
        pauseTestFor(1000);
        intended(hasComponent(Login.class.getName()));
        pauseTestFor(1000);
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}