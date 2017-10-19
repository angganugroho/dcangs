package com.example.who.dcangs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by who on 26/09/17.
 */

public class MenuAdapterProduk extends RecyclerView.Adapter<MenuAdapterProduk.MenuAdapterViewHolder>{

    Context context;
    ArrayList<Menus_Produk> listMenu;
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    FirebaseAuth mAuth;
    DatabaseReference ref, pemesanan, emailPemesan, namaProdukDipesan, jumlahPesan, totalHarga, namaProdukDipesanChild;

    public MenuAdapterProduk(Context ctx, ArrayList<Menus_Produk> list){
        this.context = ctx;
        this.listMenu = list;
    }

    @Override
    public MenuAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_produk, parent, false);
        return new MenuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuAdapterViewHolder holder, int position) {
        Menus_Produk now = listMenu.get(position);
        holder.tv_price.setText(now.getHarga());
        holder.tv_name.setText(now.getNama());

        mStorageRef.child(now.getGambar()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri).into(holder.iv);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.listMenu.size();
    }

    public class MenuAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv_name, tv_price, tv_jumlah;
        Button btnAddToCart;
        ImageButton btnPlus, btnMinus;
        int jumlah;


        public MenuAdapterViewHolder(final View itemView) {
            super(itemView);
            mAuth = FirebaseAuth.getInstance();
            iv = (ImageView) itemView.findViewById(R.id.iv_menu);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_jumlah = (TextView) itemView.findViewById(R.id.jumlah);
            btnPlus = (ImageButton) itemView.findViewById(R.id.btnPlus);
            btnMinus = (ImageButton) itemView.findViewById(R.id.btnMinus);
            btnAddToCart = (Button) itemView.findViewById(R.id.addToCart);


            this.btnPlus.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    jumlah = Integer.parseInt(tv_jumlah.getText().toString());
                    tv_jumlah.setText((jumlah + 1) + "");
                }
            });
            this.btnMinus.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    jumlah = Integer.parseInt(tv_jumlah.getText().toString());
                    if(jumlah == 1){
                        tv_jumlah.setText(1 + "");
                    } else {
                        tv_jumlah.setText((jumlah - 1) + "");
                    }
                }
            });
            this.btnAddToCart.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){

                    int harga = Integer.parseInt(tv_price.getText().toString());
                    int jmlPesan = Integer.parseInt(tv_jumlah.getText().toString());
                    int totHarga = harga * jmlPesan;

                    ref = FirebaseDatabase.getInstance().getReference();

                    String uid = mAuth.getCurrentUser().getUid();
                    pemesanan = ref.child("Pemesanan").child(uid);

                    namaProdukDipesan = pemesanan.child("Produk").child(tv_name.getText().toString());
                    jumlahPesan = pemesanan.child("Produk").child(tv_name.getText().toString()).child("Jumlah");
                    totalHarga = pemesanan.child("Produk").child(tv_name.getText().toString()).child("Total Harga");
                    namaProdukDipesanChild = pemesanan.child("Produk").child(tv_name.getText().toString()).child("Nama Produk");

                    namaProdukDipesan.setValue(tv_name.getText().toString());
                    jumlahPesan.setValue(tv_jumlah.getText().toString());
                    totalHarga.setValue(totHarga);
                    namaProdukDipesanChild.setValue(tv_name.getText().toString());

                    jumlah = Integer.parseInt(tv_jumlah.getText().toString());
                    tv_jumlah.setText(1 + "");
                    Toast.makeText(itemView.getContext(), "Berhasil ditambahkan...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setData(ArrayList<Menus_Produk> arr) {
        this.listMenu = arr;
        notifyDataSetChanged();
    }
}
