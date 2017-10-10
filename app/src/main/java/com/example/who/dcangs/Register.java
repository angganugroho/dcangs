package com.example.who.dcangs;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistrasi;
    private EditText etNama, etEmail, etNohp, etPassword;
    public ImageView userPict;
    private String nama, email, nohp, password;
    public FirebaseAuth mAuth;
    private DatabaseReference ref, create, noHpUser, namaUser, emailUser, pictUser;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        userPict = (ImageView) findViewById(R.id.userPict);
//        btnRegistrasi = (Button) findViewById(R.id.btnRegistrasi);
        etNama = (EditText) findViewById(R.id.nama);
        etEmail = (EditText) findViewById(R.id.email);
        etNohp = (EditText) findViewById(R.id.nohp);
        etPassword = (EditText) findViewById(R.id.password);

        userPict.setOnClickListener(this);

    }

    public void ActionRegister (View view){

        nama = etNama.getText().toString().trim();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        nohp = etNohp.getText().toString().trim();


        if(TextUtils.isEmpty(nama)){
            etNama.setError("Please enter email");
            return;
        }

        else if(TextUtils.isEmpty(password)){
            etPassword.setError("Please enter password");
            return;
        }

        else if(TextUtils.isEmpty(email)){
            etEmail.setError("Please enter email");
            return;
        }

        else if(TextUtils.isEmpty(nohp)){
            etNohp.setError("Please enter password");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();

                            String uid = mAuth.getCurrentUser().getUid();
                            create = ref.child("Pemesanan").child(uid).child("Profile");

                            namaUser = create.child("Nama");
                            noHpUser = create.child("No Telefon");
                            emailUser = create.child("Email");
                            pictUser = create.child("Pict");

                            if(selectedImage != null){
                                StorageReference riversRef = storageReference.child("User").child(nama);

                                riversRef.putFile(selectedImage)
                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception exception) {

                                            }
                                        });
                            } else {

                            }

                            emailUser.setValue(email);
                            namaUser.setValue(nama);
                            noHpUser.setValue(nohp);
                            pictUser.setValue(nama);
                            Toast.makeText(Register.this, "Register Succesfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Login.class));
                        } else {
                            Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
//            try{
                selectedImage = data.getData();
                userPict.setImageURI(selectedImage);
//            } catch (Exception e){
//
//            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.userPict:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
        }
    }
}
