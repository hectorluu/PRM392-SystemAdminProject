package com.example.systemadminproject.api;

import com.example.systemadminproject.model.login.LoginRequest;
import com.example.systemadminproject.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {
    @POST("api/v1.0/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
