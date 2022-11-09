package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.icecube.R;

public class CreatePlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        findViewById(R.id.create_plan_add_reminder_btn).setOnClickListener(this::onCreateReminderClicked);
    }


    // events
    void onCreateReminderClicked(View v) {

    }
}