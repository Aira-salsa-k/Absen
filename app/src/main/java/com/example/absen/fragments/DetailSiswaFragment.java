//package com.example.absen.fragments;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.absen.R;
//import com.example.absen.adapters.SiswaAdapter;
//import com.example.absen.models.Siswa;
//import com.example.absen.utils.FirebaseHelper;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//import android.app.AlertDialog;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//public class DataSiswaFragment extends Fragment {
//
//    private EditText edtNama, edtKelas;
//    private Button btnTambah;
//    private RecyclerView recyclerView;
//    private ArrayList<Siswa> siswaList;
//    private SiswaAdapter adapter;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_data_siswa, container, false);
//
//        edtNama = root.findViewById(R.id.edtNama);
//        edtKelas = root.findViewById(R.id.edtKelas);
//        btnTambah = root.findViewById(R.id.btnTambah);
//        recyclerView = root.findViewById(R.id.recyclerSiswa);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        siswaList = new ArrayList<>();
//        adapter = new SiswaAdapter(getContext(), siswaList);
//        recyclerView.setAdapter(adapter);
//
//        loadData();
//
//        btnTambah.setOnClickListener(v -> showTambahSiswaDialog());
//
//        return root;
//    }
//
//    private void showTambahSiswaDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_tambah_siswa, null);
//        builder.setView(dialogView);
//
//        EditText edtNama = dialogView.findViewById(R.id.edtNama);
//        EditText edtKelas = dialogView.findViewById(R.id.edtKelas);
//        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        btnSimpan.setOnClickListener(v -> {
//            String nama = edtNama.getText().toString();
//            String kelas = edtKelas.getText().toString();
//
//
//            if (!nama.isEmpty() && !kelas.isEmpty()) {
//                Siswa siswa = new Siswa(nama, kelas); // jangan pakai status
//                FirebaseHelper.tambahSiswa(siswa, success -> {
//                    if (success) Toast.makeText(getContext(), "Siswa ditambahkan", Toast.LENGTH_SHORT).show();
//                    else Toast.makeText(getContext(), "Gagal menambahkan", Toast.LENGTH_SHORT).show();
//                });
//                dialog.dismiss();
//            } else {
//                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void tambahSiswa() {
//        String nama = edtNama.getText().toString();
//        String kelas = edtKelas.getText().toString();
//
//        if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(kelas)) {
//            Toast.makeText(getContext(), "Isi semua data", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String id = FirebaseHelper.getSiswaRef().push().getKey();
//        Siswa siswa = new Siswa(id, nama, kelas);
//
//        FirebaseHelper.getSiswaRef().child(id).setValue(siswa);
//        edtNama.setText("");
//        edtKelas.setText("");
//    }
//
//    private void loadData() {
//        FirebaseHelper.getSiswaRef().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                siswaList.clear();
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    Siswa s = data.getValue(Siswa.class);
//                    siswaList.add(s);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Gagal load data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}
///////////////////////////////
//package com.example.absen.fragments;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.absen.R;
//import com.example.absen.adapters.SiswaAdapter;
//import com.example.absen.models.Siswa;
//import com.example.absen.utils.FirebaseHelper;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import java.util.ArrayList;
//
//import android.app.AlertDialog;
//
//
//public class DataSiswaFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    private ArrayList<Siswa> siswaList;
//    private SiswaAdapter adapter;
//    private Button btnTambah;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_data_siswa, container, false);
//
//        btnTambah = root.findViewById(R.id.btnTambah);
//        recyclerView = root.findViewById(R.id.recyclerSiswa);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        siswaList = new ArrayList<>();
////        adapter = new SiswaAdapter(getContext(), siswaList);
//        // Kirim callback ke adapter
//        adapter = new SiswaAdapter(getContext(), siswaList, siswa -> showEditSiswaDialog(siswa));
//        recyclerView.setAdapter(adapter);
//
//        loadData();
//
//        btnTambah.setOnClickListener(v -> showTambahSiswaDialog());
//
//        return root;
//    }
//
//
//    private void showTambahSiswaDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
//        builder.setView(dialogView);
//
//        EditText edtNama = dialogView.findViewById(R.id.edtNama);
//        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas); // inisialisasi dulu
//        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);
//
//        // Set adapter untuk spinner setelah spinner diinisialisasi
//        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
//                getContext(),
//                R.array.kelas_array,
//                android.R.layout.simple_spinner_item
//        );
//        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerKelas.setAdapter(adapterKelas);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        btnSimpan.setOnClickListener(v -> {
//            String nama = edtNama.getText().toString();
//            String kelas = spinnerKelas.getSelectedItem().toString();
//
//            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(kelas)) {
//                Siswa siswa = new Siswa();
//                siswa.setNama(nama);
//                siswa.setKelas(kelas);
//
//                FirebaseHelper.tambahSiswa(siswa, success -> {
//                    if (success) Toast.makeText(getContext(), "Siswa ditambahkan", Toast.LENGTH_SHORT).show();
//                    else Toast.makeText(getContext(), "Gagal menambahkan", Toast.LENGTH_SHORT).show();
//                });
//
//                dialog.dismiss();
//            } else {
//                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void showEditSiswaDialog(Siswa siswa) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
//        builder.setView(dialogView);
//
//        EditText edtNama = dialogView.findViewById(R.id.edtNama);
//        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas);
//        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);
//
//        // Set adapter spinner
//        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
//                getContext(),
//                R.array.kelas_array,
//                android.R.layout.simple_spinner_item
//        );
//        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerKelas.setAdapter(adapterKelas);
//
//        // Pre-fill data
//        edtNama.setText(siswa.getNama());
//        int spinnerPosition = adapterKelas.getPosition(siswa.getKelas());
//        spinnerKelas.setSelection(spinnerPosition);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        btnSimpan.setOnClickListener(v -> {
//            String nama = edtNama.getText().toString();
//            String kelas = spinnerKelas.getSelectedItem().toString();
//
//            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(kelas)) {
//                siswa.setNama(nama);
//                siswa.setKelas(kelas);
//
//                // Update data di Firebase
//                FirebaseHelper.getSiswaRef().child(siswa.getId())
//                        .setValue(siswa)
//                        .addOnSuccessListener(aVoid -> {
//                            Toast.makeText(getContext(), "Data siswa diperbarui", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                        })
//                        .addOnFailureListener(e ->
//                                Toast.makeText(getContext(), "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
//                        );
//            } else {
//                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//    private void loadData() {
//        FirebaseHelper.getSiswaRef().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                siswaList.clear();
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    Siswa s = data.getValue(Siswa.class);
//                    siswaList.add(s);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Gagal load data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
/// //////fix03
package com.example.absen.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absen.R;
import com.example.absen.adapters.SiswaAdapter;
import com.example.absen.models.Siswa;
import com.example.absen.utils.FirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.app.AlertDialog;


public class DetailSiswaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Siswa> siswaList = new ArrayList<>();
    private SiswaAdapter adapter;
    private Button btnTambah;
    private String kelas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_siswa, container, false);

        recyclerView = root.findViewById(R.id.recyclerSiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SiswaAdapter(getContext(), siswaList, this);
        recyclerView.setAdapter(adapter);

        btnTambah = root.findViewById(R.id.btnTambah);

        if (getArguments() != null) {
            kelas = getArguments().getString("kelas");
        }

        btnTambah.setOnClickListener(v -> showTambahSiswaDialog());

        loadData();

        return root;
    }

    private void loadData() {
        FirebaseHelper.getSiswaRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                siswaList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Siswa s = data.getValue(Siswa.class);
                    if (s != null && s.getKelas().equals(kelas)) {
                        siswaList.add(s);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public void showEditSiswaDialog(Siswa siswa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
        builder.setView(dialogView);

        EditText edtNama = dialogView.findViewById(R.id.edtNama);
        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas);
        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);

        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
                getContext(),
                R.array.kelas_array,
                android.R.layout.simple_spinner_item);
        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKelas.setAdapter(adapterKelas);

        edtNama.setText(siswa.getNama());
        spinnerKelas.setSelection(adapterKelas.getPosition(siswa.getKelas()));

        AlertDialog dialog = builder.create();
        dialog.show();

        btnSimpan.setOnClickListener(v -> {
            String nama = edtNama.getText().toString();
            String kelasSelected = spinnerKelas.getSelectedItem().toString();

            if (!TextUtils.isEmpty(nama)) {
                siswa.setNama(nama);
                siswa.setKelas(kelasSelected);

                FirebaseHelper.getSiswaRef().child(siswa.getId())
                        .setValue(siswa)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(getContext(), "Data siswa diperbarui", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        });
            }
        });
    }

    private void showTambahSiswaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
        builder.setView(dialogView);

        EditText edtNama = dialogView.findViewById(R.id.edtNama);
        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas);
        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);

        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
                getContext(),
                R.array.kelas_array,
                android.R.layout.simple_spinner_item);
        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKelas.setAdapter(adapterKelas);

        // set default ke kelas saat ini
        spinnerKelas.setSelection(adapterKelas.getPosition(kelas));

        AlertDialog dialog = builder.create();
        dialog.show();

        btnSimpan.setOnClickListener(v -> {
            String nama = edtNama.getText().toString();
            String kelasSelected = spinnerKelas.getSelectedItem().toString();

            if (!TextUtils.isEmpty(nama)) {
                Siswa siswa = new Siswa();
                siswa.setNama(nama);
                siswa.setKelas(kelasSelected);

                FirebaseHelper.tambahSiswa(siswa, success -> {
                    if (success) Toast.makeText(getContext(), "Siswa ditambahkan", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getContext(), "Gagal menambahkan", Toast.LENGTH_SHORT).show();
                });
                dialog.dismiss();
            }
        });
    }
}

/// fix04
//public class SiswaPerKelasFragment extends Fragment {
//
//    private RecyclerView recyclerX, recyclerXI, recyclerXII;
//    private ArrayList<Siswa> listX, listXI, listXII;
//    private SiswaAdapter adapterX, adapterXI, adapterXII;
//
//    private Button btnTambahX, btnTambahXI, btnTambahXII;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.fragment_data_siswa, container, false);
//
//        // init RecyclerView
//        recyclerX = root.findViewById(R.id.recyclerX);
//        recyclerXI = root.findViewById(R.id.recyclerXI);
//        recyclerXII = root.findViewById(R.id.recyclerXII);
//
//        recyclerX.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerXI.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerXII.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        // init list dan adapter
//        listX = new ArrayList<>();
//        listXI = new ArrayList<>();
//        listXII = new ArrayList<>();
//
//        adapterX = new SiswaAdapter(getContext(), listX, this);
//        adapterXI = new SiswaAdapter(getContext(), listXI, this);
//        adapterXII = new SiswaAdapter(getContext(), listXII, this);
//
//        recyclerX.setAdapter(adapterX);
//        recyclerXI.setAdapter(adapterXI);
//        recyclerXII.setAdapter(adapterXII);
//
//        // tombol tambah
//        btnTambahX = root.findViewById(R.id.btnTambahX);
//        btnTambahXI = root.findViewById(R.id.btnTambahXI);
//        btnTambahXII = root.findViewById(R.id.btnTambahXII);
//
//        btnTambahX.setOnClickListener(v -> showTambahSiswaDialog("X"));
//        btnTambahXI.setOnClickListener(v -> showTambahSiswaDialog("XI"));
//        btnTambahXII.setOnClickListener(v -> showTambahSiswaDialog("XII"));
//
//        loadData();
//
//        return root;
//    }
//
//    private void showTambahSiswaDialog(String defaultKelas) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
//        builder.setView(dialogView);
//
//        EditText edtNama = dialogView.findViewById(R.id.edtNama);
//        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas);
//        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);
//
//        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
//                getContext(),
//                R.array.kelas_array,
//                android.R.layout.simple_spinner_item
//        );
//        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerKelas.setAdapter(adapterKelas);
//
//        // set default kelas
//        spinnerKelas.setSelection(adapterKelas.getPosition(defaultKelas));
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        btnSimpan.setOnClickListener(v -> {
//            String nama = edtNama.getText().toString();
//            String kelas = spinnerKelas.getSelectedItem().toString();
//
//            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(kelas)) {
//                Siswa siswa = new Siswa();
//                siswa.setNama(nama);
//                siswa.setKelas(kelas);
//
//                FirebaseHelper.tambahSiswa(siswa, success -> {
//                    if (success) Toast.makeText(getContext(), "Siswa ditambahkan", Toast.LENGTH_SHORT).show();
//                    else Toast.makeText(getContext(), "Gagal menambahkan", Toast.LENGTH_SHORT).show();
//                });
//
//                dialog.dismiss();
//            } else {
//                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public void showEditSiswaDialog(Siswa siswa) {
//        // kode edit sama seperti sebelumnya
//    }
//
//    private void loadData() {
//        FirebaseHelper.getSiswaRef().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                listX.clear();
//                listXI.clear();
//                listXII.clear();
//
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    Siswa s = data.getValue(Siswa.class);
//                    if (s.getKelas().equals("X")) listX.add(s);
//                    else if (s.getKelas().equals("XI")) listXI.add(s);
//                    else if (s.getKelas().equals("XII")) listXII.add(s);
//                }
//
//                adapterX.notifyDataSetChanged();
//                adapterXI.notifyDataSetChanged();
//                adapterXII.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Gagal load data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}

/////batas fix 04
//public class DataSiswaFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    private ArrayList<Siswa> siswaList;
//    private SiswaAdapter adapter;
//    private Button btnTambah;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_data_siswa, container, false);
//
//        btnTambah = root.findViewById(R.id.btnTambah);
//        recyclerView = root.findViewById(R.id.recyclerSiswa);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        siswaList = new ArrayList<>();
//        adapter = new SiswaAdapter(getContext(), siswaList, this); // kirim fragment ke adapter
//        recyclerView.setAdapter(adapter);
//
//        loadData();
//
//        btnTambah.setOnClickListener(v -> showTambahSiswaDialog());
//
//        return root;
//    }
//
//    private void showTambahSiswaDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
//        builder.setView(dialogView);
//
//        EditText edtNama = dialogView.findViewById(R.id.edtNama);
//        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas);
//        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);
//
//        // set spinner
//        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
//                getContext(),
//                R.array.kelas_array,
//                android.R.layout.simple_spinner_item
//        );
//        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerKelas.setAdapter(adapterKelas);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        btnSimpan.setOnClickListener(v -> {
//            String nama = edtNama.getText().toString();
//            String kelas = spinnerKelas.getSelectedItem().toString();
//
//            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(kelas)) {
//                Siswa siswa = new Siswa();
//                siswa.setNama(nama);
//                siswa.setKelas(kelas);
//
//                FirebaseHelper.tambahSiswa(siswa, success -> {
//                    if (success) Toast.makeText(getContext(), "Siswa ditambahkan", Toast.LENGTH_SHORT).show();
//                    else Toast.makeText(getContext(), "Gagal menambahkan", Toast.LENGTH_SHORT).show();
//                });
//
//                dialog.dismiss();
//            } else {
//                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public void showEditSiswaDialog(Siswa siswa) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_tambah_siswa, null);
//        builder.setView(dialogView);
//
//        EditText edtNama = dialogView.findViewById(R.id.edtNama);
//        Spinner spinnerKelas = dialogView.findViewById(R.id.spinnerKelas);
//        Button btnSimpan = dialogView.findViewById(R.id.btnSimpan);
//
//        ArrayAdapter<CharSequence> adapterKelas = ArrayAdapter.createFromResource(
//                getContext(),
//                R.array.kelas_array,
//                android.R.layout.simple_spinner_item
//        );
//        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerKelas.setAdapter(adapterKelas);
//
//        // isi data lama
//        edtNama.setText(siswa.getNama());
//        int spinnerPosition = adapterKelas.getPosition(siswa.getKelas());
//        spinnerKelas.setSelection(spinnerPosition);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        btnSimpan.setOnClickListener(v -> {
//            String nama = edtNama.getText().toString();
//            String kelas = spinnerKelas.getSelectedItem().toString();
//
//            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(kelas)) {
//                siswa.setNama(nama);
//                siswa.setKelas(kelas);
//
//                FirebaseHelper.getSiswaRef().child(siswa.getId())
//                        .setValue(siswa)
//                        .addOnSuccessListener(aVoid -> {
//                            Toast.makeText(getContext(), "Data siswa diperbarui", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                        })
//                        .addOnFailureListener(e ->
//                                Toast.makeText(getContext(), "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
//                        );
//            } else {
//                Toast.makeText(getContext(), "Isi semua field!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void loadData() {
//        FirebaseHelper.getSiswaRef().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                siswaList.clear();
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    Siswa s = data.getValue(Siswa.class);
//                    siswaList.add(s);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Gagal load data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}

