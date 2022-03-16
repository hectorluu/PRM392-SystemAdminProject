package com.example.systemadminproject.activity.brand;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.main.MainActivity;
import com.example.systemadminproject.model.brand.BrandData;
import com.example.systemadminproject.model.brand.BrandResponse;
import com.example.systemadminproject.model.brand.StoreListResponse;
import com.example.systemadminproject.model.brand.UserListResponse;
import com.example.systemadminproject.service.BrandService;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandDetailsAdapter extends BaseAdapter implements ListAdapter {
    private BrandData _brandDataList;

    public BrandDetailsAdapter(BrandData _brandDataList) {
        this._brandDataList = _brandDataList;
    }

    public void setBrandDataList(BrandData _brandDataList) {
        this._brandDataList = _brandDataList;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return _brandDataList.getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup){
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.brand_details_grid_item, viewGroup, false);
        }

        LinearLayout layout = view.findViewById(R.id.llbranditemdetails);

        TextView brandName = view.findViewById(R.id.txtBrandNameItem);
        TextView status = view.findViewById(R.id.txtStatusItem);

        BrandData _brand = _brandDataList;

        brandName.setText(_brand.getName());
        status.setText(_brand.getStatus() == 0 ? "Hoạt động" : "Không hoạt động");


        if (layout.findViewWithTag("uniqueStore") == null) {

            TextView Store = new TextView(view.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Store.setTextSize(18);
            Store.setText("Danh Sách Cửa Hàng:");
            Store.setTag("uniqueStore");
            Store.setTextColor(Color.rgb(0, 0, 0));
            Store.setLayoutParams(lp);
            layout.addView(Store);

            List<StoreListResponse> storeList = _brand.getStoreList();

            for (StoreListResponse store : storeList) {
                TextView StoreTemp = new TextView(view.getContext());
                LinearLayout.LayoutParams lpTemp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpTemp.setMarginStart(30);
                StoreTemp.setTextSize(18);
                StoreTemp.setText("+ " + store.getName());
                StoreTemp.setTextColor(Color.rgb(0, 0, 0));
                StoreTemp.setLayoutParams(lpTemp);

                layout.addView(StoreTemp);
            }

            TextView User = new TextView(view.getContext());
            User.setTextSize(18);
            User.setText("Danh Sách Người Dùng:");
            User.setTextColor(Color.rgb(0, 0, 0));
            User.setLayoutParams(lp);
            layout.addView(User);

            List<UserListResponse> userList = _brand.getUserList();
            for (UserListResponse user : userList) {
                TextView UserTemp = new TextView(view.getContext());
                LinearLayout.LayoutParams lpTemp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpTemp.setMarginStart(30);
                UserTemp.setTextSize(18);
                UserTemp.setText("+ " + user.getName());
                UserTemp.setTextColor(Color.rgb(0, 0, 0));
                UserTemp.setLayoutParams(lpTemp);

                layout.addView(UserTemp);
            }

            Button btnDelete = new Button(view.getContext());
            LinearLayout.LayoutParams llbtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            btnDelete.setText("Dừng Hoạt Động");
            btnDelete.setGravity(Gravity.CENTER);
            btnDelete.setLayoutParams(llbtn);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BrandService.getApi().deleteBrand(_brand.getId())
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(btnDelete.getContext(), "Delete Brand Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(btnDelete.getContext(), "Something wrong happens", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                                    Toast.makeText(btnDelete.getContext(), "Call API Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Intent intent = new Intent(view.getContext(), ViewBrandActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                    layout.invalidate();
                }
            });

            layout.addView(btnDelete);
        }

        return view;
    }
}
