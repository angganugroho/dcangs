package com.example.who.dcangs;

import android.content.Intent;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.view.View;

import org.hamcrest.Matcher;
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
    }

    @Test
    public void testALihatProduk() {
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_lihatproduk));
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new LihatProduk())
                .commit();

        pauseTestFor(5000);

        onView(withId(R.id.rv_menu_produk)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "Click on specific button";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        View add = view.findViewById(R.id.btnPlus);
                        View addToCart = view.findViewById(R.id.addToCart);
                        add.performClick();
                        pauseTestFor(5000);
                        addToCart.performClick();

                    }
                })
        );

        onView(withId(R.id.rv_menu_produk)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "Click on specific button";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        View add = view.findViewById(R.id.btnPlus);
                        View min = view.findViewById(R.id.btnMinus);
                        View addToCart = view.findViewById(R.id.addToCart);
                        add.performClick();
                        min.performClick();
                        pauseTestFor(5000);
                        addToCart.performClick();
                    }
                })
        );
    }

    @Test
    public void testBLihatMap(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_lihatmap));
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new LihatMap())
                .commit();
    }

    @Test
    public void testCPemesananPesan(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_pemesanan));
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new Pemesanan())
                .commit();
        pauseTestFor(500);
        onView(withId(R.id.btnPesan)).perform(click());
        pauseTestFor(500);
        onView(withText("Pesanan Sedang Diproses"))
                .inRoot(withDecorView(not(dashboardActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        pauseTestFor(2000);
    }

    @Test
    public void testDPemesananCancel(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_pemesanan));
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new Pemesanan())
                .commit();
        pauseTestFor(500);
        onView(withId(R.id.btnCancel)).perform(click());
        pauseTestFor(500);
        onView(withText("Pesanan Dibatalkan"))
                .inRoot(withDecorView(not(dashboardActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        pauseTestFor(2000);
    }

    @Test
    public void testEProfile(){
        dashboardActivityTestRule.launchActivity(null);
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(open());
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_profile));
        dashboard = dashboardActivityTestRule.launchActivity(new Intent());
        dashboard.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new Profile())
                .commit();
    }

    @Test
    public void testFLogout(){
        dashboardActivityTestRule.launchActivity(null);
        openDrawer(R.id.drawer_layout);
        onView(withId(R.id.navigation_view)).perform(navigateTo(R.id.nav_logout));
        pauseTestFor(1000);
        intended(hasComponent(Login.class.getName()));
        pauseTestFor(1000);
    }

    @Test
    public void testGNullSession() {
        dashboardActivityTestRule.launchActivity(null);
        intended(hasComponent(Login.class.getName()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}