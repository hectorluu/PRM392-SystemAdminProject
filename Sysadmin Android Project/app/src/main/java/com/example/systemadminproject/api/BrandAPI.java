package com.example.systemadminproject.api;

import com.example.systemadminproject.model.brand.BrandResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BrandAPI {
    @GET("api/v1.0/brands")
    Call<BrandResponse> getBrandsList(@Query("page-index") int pageIndex,
                                      @Query("page-size") int pageSize);

    @DELETE("api/v1.0/brands/{id}")
    Call<Void> deleteBrand(@Path("id") int brandId);
}
