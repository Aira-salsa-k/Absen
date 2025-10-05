//package com.example.absen.adapters;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.absen.R;
//import com.example.absen.fragments.Callback;
//import com.example.absen.fragments.DataSiswaFragment;
//import com.example.absen.models.Siswa;
//import com.example.absen.utils.FirebaseHelper;
//import java.util.ArrayList;
//
//public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
//
//        private Context context;
//        private ArrayList<Siswa> siswaList;
//    private boolean callback;
//
//    public SiswaAdapter(Context context, ArrayList<Siswa> siswaList) {
//            this.context = context;
//            this.siswaList = siswaList;
//            Callback callback;
//        }
//
//        @NonNull
//        @Override
//        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
//            return new ViewHolder(view);
//        }
//
//
//
//
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Siswa siswa = siswaList.get(position);
//        holder.txtNama.setText(siswa.getNama());
//        holder.txtKelas.setText(siswa.getKelas());
//
//        holder.btnDelete.setOnClickListener(v -> {
//            FirebaseHelper.getSiswaRef().child(siswa.getId()).removeValue();
//            Toast.makeText(context, "Siswa dihapus", Toast.LENGTH_SHORT).show();
//        });
//
//        // Edit button memanggil callback di fragment
////        holder.btnEdit.setOnClickListener(v -> {
////            if (context instanceof DataSiswaFragment.Callback) {
////                ((DataSiswaFragment.Callback) context).onEditSiswa(siswa);
////            }
////        });
//        holder.btnEdit.setOnClickListener(v -> {
//            if (callback != null) {
//                callback.onEditSiswa(siswa); // panggil fragment
//            }
//        });
//    }
//    @Override
//    public int getItemCount() {
//        return siswaList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtNama, txtKelas;
//        Button btnDelete, btnEdit;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtNama = itemView.findViewById(R.id.txtNama);
//            txtKelas = itemView.findViewById(R.id.txtKelas);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//        }
//    }
//}
//////////
package com.example.absen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absen.R;
import com.example.absen.fragments.DetailSiswaFragment;
import com.example.absen.models.Siswa;
import com.example.absen.utils.FirebaseHelper;

import java.util.ArrayList;
public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Siswa> siswaList;
    private DetailSiswaFragment fragment;

    public SiswaAdapter(Context context, ArrayList<Siswa> siswaList, DetailSiswaFragment fragment) {
        this.context = context;
        this.siswaList = siswaList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);
        holder.txtNama.setText(siswa.getNama());
        holder.txtKelas.setText(siswa.getKelas());

        holder.btnDelete.setOnClickListener(v -> {
            FirebaseHelper.getSiswaRef().child(siswa.getId()).removeValue();
            Toast.makeText(context, "Siswa dihapus", Toast.LENGTH_SHORT).show();
        });

        holder.btnEdit.setOnClickListener(v -> {
            if (fragment != null) {
                fragment.showEditSiswaDialog(siswa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama, txtKelas;
        ImageButton btnEdit, btnDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtKelas = itemView.findViewById(R.id.txtKelas);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

/// ///////04
//public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
//
//    private Context context;
//    private ArrayList<Siswa> siswaList;
//    private SiswaPerKelasFragment fragment;
//
//    public SiswaAdapter(Context context, ArrayList<Siswa> siswaList, SiswaPerKelasFragment fragment) {
//        this.context = context;
//        this.siswaList = siswaList;
//        this.fragment = fragment;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Siswa siswa = siswaList.get(position);
//
//        // tampilkan data siswa
//        holder.txtNama.setText(siswa.getNama());
//        holder.txtKelas.setText(siswa.getKelas());
//
//        // tombol hapus
//        holder.btnDelete.setOnClickListener(v -> {
//            FirebaseHelper.getSiswaRef().child(siswa.getId()).removeValue();
//            Toast.makeText(context, "Siswa dihapus", Toast.LENGTH_SHORT).show();
//        });
//
//        // tombol edit
//        holder.btnEdit.setOnClickListener(v -> fragment.showEditSiswaDialog(siswa));
//    }
//
//    @Override
//    public int getItemCount() {
//        return siswaList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtNama, txtKelas;
//        Button btnDelete, btnEdit;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtNama = itemView.findViewById(R.id.txtNama);
//            txtKelas = itemView.findViewById(R.id.txtKelas);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//        }
//    }
//}
/// /////////////batas 04
/// ////////////////////03
//public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
//
//    private Context context;
//    private ArrayList<Siswa> siswaList;
//    private DataSiswaFragment fragment;
//
//    public SiswaAdapter(Context context, ArrayList<Siswa> siswaList, DataSiswaFragment fragment) {
//        this.context = context;
//        this.siswaList = siswaList;
//        this.fragment = fragment;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Siswa siswa = siswaList.get(position);
//        holder.txtNama.setText(siswa.getNama());
//        holder.txtKelas.setText(siswa.getKelas());
//
//        holder.btnDelete.setOnClickListener(v -> {
//            FirebaseHelper.getSiswaRef().child(siswa.getId()).removeValue();
//            Toast.makeText(context, "Siswa dihapus", Toast.LENGTH_SHORT).show();
//        });
//
//        holder.btnEdit.setOnClickListener(v -> {
//            fragment.showEditSiswaDialog(siswa);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return siswaList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtNama, txtKelas;
//        Button btnDelete, btnEdit;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtNama = itemView.findViewById(R.id.txtNama);
//            txtKelas = itemView.findViewById(R.id.txtKelas);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//        }
//    }
//}
