package com.zc.takeout.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zc.takeout.R;
import com.zc.takeout.service.CustomService;
import com.zc.takeout.service.impl.CustomServiceImpl;

public class LoginActivity extends AppCompatActivity {

    private CustomService customService = new CustomServiceImpl(this);
    private TextView tvSig;
    private EditText etAccount, etPassword;
    private Button btnLogin;
    private String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        tvSig = findViewById(R.id.tv_sig);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etAccount.getText().toString();
                password = etPassword.getText().toString();
                if (customService.login(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "恭喜你登陆成功",  Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误！",  Toast.LENGTH_LONG).show();
                }
            }
        });
        tvSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            etAccount.setText(data.getStringExtra("username"));
            etPassword.setText(data.getStringExtra("password"));
        }
    }
}