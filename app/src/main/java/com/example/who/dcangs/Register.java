package com.example.who.dcangs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register extends AppCompatActivity implements View.OnClickListener{

    public EditText etNama, etEmail, etNohp, etPassword;
    public ImageView userPict;
    public String nama, email, nohp, password;
    public FirebaseAuth mAuth;
    private DatabaseReference ref, create, noHpUser, namaUser, emailUser, pictUser, id;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference count = database.getReference("Pemesanan");
    private static final int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage;
    private StorageReference storageReference;
    public String jml;
    public int jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        userPict = (ImageView) findViewById(R.id.userPict);
        etNama = (EditText) findViewById(R.id.nama);
        etEmail = (EditText) findViewById(R.id.email);
        etNohp = (EditText) findViewById(R.id.nohp);
        etPassword = (EditText) findViewById(R.id.password);

        userPict.setOnClickListener(this);

    }

    public void register(){
        nama = etNama.getText().toString().trim();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        nohp = etNohp.getText().toString().trim();

        if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(password) && TextUtils.isEmpty(email) && TextUtils.isEmpty(nohp)){
            etNama.setError("Please enter your name");
            etPassword.setError("Please enter your password");
            etEmail.setError("Please enter your email");
            etNohp.setError("Please enter your phone number");
            return;
        }

        else if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(email) && TextUtils.isEmpty(nohp)){
            etNama.setError("Please enter your name");
            etEmail.setError("Please enter your email");
            etNohp.setError("Please enter your phone number");
            return;
        }

        else if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            etNama.setError("Please enter your name");
            etEmail.setError("Please enter your email");
            etPassword.setError("Please enter your password");
            return;
        }

        else if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(nohp) && TextUtils.isEmpty(password)){
            etNama.setError("Please enter your name");
            etNohp.setError("Please enter your phone number");
            etPassword.setError("Please enter your password");
            return;
        }

        else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(nohp) && TextUtils.isEmpty(password)){
            etEmail.setError("Please enter your email");
            etNohp.setError("Please enter your phone number");
            etPassword.setError("Please enter your password");
        }

        else if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(email)){
            etNama.setError("Please enter your name");
            etEmail.setError("Please enter your email");
        }

        else if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(nohp)){
            etNama.setError("Please enter your name");
            etNohp.setError("Please enter your phone number");
        }

        else if(TextUtils.isEmpty(nama) && TextUtils.isEmpty(password)){
            etNama.setError("Please enter your name");
            etPassword.setError("Please enter your password");
        }

        else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(nohp)){
            etEmail.setError("Please enter your email");
            etNohp.setError("Please enter your phone number");
        }

        else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            etPassword.setError("Please enter your password");
            etEmail.setError("Please enter your email");
        }

        else if(TextUtils.isEmpty(nohp) && TextUtils.isEmpty(password)){
            etPassword.setError("Please enter your password");
            etNohp.setError("Please enter your phone number");
        }

        else if(TextUtils.isEmpty(nama)){
            etNama.setError("Please enter your name");
        }

        else if(TextUtils.isEmpty(nohp)){
            etNohp.setError("Please enter your phone number");
        }

        else if(TextUtils.isEmpty(email)){
            etEmail.setError("Please enter your email");
        }

        else if(TextUtils.isEmpty(password)){
            etPassword.setError("Please enter your password");
        }

        else if((!(TextUtils.isEmpty(password))) && password.length() < 8 && (!(TextUtils.isEmpty(nohp))) && nohp.length() < 10){
            etPassword.setError("Password lenght must have atleast 8 character !!");
            etNohp.setError("Your phone number invalid");
        }

        else if((!(TextUtils.isEmpty(password))) && password.length() < 8){
            etPassword.setError("Password lenght must have atleast 8 character !!");
        }

        else if((!(TextUtils.isEmpty(nohp))) && nohp.length() < 10){
            etNohp.setError("Your phone number invalid");
        }

        else if(selectedImage == null){
            Toast.makeText(Register.this, "Please select your image", Toast.LENGTH_SHORT).show();
        }

        else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                finish();

                                final String uid = mAuth.getCurrentUser().getUid();
                                create = ref.child("Pemesanan").child(uid).child("Profile");

                                namaUser = create.child("Nama");
                                noHpUser = create.child("No Telefon");
                                emailUser = create.child("Email");
                                pictUser = create.child("Pict");
                                id = ref.child("Users");

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

                                emailUser.setValue(email);
                                namaUser.setValue("hatma");
                                noHpUser.setValue(nohp);
                                pictUser.setValue(nama);

                                ref.child("1 ID GLOBAL").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        jml = dataSnapshot.getValue().toString();
                                        jumlah = Integer.parseInt(jml) + 1;
                                        id.child(jumlah+"").child("Uid").setValue(uid);

                                        ref.child("1 ID GLOBAL").setValue(jumlah);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                                Toast.makeText(Register.this, "Register Succesfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void ActionRegister (View view){

        register();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            try{
                selectedImage = data.getData();
                userPict.setImageURI(selectedImage);
            } catch (Exception e){

            }
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
