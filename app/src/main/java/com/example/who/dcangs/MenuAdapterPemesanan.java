package com.example.who.dcangs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by who on 29/09/17.
 */

public class MenuAdapterPemesanan extends RecyclerView.Adapter<MenuAdapterPemesanan.MenuAdapterViewHolder>{

    Context context;
    ArrayList<Menus_Pemesanan> listMenu;
    FirebaseAuth mAuth;
    DatabaseReference ref;

    public MenuAdapterPemesanan(Context ctx, ArrayList<Menus_Pemesanan> list) {
        this.context = ctx;
        this.listMenu = list;
    }

    @Override
    public MenuAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_pemesanan, parent, false);
        return new MenuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuAdapterViewHolder holder, int position) {
        Menus_Pemesanan now = listMenu.get(position);
        holder.tv_harga.setText(now.getHarga());
        holder.tv_produk.setText(now.getProduk());
        holder.tv_jumlah.setText(now.getJumlah());

//        holder.tv_email.setText(now.getEmail());

    }

    @Override
    public int getItemCount() {
        return this.listMenu.size();
    }

    public class MenuAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tv_harga, tv_produk, tv_jumlah, tv_email;

        public MenuAdapterViewHolder(final View itemView) {
            super(itemView);

            tv_harga = (TextView) itemView.findViewById(R.id.tv_harga);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_jumlah = (TextView) itemView.findViewById(R.id.tv_jumlah);

        }

    }

    public void setData(ArrayList<Menus_Pemesanan> arr) {
        this.listMenu = arr;
        notifyDataSetChanged();
    }
}
