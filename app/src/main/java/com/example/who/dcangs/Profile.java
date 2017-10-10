package com.example.who.dcangs;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    private DatabaseReference ref, data;
    public FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    TextView tvNama, tvEmail, tvNohp;
    ImageView ivProfil;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("User");
        final String uid = mAuth.getCurrentUser().getUid().toString();
        ref = FirebaseDatabase.getInstance().getReference();
        data = ref.child("Pemesanan").child(uid).child("Profile");

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvNama = (TextView) view.findViewById(R.id.tv_Nama);
        tvEmail = (TextView) view.findViewById(R.id.tv_Email);
        tvNohp = (TextView) view.findViewById(R.id.tv_Nohp);
        ivProfil = (ImageView) view.findViewById(R.id.iv_Profil);

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                String a = dataSnapshot.child("Email").getValue().toString();
                String b = dataSnapshot.child("Nama").getValue().toString();
                String c = dataSnapshot.child("No Telefon").getValue().toString();
                String d = dataSnapshot.child("Pict").getValue().toString();
//                    Log.d("Data Value", a);
                tvEmail.setText(a);
                tvNama.setText(b);
                tvNohp.setText(c);

                mStorageRef.child(d).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        Picasso.with(getContext()).load(uri).into(ivProfil);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        return view;
    }

}
