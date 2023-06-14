package com.example.uaspab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.uaspab.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String konfirmasiPassword = binding.etKonfirmasiPasword.getText().toString();

                boolean bolehRegister = true;
                if (TextUtils.isEmpty(username)){
                    bolehRegister = false;
                    binding.etUsername.setError("Username tidak boleh Kosong!");
                }
                if (TextUtils.isEmpty(password)){
                    bolehRegister = false;
                    binding.etPassword.setError("Password tidak boleh Kosong!");
                }
                if (TextUtils.isEmpty(konfirmasiPassword)){
                    bolehRegister = false;
                    binding.etKonfirmasiPasword.setError("Konfirmasi Password tidak boleh Kosong!");
                }
                if (!password.equals(konfirmasiPassword)){
                    bolehRegister = false;
                    binding.etPassword.setError("Konfirmasi Passsword Tidak Sama");
                }
                if (password.length()<6){
                    bolehRegister = false;
                    binding.etPassword.setError("Password minimal 6 Karakter");
                }
                if (bolehRegister){
                    register(username,password);
                }

            }

        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void register(String username, String password) {
        APIService apiRegister = Utility.getRetrofit().create(APIService.class);
        apiRegister.register(username, password).enqueue(new Callback<ValueData<Dinda>>() {
            @Override
            public void onResponse(Call<ValueData<Dinda>> call, Response<ValueData<Dinda>> response) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ValueData<Dinda>> call, Throwable t) {

            }
        });
    }
}