package com.example.jandi.masjid;

public class model_user {
    private String nama;
    private String jabatan;
    private String gambar;
    private String id;
    private String password;
    private String email;
    private String kontak;
    private String alamat;

    public model_user(String nama, String jabatan, String gambar, String id, String password, String email, String kontak, String alamat) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.gambar = gambar;
        this.id = id;
        this.password = password;
        this.email = email;
        this.kontak = kontak;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.id = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


}
