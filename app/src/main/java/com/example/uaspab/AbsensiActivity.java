package com.example.uaspab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.uaspab.databinding.ActivityAbsensiBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsensiActivity extends AppCompatActivity {
    ActivityAbsensiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsensiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.inputNama.getText().toString();
                String keterangan = binding.inputKeterangan.getText().toString();
                String lokasi = binding.inputLokasi.getText().toString();

                boolean bolehPost = true;
                if(TextUtils.isEmpty(nama)){
                    bolehPost = false;
                    binding.inputNama.setError("Username tdk boleh kosong");
                }
                if(TextUtils.isEmpty(keterangan)){
                    bolehPost = false;
                    binding.inputKeterangan.setError("Password tdk boleh kosong");
                }
                if(TextUtils.isEmpty(lokasi)){
                    bolehPost = false;
                    binding.inputLokasi.setError("Password tdk boleh kosong");
                }
                if (bolehPost){
                    postAbsen(nama,lokasi,keterangan);
                }
            }
        });
    }

    private void postAbsen(String nama, String lokasi, String keterangan) {
        binding.progressBar.setVisibility(View.VISIBLE);
        String user_id = Utility.getValue(AbsensiActivity.this, "xUserId");
        APIService apiPost = Utility.getRetrofit().create(APIService.class);
        apiPost.addPost(user_id, nama, lokasi, keterangan).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if(response.code() == 200) {
                    startActivity(new Intent(AbsensiActivity.this, HistoryActivity.class));
                    finish();
                }
            }
            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(AbsensiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}