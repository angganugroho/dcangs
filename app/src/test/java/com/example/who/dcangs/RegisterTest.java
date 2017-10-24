package com.example.who.dcangs;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

/**
 * Created by who on 19/10/2017.
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})

public class RegisterTest {

//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference ref = database.getReference("Pemesanan");
    DatabaseReference mockedDatabaseReference, count;
    Register register;
    long jumlahUserAktual;


//    public long getCount(){
//        return jumlahUserAktual;
//    }
//
//    public void setCount(long jumlahUserAktual){
//        this.jumlahUserAktual = jumlahUserAktual;
//    }

    @Before
    public void before() {

//        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

    }

//    @Test
//    public void register_success() throws Exception {
//
//        count = mockedDatabaseReference.child("Pemesanan");
//
//        count.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                long now = dataSnapshot.getChildrenCount();
//
//                long exp = 6;
//
//                assertEquals(now, exp);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//        register.nama = "Ahmad Rifai Habibullah";
//        register.nohp = "082244725059";
//        register.password = "12345678abc";
//        register.email = "rifai.habib@gmail.com";
//
//    }

    @Test
    public void regis() {

        when(mockedDatabaseReference.child("Pemesanan")).thenReturn(mockedDatabaseReference);

        doAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];

                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)

                valueEventListener.onDataChange(mockedDataSnapshot);

//                setCount(mockedDataSnapshot.getChildrenCount());

                jumlahUserAktual = mockedDataSnapshot.getChildrenCount();

                long expected = mockedDataSnapshot.getChildrenCount() + 1;

                assertEquals(expected, jumlahUserAktual);

                return null;
            }
        }).when(mockedDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));

    }
}