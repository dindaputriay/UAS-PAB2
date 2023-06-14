package com.example.uaspab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;

import com.example.uaspab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!Utility.checkValue(this,"xUserId")){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        binding.imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.clearUser(MainActivity.this);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });  


    binding.btnAbsenMasuk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, AbsensiActivity.class));
        }
    });
    }

}