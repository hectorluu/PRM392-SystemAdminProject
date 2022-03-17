package com.example.systemadminproject.activity.storeApprove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.store.StoreDetailsAdapter;
import com.example.systemadminproject.model.store.StoreData;
import com.google.gson.Gson;

public class ViewStoreApproveDetailsActivity extends AppCompatActivity {
    private GridView gvStoreApproveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_approve_details);
        gvStoreApproveDetails = findViewById(R.id.gvStoreApproveDetails);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        StoreData _storeApprove = new Gson().fromJson((String) intent.getSerializableExtra("storeapprove"), StoreData.class) ;

        StoreData storeApproveDataList = _storeApprove;
        gvStoreApproveDetails.setAdapter(new StoreApproveDetailsAdapter(storeApproveDataList));
    }
}