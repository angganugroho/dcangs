package com.example.who.dcangs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity{

    public String email, password;
    public EditText etEmail, etPassword;
    public TextView tvError;
    public FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
        }
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvError = (TextView) findViewById(R.id.tvError);
    }

    public void OnRegister (View view){
        startActivity(new Intent(this, Register.class));
    }

    public void OnLogin (View view){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            etEmail.setError("Please enter email");
            return;
        }

        if(TextUtils.isEmpty(password)){
            etPassword.setError("Please enter password");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));

                            Toast.makeText(Login.this, "Login success...", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            tvError.setText("Username or Password Incorrect");
//                            Toast.makeText(Login.this, "Login failed...", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }
}
