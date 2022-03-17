package com.example.systemadminproject.activity.user;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.brand.ViewBrandActivity;
import com.example.systemadminproject.activity.brand.ViewBrandDetailsActivity;
import com.example.systemadminproject.model.user.UserData;
import com.example.systemadminproject.service.BrandService;
import com.example.systemadminproject.service.UserService;
import com.google.gson.Gson;

import java.util.List;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends BaseAdapter implements ListAdapter {
    private List<UserData> _userList;

    public void setUserList(List<UserData> _userList) {
        this._userList = _userList;
    }

    @Override
    public int getCount() {
        return _userList.size();
    }

    @Override
    public Object getItem(int i) {
        return _userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _userList.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view =convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.user_grid_item, viewGroup, false);
        }
        TextView username = view.findViewById(R.id.txtUserName);
        TextView email = view.findViewById(R.id.txtEmail);
        TextView phonenum = view.findViewById(R.id.txtPhonenumber);
        Button btnClickDelete = view.findViewById(R.id.btnDeleteUser);
        UserData _user = _userList.get(i);
        username.setText(_user.getName());
        email.setText(_user.getEmail());
        phonenum.setText(_user.getPhone());

        btnClickDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserService.getApi().deleteUser(_user.getId())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                if (response.isSuccessful()) {
                                    ((ViewUserActivity)btnClickDelete.getContext()).callAPI();
                                    Toast.makeText(btnClickDelete.getContext(), "Xóa Người Dùng Thành Công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(btnClickDelete.getContext(), "Something wrong happens", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<Void> call,@NonNull Throwable t) {
                                Toast.makeText(btnClickDelete.getContext(), "Call API Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }
}
