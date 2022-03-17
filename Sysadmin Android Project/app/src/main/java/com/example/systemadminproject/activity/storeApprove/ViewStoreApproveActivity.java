package com.example.systemadminproject.activity.storeApprove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.store.ViewStoreActivity;
import com.example.systemadminproject.model.store.StoreData;
import com.example.systemadminproject.model.store.StoreResponse;
import com.example.systemadminproject.service.StoreService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStoreApproveActivity extends AppCompatActivity {
    private GridView gvStoreApprove;
    StoreApproveAdapter storeApproveAdapter = new StoreApproveAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_approve);

        gvStoreApprove = findViewById(R.id.gvStoreApprove);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed (() -> {
            callAPI();
        }, 1000);
    }

    public void callAPI() {
        int approveStatus = 1;
        StoreService.getApi().getStoresListPending(approveStatus, 1, 999)
                .enqueue(new Callback<StoreResponse>() {
                    @Override
                    public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                        List<StoreData> storeList = response.body().getData();
                        storeApproveAdapter.setStoreList(storeList);
                        gvStoreApprove.setAdapter(storeApproveAdapter);
                    }

                    @Override
                    public void onFailure(Call<StoreResponse> call, Throwable t) {
                        Toast.makeText(ViewStoreApproveActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}