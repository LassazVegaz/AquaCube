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

public class SignInActivity extends AppCompatActivity {
    final AuthService as = ServiceLocator.getInstance().getAuthService();

    EditText emailTxt, passwordTxt;
    FrameLayout spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (as.isLoggedIn())
            moveAndClean(HomeActivity.class);

        spinner = findViewById(R.id.signin_spinner);
        emailTxt = findViewById(R.id.signin_email_txt);
        passwordTxt = findViewById(R.id.signin_password_txt);

        findViewById(R.id.signin_login_btn).setOnClickListener(this::onLoginClick);
        findViewById(R.id.signin_register_btn).setOnClickListener(this::onRegisterClick);
    }

    void onLoginClick(View v) {
        showSpinner();
        as.login(emailTxt.getText().toString(), passwordTxt.getText().toString(),
                unused -> {
                    hideSpinner();
                    moveAndClean(HomeActivity.class);
                }, e -> {
                    hideSpinner();
                    Toast.makeText(this, "Incorrect email address or password", Toast.LENGTH_SHORT).show();
                });
    }

    void onRegisterClick(View v) {
        moveAndClean(SignUpActivity.class);
    }

    void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }

    void moveAndClean(Class<?> cls) {
        Intent i = new Intent(this, cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}