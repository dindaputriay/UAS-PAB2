package com.example.uaspab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.uaspab.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etInputNama.getText().toString();
                String password = binding.etInputPassword.getText().toString();

                boolean bolehLogin = true;
                if(TextUtils.isEmpty(username)){
                    bolehLogin = false;
                    binding.etInputNama.setError("Username tdk boleh kosong");
                }
                if(TextUtils.isEmpty(password)){
                    bolehLogin = false;
                    binding.etInputPassword.setError("Password tdk boleh kosong");
                }
                if (bolehLogin){
                    login(username,password);
                }
            }


        });
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void login(String username, String password) {
        binding.progressBar.setVisibility(View.VISIBLE);
        //memanggil API LOGIN
        APIService apilogin = Utility.getRetrofit().create(APIService.class);
        apilogin.login(username, password).enqueue(new Callback<ValueData<Dinda>>() {
            @Override
            public void onResponse(Call<ValueData<Dinda>> call, Response<ValueData<Dinda>> response) {
                binding.progressBar.setVisibility(View.GONE);
                String id = response.body().getData().getId();
                Utility.setValue(LoginActivity.this, "xUserId", id);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ValueData<Dinda>> call, Throwable t) {

            }
        });
        
    }
}