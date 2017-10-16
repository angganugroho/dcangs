package com.example.who.dcangs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
//            Log.d("USER", mAuth.getCurrentUser()+"");
            finish();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
        } else {
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(3000);
                    }catch(Exception e){
//                        e.printStackTrace();
                    }finally{
                        Intent intent = new Intent(SplashScreen.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            timerThread.start();
        }
    }
}
