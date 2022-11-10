package com.example.icecube.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.icecube.R;
import com.example.icecube.activities.HomeActivity;
import com.example.icecube.services.AuthService;
import com.example.icecube.services.ServiceLocator;

public class SignUpActivity extends AppCompatActivity {
    final AuthService as = ServiceLocator.getInstance().getAuthService();

    EditText emailTxt, passwordTxt;
    FrameLayout spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinner = findViewById(R.id.signup_spinner);
        emailTxt = findViewById(R.id.signup_email_txt);
        passwordTxt = findViewById(R.id.signup_password_txt);

        findViewById(R.id.signup_login_btn).setOnClickListener(this::onLoginClick);
        findViewById(R.id.signup_register_btn).setOnClickListener(this::onRegisterClick);
    }

    void onLoginClick(View v) {
        Intent i = new Intent(this, SignInActivity.class);
        finish();
        startActivity(i);
    }

    void onRegisterClick(View v) {
        showSpinner();
        as.register(emailTxt.getText().toString(), passwordTxt.getText().toString(),
                unused -> {
                    hideSpinner();
                    Intent i = new Intent(this, HomeActivity.class);
                    finish();
                    startActivity(i);
                }, e -> {
                    hideSpinner();
                    Toast.makeText(this, "This email address already has an account", Toast.LENGTH_SHORT).show();
                });
    }


    void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }
}