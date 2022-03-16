package com.example.systemadminproject.activity.brand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.model.brand.BrandData;
import com.example.systemadminproject.model.brand.BrandResponse;
import com.example.systemadminproject.service.BrandService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBrandActivity extends AppCompatActivity {
    private GridView gvBrand;
    BrandAdapter brandAdapter = new BrandAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_brand);

        gvBrand = findViewById(R.id.gvBrand);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed (() -> {
            callAPI();
        }, 1000);
    }

    public void callAPI() {
        BrandService.getApi().getBrandsList()
                .enqueue(new Callback<BrandResponse>() {
                    @Override
                    public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                        Toast.makeText(ViewBrandActivity.this, "Call API successfully", Toast.LENGTH_SHORT).show();
                        List<BrandData> brandList = response.body().getData();
                        brandAdapter.setBrandList(brandList);
                        gvBrand.setAdapter(brandAdapter);
                    }

                    @Override
                    public void onFailure(Call<BrandResponse> call, Throwable t) {
                        Toast.makeText(ViewBrandActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
