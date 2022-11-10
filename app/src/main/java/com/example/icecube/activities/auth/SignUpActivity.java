package com.example.icecube.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.icecube.R;

public class SignUpActivity extends AppCompatActivity {
    EditText emailTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailTxt = findViewById(R.id.signup_email_txt);
        passwordTxt = findViewById(R.id.signup_password_txt);

        findViewById(R.id.signup_login_btn).setOnClickListener(this::onLoginClick);
        findViewById(R.id.signup_register_btn).setOnClickListener(this::onRegisterClick);
    }

    void onLoginClick(View v) {
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
    }

    void onRegisterClick(View v) {}
}