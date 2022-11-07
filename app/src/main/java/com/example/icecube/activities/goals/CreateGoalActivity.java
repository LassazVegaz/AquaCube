package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.icecube.R;

public class CreateGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        ImageView backImg = (ImageView) findViewById(R.id.create_goal_back_icon);
        backImg.setOnClickListener((v) -> onBackIconClicked());
    }

    private void onBackIconClicked() {
        finish();
    }
}