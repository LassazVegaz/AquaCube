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
import com.example.icecube.models.User;
import com.example.icecube.services.AuthService;
import com.example.icecube.services.ServiceLocator;

public class SignUpActivity extends AppCompatActivity {
    final AuthService as = ServiceLocator.getInstance().getAuthService();

    EditText emailTxt, passwordTxt, nameTxt;
    FrameLayout spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinner = findViewById(R.id.signup_spinner);
        emailTxt = findViewById(R.id.signup_email_txt);
        passwordTxt = findViewById(R.id.signup_password_txt);
        nameTxt = findViewById(R.id.signup_name_txt);

        findViewById(R.id.signup_login_btn).setOnClickListener(this::onLoginClick);
        findViewById(R.id.signup_register_btn).setOnClickListener(this::onRegisterClick);
    }

    void onLoginClick(View v) {
        moveAndClean(SignInActivity.class);
    }

    void onRegisterClick(View v) {
        showSpinner();
        User u = buildUser();
        as.register(u, passwordTxt.getText().toString(),
                unused -> {
                    hideSpinner();
                    moveAndClean(HomeActivity.class);
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

    User buildUser() {
        User u = new User();

        u.email = emailTxt.getText().toString();
        u.name = nameTxt.getText().toString();

        return u;
    }

    void moveAndClean(Class<?> cls) {
        Intent i = new Intent(this, cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}