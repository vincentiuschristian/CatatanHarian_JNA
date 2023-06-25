package com.example.catatanharian;

public class Catatan {
    private final String namaFile;
    private final String isiCatatan;

    public Catatan(String namaFile, String isiCatatan) {
        this.namaFile = namaFile;
        this.isiCatatan = isiCatatan;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public String getIsiCatatan() {
        return isiCatatan;
    }
}
