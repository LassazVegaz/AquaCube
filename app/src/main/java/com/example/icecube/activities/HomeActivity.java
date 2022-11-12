package com.example.icecube.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icecube.R;
import com.example.icecube.activities.auth.SignInActivity;
import com.example.icecube.activities.goals.GoalsListActivity;
import com.example.icecube.services.AuthService;
import com.example.icecube.services.ServiceLocator;

public class HomeActivity extends AppCompatActivity {
    final AuthService as = ServiceLocator.getInstance().getAuthService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.home_goals_menu).setOnClickListener(this::onGoalsMenuClick);
        findViewById(R.id.home_logout_btn).setOnClickListener(this::onLogoutClick);
    }

    void onGoalsMenuClick(View v) {
        Intent i = new Intent(this, GoalsListActivity.class);
        startActivity(i);
    }

    void onLogoutClick(View v) {
        as.logout();
        Intent i = new Intent(this, SignInActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}