package com.example.systemadminproject.api;

import com.example.systemadminproject.model.brand.BrandResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandAPI {
    @GET("api/v1.0/brands")
    Call<BrandResponse> getBrandsList();
}
