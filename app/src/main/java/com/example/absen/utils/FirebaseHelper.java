package com.example.absen.utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.absen.models.Siswa;

public class FirebaseHelper {
//    private static DatabaseReference mDatabase;
//
//    public static DatabaseReference getDatabase() {
//        if (mDatabase == null) {
//            mDatabase = FirebaseDatabase.getInstance(
//                    "https://absen-5ed4d-default-rtdb.asia-southeast1.firebasedatabase.app/"
//            ).getReference();
//
//        }
//        return mDatabase;
//    }
//
//    // Reference untuk siswa
//    public static DatabaseReference getSiswaRef() {
//        return getDatabase().child("siswa");
//    }
//    // Tambah siswa dengan callback sukses/gagal
//    public static void tambahSiswa(Siswa siswa, OnSiswaAddedListener listener) {
//        String id = getSiswaRef().push().getKey();
//        siswa.setId(id);
//
//        getSiswaRef().child(id).setValue(siswa)
//                .addOnSuccessListener(aVoid -> listener.onComplete(true))
//                .addOnFailureListener(e -> listener.onComplete(false));
//    }
//
//    // Interface callback
//    public interface OnSiswaAddedListener {
//        void onComplete(boolean success);
//    }
//    // Reference untuk absen
//    public static DatabaseReference getAbsenRef() {
//        return getDatabase().child("absensi");
//    }
/// //////////fix 03
    private static DatabaseReference mDatabase;

    public static DatabaseReference getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance(
                    "https://absen-5ed4d-default-rtdb.asia-southeast1.firebasedatabase.app/"
            ).getReference();
        }
        return mDatabase;
    }

    public static DatabaseReference getSiswaRef() {
        return getDatabase().child("siswa");
    }

    public static void tambahSiswa(Siswa siswa, OnSiswaAddedListener listener) {
        String id = getSiswaRef().push().getKey();
        siswa.setId(id);

        getSiswaRef().child(id).setValue(siswa)
                .addOnSuccessListener(aVoid -> listener.onComplete(true))
                .addOnFailureListener(e -> listener.onComplete(false));
    }

    public interface OnSiswaAddedListener {
        void onComplete(boolean success);
    }
}
