package com.example.systemadminproject.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.systemadminproject.R;
import com.example.systemadminproject.activity.main.MainActivity;
import com.example.systemadminproject.model.login.LoginRequest;
import com.example.systemadminproject.model.login.LoginResponse;
import com.example.systemadminproject.service.LoginService;
import com.example.systemadminproject.util.Constants;
import com.example.systemadminproject.util.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void clickToLogin(View view) {
        String txtUsername = username.getText().toString().trim();
        String txtPassword = password.getText().toString().trim();

            LoginService.getApi().login(new LoginRequest(txtUsername,txtPassword))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()){
                                if (!response.body().getToken().isEmpty()) {
                                    isLogin = true;
                                    Toast.makeText(LoginActivity.this, "Login Successfully !", Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.GROCERY_CLOUD_SHARED_PREFERENCE, Context.MODE_PRIVATE);
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect Username or Password !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            if (isLogin) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
    }
}