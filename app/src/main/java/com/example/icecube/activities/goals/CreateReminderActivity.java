package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.icecube.R;

public class CreateReminderActivity extends AppCompatActivity {
    public static final String
            PARAMS_GOAL_ID = "goalId", PARAMS_PLAN_ID = "planId", PARAMS_REMINDER_ID = "reminderId";

    String goalId, planId, reminderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);
        planId = bundle.getString(PARAMS_PLAN_ID);

        if (bundle.containsKey(PARAMS_REMINDER_ID)) {
            reminderId = bundle.getString(PARAMS_REMINDER_ID);
        }
    }
}