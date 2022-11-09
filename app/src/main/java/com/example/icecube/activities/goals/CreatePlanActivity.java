package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.icecube.R;
import com.example.icecube.fragments.goals.DaySelectorFragment;
import com.example.icecube.models.Plan;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.PlansService;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class CreatePlanActivity extends AppCompatActivity {
    final static String PARAMS_GOAL_ID = "goalId", PARAMS_PLAN_ID = "planId";

    PlansService ps;
    String goalId;
    String planId;
    DaySelectorFragment daySelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);
        ps = ServiceLocator.getInstance().getPlansService(goalId);

        daySelector = (DaySelectorFragment) getSupportFragmentManager().findFragmentById(R.id.create_plan_day_selector);

        findViewById(R.id.create_plan_add_reminder_btn).setOnClickListener(this::onCreateReminderClicked);
        findViewById(R.id.create_plan_save_btn).setOnClickListener(this::onSaveButtonClicked);
    }


    // events
    void onCreateReminderClicked(View v) {
    }

    void onSaveButtonClicked(View v) {
        Plan p = buildPlan();
        ps.createPlan(p, plan -> Log.d("PLAN", "plan was created"));
    }


    // utils
    Plan buildPlan() {
        Plan p = new Plan();
        p.days = daySelector.getDays();
        return p;
    }
}