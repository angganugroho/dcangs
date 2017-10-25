package com.example.who.dcangs;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Created by who on 24/10/2017.
 */

public class RegTest {

    @Rule
    public ActivityTestRule<Register> registerActivityTestRule = new ActivityTestRule<>(Register.class, true, false);
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference data = database.getReference();
    private DatabaseReference ref = database.getReference("Pemesanan");
    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    long aktual = 0;
    long expected = 0;
    String idGlobal, idUser, a, b, c, d;
    String nama = "habib14";
    String email = "habib14@gmail.com";
    String nohp = "087755997588";
    String password = "12345678";
    String pict = nama;

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
    public void testVRegisterSuccess(){
        registerActivityTestRule.launchActivity(null);

        pauseTestFor(4000);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aktual = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        onView(withId(R.id.nama)).perform(typeText(nama), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.nohp)).perform(typeText(nohp), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.userPict)).perform(click());
        pauseTestFor(6000);

        onView(withId(R.id.btnRegistrasi)).perform(click());

        pauseTestFor(4000);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                expected = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        pauseTestFor(2500);

        long a = expected-aktual;

        assertEquals(1, a);
    }

    @Test
    public void testDataCocok(){
        registerActivityTestRule.launchActivity(null);

        pauseTestFor(2500);

        data.child("1 ID GLOBAL").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                idGlobal = dataSnapshot.getValue().toString();
//                Log.d("ID GLOBAL", idGlobal);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        pauseTestFor(2500);

        data.child("Users").child(idGlobal).child("Uid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                idUser = dataSnapshot.getValue().toString();
                Log.d("ID USER", idUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        pauseTestFor(2500);

        String emailCheck = mAuth.getCurrentUser().getEmail();
        Log.d("EMAILLLLL", emailCheck);

        data.child("Pemesanan").child(idUser).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                a = dataSnapshot.child("Email").getValue().toString();
                b = dataSnapshot.child("Nama").getValue().toString();
                c = dataSnapshot.child("No Telefon").getValue().toString();
                d = dataSnapshot.child("Pict").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        pauseTestFor(2500);

        assertEquals(email, a);
        assertEquals(email, emailCheck);
        assertEquals(nama, b);
        assertEquals(nohp, c);
        assertEquals(pict, d);

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}
