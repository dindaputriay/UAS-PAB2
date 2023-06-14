package com.example.uaspab;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uaspab.databinding.ActivityHistoryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    ActivityHistoryBinding binding;
    List<Post> posts = new ArrayList<>();
    PostViewAdapter postViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this, MainActivity.class));
                finish();
            }
        });

        getPostData();
    }

    private void getPostData() {
        APIService apiGetData = Utility.getRetrofit().create(APIService.class);
        apiGetData.getPost().enqueue(new Callback<ValueData<List<Post>>>() {
            @Override
            public void onResponse(Call<ValueData<List<Post>>> call, Response<ValueData<List<Post>>> response) {
                if (response.code() == 200){
                    posts = response.body().getData();
                    loadPostData(posts);
                }
            }

            @Override
            public void onFailure(Call<ValueData<List<Post>>> call, Throwable t) {

            }
        });

    }

    private void loadPostData(List<Post> posts) {
        postViewAdapter = new PostViewAdapter(HistoryActivity.this);
        binding.rvPost.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        binding.rvPost.setAdapter(postViewAdapter);
        postViewAdapter.setPosts(posts);
    }
}