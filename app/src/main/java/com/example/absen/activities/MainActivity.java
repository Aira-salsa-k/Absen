package com.example.absen.activities;

//import static com.example.absen.R.id.navigation_siswa;

import android.os.Bundle;

import com.example.absen.R;
import com.example.absen.fragments.AbsenFragment;
import com.example.absen.fragments.DetailSiswaFragment;
import com.example.absen.fragments.RekapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.absen.fragments.KelasFragment;



public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Load default fragment pertama (misalnya AbsenFragment)
//        loadFragment(new AbsenFragment());
//
//
//        // Listener untuk bottom navigation
//        binding.navView.setOnItemSelectedListener(item -> {
//            Fragment fragment = null;
//
//            if (item.getItemId() == R.id.nav_data_siswa) {
//                fragment = new DataSiswaFragment();
//            } else if (item.getItemId() == R.id.nav_absen) {
//                fragment = new AbsenFragment();
//            } else if (item.getItemId() == R.id.nav_rekap) {
//                fragment = new RekapFragment();
//            }
//
//            if (fragment != null) {
//                loadFragment(fragment);
//            }
//
//            return true;
//        });
//
//    }
//    private void loadFragment(Fragment fragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.nav_host_fragment_activity_main, fragment)
//                .commit();
//    }
/// /04
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
//
//        // Load fragment default (misalnya DataSiswa)
//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container_fragment, new DetailSiswaFragment())
//                    .commit();
//        }
//
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//
//            if (item.getItemId() == R.id.navigation_siswa) {
//                selectedFragment = new DetailSiswaFragment();
//            } else if (item.getItemId() == R.id.navigation_absen) {
//                selectedFragment = new AbsenFragment();
//            } else if (item.getItemId() == R.id.navigation_rekap) {
//                selectedFragment = new RekapFragment();
//            }
//
//            if (selectedFragment != null) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container_fragment, selectedFragment)
//                        .commit();
//            }
//
//            return true;
//        });
//    }

/// /04

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

    // tampilkan fragment default
    if (savedInstanceState == null) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, new AbsenFragment())
                .commit();
    }

    // listener menu
    bottomNavigationView.setOnItemSelectedListener(item -> {
        Fragment selectedFragment = null;
        if (item.getItemId() == R.id.navigation_siswa) {
                selectedFragment = new KelasFragment();
            } else if (item.getItemId() == R.id.navigation_absen) {
                selectedFragment = new AbsenFragment();
            } else if (item.getItemId() == R.id.navigation_rekap) {
                selectedFragment = new RekapFragment();
            }

        if (selectedFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_fragment, selectedFragment)
                    .commit();
        }
        return true;
    });
}



}