package com.example.who.dcangs;

/**
 * Created by who on 26/09/17.
 */

public class Menus_Produk {
    String nama;
    String harga;
    String gambar;

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getGambar() {
        return gambar;
    }

    public Menus_Produk(String nama, String harga, String gambar) {

        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;

    }
}
