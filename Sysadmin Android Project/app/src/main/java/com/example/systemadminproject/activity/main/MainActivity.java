package com.example.systemadminproject.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.brand.ViewBrandActivity;
import com.example.systemadminproject.activity.login.LoginActivity;
import com.example.systemadminproject.activity.store.ViewStoreActivity;
import com.example.systemadminproject.activity.storeApprove.ViewStoreApproveActivity;
import com.example.systemadminproject.activity.user.ViewUserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToViewUsers(View view) {
        Intent intent = new Intent(this, ViewUserActivity.class);
        startActivity(intent);
    }

    public void clickToViewBrands(View view) {
        Intent intent = new Intent(this, ViewBrandActivity.class);
        startActivity(intent);
    }

    public void clickToViewStores(View view) {
        Intent intent = new Intent(this, ViewStoreActivity.class);
        startActivity(intent);
    }

    public void clickToStoresApprove(View view) {
        Intent intent = new Intent(this, ViewStoreApproveActivity.class);
        startActivity(intent);
    }

}