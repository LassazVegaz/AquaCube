package com.example.icecube.activities.goals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

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

    private final static String
            MTV_TEXT_BAD = "%d cups are remaining", MTV_EMOJI_BAD = "😬",
            MTV_TEXT_GOOD = "This is a great plan", MTV_EMOJI_GOOD = "🫡";

    PlansService ps;
    String goalId;
    String planId;
    DaySelectorFragment daySelector;
    RemindersAdapter adapter;
    RecyclerView rv;
    FrameLayout spinner;
    TextView mtvTxt, mtvEmojiTxt;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        daySelector = (DaySelectorFragment) getSupportFragmentManager().findFragmentById(R.id.create_plan_day_selector);
        spinner = findViewById(R.id.create_plan_spinner);
        mtvTxt = findViewById(R.id.create_plan_mtv_txt);
        mtvEmojiTxt = findViewById(R.id.create_plan_mtv_emoji);

        deleteBtn = findViewById(R.id.create_plan_delete_btn);
        deleteBtn.setOnClickListener(this::onDeleteButtonClick);

        findViewById(R.id.create_plan_add_reminder_btn).setOnClickListener(this::onCreateReminderClicked);
        findViewById(R.id.create_plan_save_btn).setOnClickListener(this::onSaveButtonClicked);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);
        ps = ServiceLocator.getInstance().getPlansService(goalId);
        setDisabledDays();

        if (bundle.containsKey(PARAMS_PLAN_ID)) {
            planId = bundle.getString(PARAMS_PLAN_ID);
            deleteBtn.setVisibility(View.VISIBLE);
            loadPlanData();
        }

        setupAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMotivationText();
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
        showSpinner();
        saveWork(plan -> {
            hideSpinner();
            deleteBtn.setVisibility(View.VISIBLE);
            if (adapter == null) setupAdapter();
        });
    }

    void onReminderClick(View v, String reminderId) {
        showSpinner();
        saveWork(p -> {
            hideSpinner();
            moveToCreateReminder(reminderId);
        });
    }

    void onDeleteButtonClick(View v) {
        showSpinner();
        ps.deletePlan(planId, (u) -> {
            hideSpinner();
            finish();
        });
    }


    // utils
    Plan buildPlan() {
        Plan p = new Plan();
        p.id = planId;
        p.days = daySelector.getDays();
        return p;
    }

    void loadPlanData() {
        showSpinner();
        ps.getPlan(planId, plan -> {
            hideSpinner();

            HeaderFragment header = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.create_plan_header);
            header.setTitle("Plan Details");

            daySelector.setDays(plan.days);
        });
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

    void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }

    void setMotivationText() {
        ps.getRemainingCups(planId, cups -> {
            String txt = MTV_TEXT_GOOD, emo = MTV_EMOJI_GOOD;
            if (cups > 0) {
                txt = String.format(MTV_TEXT_BAD, cups);
                emo = MTV_EMOJI_BAD;
            }

            String finalTxt = txt;
            String finalEmo = emo;
            runOnUiThread(() -> {
                mtvTxt.setText(finalTxt);
                mtvEmojiTxt.setText(finalEmo);
            });
        });
    }

}