package com.example.systemadminproject.activity.brand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.systemadminproject.R;
import com.example.systemadminproject.model.brand.BrandData;
import com.google.gson.Gson;

import java.util.List;

public class ViewBrandDetailsActivity extends AppCompatActivity {
    private GridView gvBrandDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_brand_details);
        gvBrandDetails = findViewById(R.id.gvBrandDetails);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        BrandData _brand = new Gson().fromJson((String) intent.getSerializableExtra("brand"), BrandData.class) ;

        BrandData brandDataList = _brand;
        gvBrandDetails.setAdapter(new BrandDetailsAdapter(brandDataList));
    }
}