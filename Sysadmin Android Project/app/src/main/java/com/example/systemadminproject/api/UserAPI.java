package com.example.systemadminproject.api;

import com.example.systemadminproject.model.login.LoginRequest;
import com.example.systemadminproject.model.login.LoginResponse;
import com.example.systemadminproject.model.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAPI {
    @GET("api/v1.0/users")
    Call<UserResponse> getUsersList(@Query("search-term") String searchTerm,
                                    @Query("status") int status,
                                    @Query("page-index") int pageIndex,
                                    @Query("page-size") int pageSize);

    @DELETE("api/v1.0/users/{id}")
    Call<Void> deleteUser(@Path("id") int userId);
}
