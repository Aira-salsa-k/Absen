package com.example.absen.models;

public class Siswa {
    private String id;
    private String nama;
    private String kelas;

    public Siswa() {} // Diperlukan Firebase

    public Siswa(String id, String nama, String kelas) {
        this.id = id;
        this.nama = nama;
        this.kelas = kelas;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getKelas() { return kelas; }

    public void setId(String id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setKelas(String kelas) { this.kelas = kelas; }
}
