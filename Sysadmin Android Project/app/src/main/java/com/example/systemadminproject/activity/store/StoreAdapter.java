package com.example.systemadminproject.activity.store;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.brand.ViewBrandDetailsActivity;
import com.example.systemadminproject.model.brand.BrandData;
import com.example.systemadminproject.model.store.StoreData;
import com.google.gson.Gson;

import java.util.List;

public class StoreAdapter extends BaseAdapter implements ListAdapter {
    private List<StoreData> _storeList;

    public void setStoreList(List<StoreData> _storeList) {
        this._storeList = _storeList;
    }

    @Override
    public int getCount() {
        return _storeList.size();
    }

    @Override
    public Object getItem(int i) {
        return _storeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _storeList.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.store_grid_item, viewGroup, false);
        }
        TextView storeName = view.findViewById(R.id.txtStoreName);
        TextView brandName = view.findViewById(R.id.txtBrandNameOfStore);
        TextView address = view.findViewById(R.id.txtAddress);
        Button btnClickDetail = view.findViewById(R.id.btnDetailOfStore);
        StoreData _store = _storeList.get(i);
        storeName.setText(_store.getName());
        brandName.setText(_store.getBrandName());
        address.setText(_store.getAddress());

        btnClickDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewStoreDetailsActivity.class);
                intent.putExtra("store", (new Gson().toJson(_store)));
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }
}
