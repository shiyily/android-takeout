package com.zc.takeout.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zc.takeout.R;
import com.zc.takeout.bean.Custom;
import com.zc.takeout.service.CustomService;
import com.zc.takeout.service.impl.CustomServiceImpl;

public class RegisterActivity extends AppCompatActivity {

    private CustomService customService = new CustomServiceImpl(this);
    private EditText etAccount, etTel, etPassword, etPasswordCon;
    private Button btnRegister;
    private String username, password, passwordCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account_register);
        etTel = findViewById(R.id.et_tel_register);
        etPassword = findViewById(R.id.et_password_register);
        etPasswordCon = findViewById(R.id.et_password_register_confirm);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().equals(etPasswordCon.getText().toString()) && !"".equals(etAccount.getText().toString()) && !"".equals(etTel.getText().toString())  && !"".equals(etPasswordCon.getText().toString())) {
                    Intent intent = new Intent();
                    username = etAccount.getText().toString();
                    password = etPassword.getText().toString();
                    Custom custom = new Custom();
                    custom.setAccount(username);
                    custom.setPassword(password);
                    custom.setTel(etTel.getText().toString());
                    customService.register(custom);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this,"信息填写错误,请检查！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}