package com.example.icecube.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.icecube.R;

public class SignInActivity extends AppCompatActivity {
    EditText emailTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailTxt = findViewById(R.id.signin_email_txt);
        passwordTxt = findViewById(R.id.signin_password_txt);

        findViewById(R.id.signin_login_btn).setOnClickListener(this::onLoginClick);
        findViewById(R.id.signin_register_btn).setOnClickListener(this::onRegisterClick);
    }

    void onLoginClick(View v) {
    }

    void onRegisterClick(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}