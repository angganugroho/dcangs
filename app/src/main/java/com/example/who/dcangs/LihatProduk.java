package com.example.who.dcangs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class LihatProduk extends android.support.v4.app.Fragment  {

    private DatabaseReference ref, produk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lihat_produk, container, false);

        final ArrayList<Menus_Produk> dataList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_menu_produk);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final MenuAdapterProduk menuAdapter = new MenuAdapterProduk(getContext(), dataList);
        recyclerView.setLayoutManager(layoutManager);

        ref = FirebaseDatabase.getInstance().getReference();
        produk = ref.child("produk");
        produk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    Log.d("AMBIL DATA", ds.child("nama").getValue().toString());
                    dataList.add(new Menus_Produk(ds.child("nama").getValue().toString(), ds.child("harga").getValue().toString(), ds.child("link").getValue().toString()));
                }
//                Log.d("SAMPLE DATA", String.valueOf(dataList.get(2).getHarga()));
                menuAdapter.setData(dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        recyclerView.setAdapter(menuAdapter);
        return view;
    }
}
