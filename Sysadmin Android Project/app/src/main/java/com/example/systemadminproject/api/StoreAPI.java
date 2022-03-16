package com.example.systemadminproject.api;

import com.example.systemadminproject.model.login.LoginRequest;
import com.example.systemadminproject.model.login.LoginResponse;
import com.example.systemadminproject.model.store.StoreResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StoreAPI {
    @GET("api/v1.0/stores")
    Call<StoreResponse> getStoresList();

    @GET("api/v1.0/stores")
    Call<StoreResponse> getStoresListPending(@Query("approve-status") int approveStatus);
}
