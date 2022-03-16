package com.example.systemadminproject.activity.brand;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.systemadminproject.R;
import com.example.systemadminproject.model.brand.BrandData;
import com.google.gson.Gson;

import java.util.List;

public class BrandAdapter extends BaseAdapter implements ListAdapter {
    private List<BrandData> _brandList;

    public void setBrandList(List<BrandData> _brandList) {
        this._brandList = _brandList;
    }

    @Override
    public int getCount(){
        return _brandList.size();
    }

    @Override
    public Object getItem(int i){
        return _brandList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _brandList.get(i).getId();
    }

    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.brand_grid_item, viewGroup, false);
        }
        TextView brandName = view.findViewById(R.id.txtBrandName);
        TextView storeNumber = view.findViewById(R.id.txtStoreNumber);
        TextView status = view.findViewById(R.id.txtStatus);
        Button btnClickDetail = view.findViewById(R.id.btnDetail);
        BrandData _brand = _brandList.get(i);
        brandName.setText(_brand.getName());
        storeNumber.setText(String.valueOf(_brand.getStoreList().size()));
        status.setText(_brand.getStatus() == 0 ? "Hoạt động" : "Không hoạt động");

        btnClickDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewBrandDetailsActivity.class);
                intent.putExtra("brand", (new Gson().toJson(_brand)));
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }
}
