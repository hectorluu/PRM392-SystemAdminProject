package com.example.systemadminproject.activity.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.brand.BrandDetailsAdapter;
import com.example.systemadminproject.model.brand.BrandData;
import com.example.systemadminproject.model.store.StoreData;
import com.google.gson.Gson;

public class ViewStoreDetailsActivity extends AppCompatActivity {
    private GridView gvStoreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_details);
        gvStoreDetails=findViewById(R.id.gvStoreDetails);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        StoreData _store = new Gson().fromJson((String) intent.getSerializableExtra("store"), StoreData.class) ;

        StoreData storeDataList = _store;
        gvStoreDetails.setAdapter(new StoreDetailsAdapter(storeDataList));
    }
}