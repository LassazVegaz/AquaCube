package com.example.icecube.activities.goals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.R;
import com.example.icecube.fragments.goals.DaySelectorFragment;
import com.example.icecube.models.Plan;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.PlansService;
import com.google.android.gms.tasks.OnSuccessListener;

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
        setDisabledDays();

        if (bundle.containsKey(PARAMS_PLAN_ID)) {
            planId = bundle.getString(PARAMS_PLAN_ID);
            loadPlanData();
        }

        daySelector = (DaySelectorFragment) getSupportFragmentManager().findFragmentById(R.id.create_plan_day_selector);

        findViewById(R.id.create_plan_add_reminder_btn).setOnClickListener(this::onCreateReminderClicked);
        findViewById(R.id.create_plan_save_btn).setOnClickListener(this::onSaveButtonClicked);
    }


    // events
    void onCreateReminderClicked(View v) {
        saveWork(plan -> {
            Intent i = new Intent(this, CreateReminderActivity.class);
            i.putExtra(CreateReminderActivity.PARAMS_GOAL_ID, goalId);
            i.putExtra(CreateReminderActivity.PARAMS_PLAN_ID, planId);
            startActivity(i);
        });
    }

    void onSaveButtonClicked(View v) {
        saveWork(plan -> {});
    }


    // utils
    Plan buildPlan() {
        Plan p = new Plan();
        p.id = planId;
        p.days = daySelector.getDays();
        return p;
    }

    void loadPlanData() {
        ps.getPlan(planId, plan -> daySelector.setDays(plan.days));
    }

    void setDisabledDays() {
        ps.getDisabledDays(disabledDays -> daySelector.setDisabledDays(disabledDays));
    }

    void saveWork(OnSuccessListener<Plan> onSuccessListener) {
        Plan p = buildPlan();
        if (planId == null)
            ps.createPlan(p, plan -> {
                planId = p.id;
                onSuccessListener.onSuccess(plan);
            });
        else
            ps.updatePlan(planId, p, onSuccessListener);
    }
}