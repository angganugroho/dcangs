package com.example.who.dcangs;

import android.app.ProgressDialog;
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
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.splash);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
        }
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvError = (TextView) findViewById(R.id.tvError);
    }

    public void onRegister(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }

    public void onForgot(View f)
    {
        Intent intent = new Intent(getApplicationContext(), Forgot.class);
        startActivity(intent);
    }

    public void OnLogin (View view){
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();


        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            tvError.setText("Please enter email and password");
            return;
        }

        else if(!(TextUtils.isEmpty(email)) && !(TextUtils.isEmpty(password)) && password.length() < 8 ){
            tvError.setText("Password lenght must have atleast 8 character !!");
        }

        else if(TextUtils.isEmpty(email)){
            etEmail.setError("Please enter email");
            return;
        }

        else if(TextUtils.isEmpty(password)){
            etPassword.setError("Please enter password");
            return;
        } else {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.setMessage("Authenticating...");
                                progressDialog.show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), Dashboard.class));

                                Toast.makeText(Login.this, "Login success...", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                tvError.setText("Username or Password Incorrect");
                                return;
                            }
                        }
                    });
        }
    }
}
