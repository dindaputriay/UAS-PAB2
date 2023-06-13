package com.example.uaspab;

import retrofit2.Call;

public interface APIService {
    Call<ValueNoData> register(String dirumahaja, String username, String password);

    Call<ValueNoData> login(String dirumahaja, String username, String password);
}
