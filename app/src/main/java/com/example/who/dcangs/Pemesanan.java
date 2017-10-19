package com.example.who.dcangs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pemesanan extends android.support.v4.app.Fragment implements View.OnClickListener{

    private DatabaseReference ref, dataPesan, nama, pesan;
    FirebaseAuth mAuth;
    TextView tvNama;
    Button btnPesan, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pemesanan, container, false);

        tvNama = (TextView) view.findViewById(R.id.tv_Nama);
        btnPesan = (Button) view.findViewById(R.id.btnPesan);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnPesan.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


        final ArrayList<Menus_Pemesanan> dataList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_menu_pemesanan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final MenuAdapterPemesanan menuAdapter = new MenuAdapterPemesanan(getContext(), dataList);
        recyclerView.setLayoutManager(layoutManager);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        String uid = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference();
        nama = ref.child("Pemesanan").child(uid).child("Profile").child("Nama");
        dataPesan = ref.child("Pemesanan").child(uid).child("Produk");

        nama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue().toString();
                tvNama.setText(a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        dataPesan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        dataList.add(new Menus_Pemesanan(ds.child("Nama Produk").getValue().toString(), ds.child("Jumlah").getValue().toString(), ds.child("Total Harga").getValue().toString()));
                    }
                    menuAdapter.setData(dataList);
                } catch (Exception e){

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        recyclerView.setAdapter(menuAdapter);
        return view;
    }

    public void pesan(){
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        String uid = mAuth.getCurrentUser().getUid();
        pesan = ref.child("Pemesanan").child(uid);
        pesan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("Produk")) {
                    Toast.makeText(getContext(), "Pesanan Sedang Diproses", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Silakan Pesan Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), Dashboard.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void cancel(){
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        String uid2 = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Pemesanan").child(uid2).child("Produk").removeValue();
        startActivity(new Intent(getContext(), Dashboard.class));
        Toast.makeText(getContext(), "Pesanan Dibatalkan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPesan:
                pesan();
                break;
            case R.id.btnCancel:
                cancel();
                break;
        }
    }
}
