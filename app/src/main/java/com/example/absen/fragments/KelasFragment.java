package com.example.absen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.absen.R;

public class KelasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kelas, container, false);

        root.findViewById(R.id.btnKelasX).setOnClickListener(v -> openDetail("X"));
        root.findViewById(R.id.btnKelasXI).setOnClickListener(v -> openDetail("XI"));
        root.findViewById(R.id.btnKelasXII).setOnClickListener(v -> openDetail("XII"));

        return root;
    }

    private void openDetail(String kelas) {
        DetailSiswaFragment fragment = new DetailSiswaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("kelas", kelas);
        fragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
