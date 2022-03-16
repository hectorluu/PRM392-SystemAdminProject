package com.example.systemadminproject.service;

import com.example.systemadminproject.api.UserAPI;
import com.example.systemadminproject.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {
    private static UserAPI api;

    public static synchronized UserAPI getApi() {
        if (api == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            api = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(UserAPI.class);
        }
        return api;
    }
}
