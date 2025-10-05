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
                            Toast.makeText(getContext(), "Data Siswa diperbarui", Toast.LENGTH_SHORT).show();
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


