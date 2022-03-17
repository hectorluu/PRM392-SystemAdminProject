package com.example.systemadminproject.activity.storeApprove;

import android.content.Intent;
import android.graphics.Color;
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
            Store.setTextSize(18);
            Store.setText("Chủ Sở Hữu:");
            Store.setTag("uniqueUserApprove");
            Store.setTextColor(Color.rgb(0, 0, 0));
            Store.setLayoutParams(lp);
            layout.addView(Store);

            List<UserListResponse> userList = _storeApprove.getBrand().getUserList();
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

//            Button btnDelete = new Button(view.getContext());
//            LinearLayout.LayoutParams llbtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            btnDelete.setText("Dừng Hoạt Động");
//            btnDelete.setGravity(Gravity.CENTER);
//            btnDelete.setLayoutParams(llbtn);
//
//            btnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    StoreService.getApi().deleteStore(_store.getId())
//                            .enqueue(new Callback<Void>() {
//                                @Override
//                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
//                                    if (response.isSuccessful()) {
//                                        Toast.makeText(btnDelete.getContext(), "Delete Store Successfully", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(btnDelete.getContext(), "Something wrong happens", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
//                                    Toast.makeText(btnDelete.getContext(), "Call API Failed", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                    Intent intent = new Intent(view.getContext(), ViewStoreActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    view.getContext().startActivity(intent);
//                    layout.invalidate();
//                }
//            });

//            layout.addView(btnDelete);
        }


        return view;
    }
}
