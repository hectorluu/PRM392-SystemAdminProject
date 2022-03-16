package com.example.systemadminproject.api;

import com.example.systemadminproject.model.login.LoginRequest;
import com.example.systemadminproject.model.login.LoginResponse;
import com.example.systemadminproject.model.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPI {
    @GET("api/v1.0/users")
    Call<UserResponse> getUsersList();
}
