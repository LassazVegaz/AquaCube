package com.example.icecube.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icecube.R;
import com.example.icecube.activities.goals.GoalsListActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.home_goals_menu).setOnClickListener(this::onGoalsMenuClick);
    }

    void onGoalsMenuClick(View v) {
        Intent i = new Intent(this, GoalsListActivity.class);
        startActivity(i);
    }

}