package com.example.who.dcangs;

/**
 * Created by who on 29/09/17.
 */

public class Menus_Pemesanan {
    String produk;
    String harga;
    String jumlah;
    String email;

    public Menus_Pemesanan(String produk, String jumlah, String harga) {
        this.produk = produk;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public Menus_Pemesanan(String email){
        this.email = email;
    }

    public String getProduk() {
        return produk;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah() {
        return jumlah;
    }

}
