package com.example.systemadminproject.activity.user;

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
import com.example.systemadminproject.model.user.UserData;
import com.google.gson.Gson;

import java.util.List;

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
        Button btnClickDetail = view.findViewById(R.id.btnDetailOfUser);
        UserData _user = _userList.get(i);
        username.setText(_user.getName());
        email.setText(_user.getEmail());
        phonenum.setText(_user.getPhone());

        btnClickDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewUserDetailsActivity.class);
                intent.putExtra("brand", (new Gson().toJson(_user)));
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }
}
