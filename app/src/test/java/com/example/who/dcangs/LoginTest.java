package com.example.who.dcangs;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Test;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by who on 18/10/2017.
 */



public class LoginTest {

    Login l;

    @Test
    public void regisIntent() throws Exception{
        Login login = new Login();

        TextView btnRegis = (TextView) l.findViewById(R.id.tvRegister1);
        btnRegis.performClick();

        startActivity(new Intent(, Register.class));

    }

}