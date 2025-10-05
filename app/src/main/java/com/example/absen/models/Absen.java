package com.example.absen.models;

public class Absen {

        private String id;          // ID unik absen (Firebase autoId)
        private String siswaId;     // Relasi ke Siswa
        private String namaSiswa;
        private int kelas;
        private String status;      // "Masuk", "Izin", "Alpha"
        private String tanggal;     // YYYY-MM-DD
        private int pertemuanKe;    // pertemuan ke-n
        private String guru;        // nama guru yang absen

        public Absen() {}

        public Absen(String id, String siswaId, String namaSiswa, int kelas,
                     String status, String tanggal, int pertemuanKe, String guru) {
            this.id = id;
            this.siswaId = siswaId;
            this.namaSiswa = namaSiswa;
            this.kelas = kelas;
            this.status = status;
            this.tanggal = tanggal;
            this.pertemuanKe = pertemuanKe;
            this.guru = guru;
        }

        public String getId() { return id; }
        public String getSiswaId() { return siswaId; }
        public String getNamaSiswa() { return namaSiswa; }
        public int getKelas() { return kelas; }
        public String getStatus() { return status; }
        public String getTanggal() { return tanggal; }
        public int getPertemuanKe() { return pertemuanKe; }
        public String getGuru() { return guru; }

        public void setId(String id) { this.id = id; }
        public void setSiswaId(String siswaId) { this.siswaId = siswaId; }
        public void setNamaSiswa(String namaSiswa) { this.namaSiswa = namaSiswa; }
        public void setKelas(int kelas) { this.kelas = kelas; }
        public void setStatus(String status) { this.status = status; }
        public void setTanggal(String tanggal) { this.tanggal = tanggal; }
        public void setPertemuanKe(int pertemuanKe) { this.pertemuanKe = pertemuanKe; }
        public void setGuru(String guru) { this.guru = guru; }


}
