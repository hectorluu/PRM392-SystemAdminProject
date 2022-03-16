package com.example.systemadminproject.activity.store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.brand.ViewBrandActivity;
import com.example.systemadminproject.model.brand.BrandData;
import com.example.systemadminproject.model.brand.BrandResponse;
import com.example.systemadminproject.model.store.StoreData;
import com.example.systemadminproject.model.store.StoreResponse;
import com.example.systemadminproject.service.BrandService;
import com.example.systemadminproject.service.StoreService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStoreActivity extends AppCompatActivity {
    private GridView gvStore;
    StoreAdapter storeAdapter = new StoreAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store);

        gvStore = findViewById(R.id.gvStore);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed (() -> {
            callAPI();
        }, 1000);
    }

    public void callAPI() {
        StoreService.getApi().getStoresList()
                .enqueue(new Callback<StoreResponse>() {
                    @Override
                    public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                        Toast.makeText(ViewStoreActivity.this, "Call API successfully", Toast.LENGTH_SHORT).show();
                        List<StoreData> storeList = response.body().getData();
                        storeAdapter.setStoreList(storeList);
                        gvStore.setAdapter(storeAdapter);
                    }

                    @Override
                    public void onFailure(Call<StoreResponse> call, Throwable t) {
                        Toast.makeText(ViewStoreActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}