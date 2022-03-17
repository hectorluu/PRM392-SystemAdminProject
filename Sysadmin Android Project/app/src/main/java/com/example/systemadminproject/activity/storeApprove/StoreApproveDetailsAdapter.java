package com.example.systemadminproject.activity.storeApprove;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.store.ViewStoreActivity;
import com.example.systemadminproject.model.brand.UserListResponse;
import com.example.systemadminproject.model.store.StoreData;
import com.example.systemadminproject.service.StoreService;

import java.util.List;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreApproveDetailsAdapter extends BaseAdapter implements ListAdapter {
    private StoreData _storeApproveDataList;

    public StoreApproveDetailsAdapter(StoreData _storeApproveDataList) {
        this._storeApproveDataList = _storeApproveDataList;
    }

    public void setStoreApproveDataList(StoreData _storeApproveDataList) {
        this._storeApproveDataList = _storeApproveDataList;
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
        return _storeApproveDataList.getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.store_approve_details_grid_item, viewGroup, false);
        }

        LinearLayout layout = view.findViewById(R.id.llstoreapproveitemdetails);

        TextView storeName = view.findViewById(R.id.txtStoreApproveNameItem);
        TextView address = view.findViewById(R.id.txtAddressApproveItem);
        TextView brandName = view.findViewById(R.id.txtBrandNameOfStoreApproveItem);

        StoreData _storeApprove = _storeApproveDataList;

        storeName.setText(_storeApprove.getName());
        address.setText(_storeApprove.getAddress());
        brandName.setText(_storeApprove.getBrandName());

        if (layout.findViewWithTag("uniqueUserApprove") == null) {

            TextView Store = new TextView(view.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Store.setTextSize(20);
            Store.setTypeface(null, Typeface. BOLD);
            Store.setText("Chủ Sở Hữu:");
            Store.setTag("uniqueUserApprove");
            Store.setTextColor(Color.rgb(0, 0, 0));
            Store.setLayoutParams(lp);
            layout.addView(Store);

            List<UserListResponse> userList = _storeApprove.getBrand().getUserList();
            for (UserListResponse user : userList) {
                TextView UserTemp = new TextView(view.getContext());
                LinearLayout.LayoutParams lpTemp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lpTemp.setMarginStart(40);
                UserTemp.setTextSize(18);
                UserTemp.setText("\u2022 " + user.getName());
                UserTemp.setTextColor(Color.rgb(0, 0, 0));
                UserTemp.setLayoutParams(lpTemp);

                layout.addView(UserTemp);
            }

            Button btnApprove = new Button(view.getContext());
            LinearLayout.LayoutParams llbtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            btnApprove.setText("Chấp Nhận");
            llbtn.gravity = Gravity.CENTER_HORIZONTAL;
            llbtn.weight = 1.0f;
            llbtn.setMargins(20, 40, 20, 0);
            btnApprove.setBackgroundColor(Color.rgb(0, 128, 128));
            btnApprove.setPadding(30, 20, 30, 20);
            btnApprove.setTextColor(Color.WHITE);
            btnApprove.setLayoutParams(llbtn);

            btnApprove.setOnClickListener(new View.OnClickListener() {
                StoreApproveStatus status = new StoreApproveStatus(0);
                @Override
                public void onClick(View view) {
                    StoreService.getApi().approveStore(_storeApprove.getId(), status)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(btnApprove.getContext(), "Duyệt Thành Công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(btnApprove.getContext(), "Something wrong happens", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                                    Toast.makeText(btnApprove.getContext(), "Call API Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Intent intent = new Intent(view.getContext(), ViewStoreApproveActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                    layout.invalidate();
                }
            });

//            layout.addView(btnApprove);

            Button btnReject = new Button(view.getContext());
            btnReject.setText("TỪ CHỐI");
            btnReject.setBackgroundColor(Color.rgb(140, 22, 22));
            btnReject.setPadding(30, 20, 30, 20);
            btnReject.setTextColor(Color.WHITE);

            btnReject.setLayoutParams(llbtn);

            btnReject.setOnClickListener(new View.OnClickListener() {
                StoreApproveStatus statusReject = new StoreApproveStatus(2);
                @Override
                public void onClick(View view) {
                    StoreService.getApi().approveStore(_storeApprove.getId(), statusReject)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(btnReject.getContext(), "Duyệt Thành Công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(btnReject.getContext(), "Something wrong happens", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                                    Toast.makeText(btnReject.getContext(), "Call API Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Intent intent = new Intent(view.getContext(), ViewStoreApproveActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                    layout.invalidate();
                }
            });

//            layout.addView(btnReject);

            LinearLayout parent = new LinearLayout(view.getContext());

            LinearLayout.LayoutParams paramsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsLayout.setMargins(10, 50, 10, 0);
            parent.setLayoutParams(paramsLayout);
            parent.setOrientation(LinearLayout.HORIZONTAL);

            parent.addView(btnApprove);
            parent.addView(btnReject);

            layout.addView(parent);
        }


        return view;
    }
}
