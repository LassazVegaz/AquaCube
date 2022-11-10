package com.example.icecube.activities.goals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecube.R;
import com.example.icecube.adapters.goals.RemindersAdapter;
import com.example.icecube.fragments.HeaderFragment;
import com.example.icecube.fragments.goals.DaySelectorFragment;
import com.example.icecube.models.Plan;
import com.example.icecube.models.Reminder;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.PlansService;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class CreatePlanActivity extends AppCompatActivity {
    final static String PARAMS_GOAL_ID = "goalId", PARAMS_PLAN_ID = "planId";

    PlansService ps;
    String goalId;
    String planId;
    DaySelectorFragment daySelector;
    RemindersAdapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);
        ps = ServiceLocator.getInstance().getPlansService(goalId);
        setDisabledDays();

        if (bundle.containsKey(PARAMS_PLAN_ID)) {
            HeaderFragment header = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.create_plan_header);
            header.setTitle("Plan Details");
            planId = bundle.getString(PARAMS_PLAN_ID);
            loadPlanData();
        }

        daySelector = (DaySelectorFragment) getSupportFragmentManager().findFragmentById(R.id.create_plan_day_selector);

        findViewById(R.id.create_plan_add_reminder_btn).setOnClickListener(this::onCreateReminderClicked);
        findViewById(R.id.create_plan_save_btn).setOnClickListener(this::onSaveButtonClicked);

        setupAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (rv == null || adapter == null) return;
        rv.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter == null) return;
        adapter.stopListening();
    }


    // events
    void onCreateReminderClicked(View v) {
        saveWork(plan -> moveToCreateReminder(null));
    }

    void onSaveButtonClicked(View v) {
        saveWork(plan -> {
            if (adapter == null) setupAdapter();
        });
    }

    void onReminderClick(View v, String reminderId) {
        saveWork(p -> moveToCreateReminder(reminderId));
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

    void setupAdapter() {
        if (planId == null) return;

        FirestoreRecyclerOptions<Reminder> options = new FirestoreRecyclerOptions.Builder<Reminder>()
                .setQuery(ps.getRemindersQuery(planId), Reminder.class)
                .build();
        adapter = new RemindersAdapter(options, this::onReminderClick);

        rv = findViewById(R.id.create_plan_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    void moveToCreateReminder(String reminderId) {
        Intent i = new Intent(this, CreateReminderActivity.class);

        i.putExtra(CreateReminderActivity.PARAMS_GOAL_ID, goalId);
        i.putExtra(CreateReminderActivity.PARAMS_PLAN_ID, planId);
        if (reminderId != null) i.putExtra(CreateReminderActivity.PARAMS_REMINDER_ID, reminderId);

        startActivity(i);
    }
}