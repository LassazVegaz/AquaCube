package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icecube.R;
import com.example.icecube.adapters.goals.OnPlanClickListener;
import com.example.icecube.adapters.goals.PlansListAdapter;
import com.example.icecube.models.Plan;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.PlansService;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class PlansListActivity extends AppCompatActivity {
    static final String PARAMS_GOAL_ID = "goalId";

    String goalId;
    PlansService ps;
    PlansListAdapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_list);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);
        ps = ServiceLocator.getInstance().getPlansService(goalId);
        setupAdapter();

        findViewById(R.id.plans_list_add_btn).setOnClickListener(this::onAddButtonClick);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rv.getRecycledViewPool().clear();
        adapter.notifyDataSetChanged();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    // events
    void onAddButtonClick(View v) {
        moveToCreatePlan(null);
    }

    void onPlanClick(View v, String plainId) {
        moveToCreatePlan(plainId);
    }


    // utils
    void setupAdapter() {
        Query query = ps.getPlansQuery();
        FirestoreRecyclerOptions<Plan> options = new FirestoreRecyclerOptions.Builder<Plan>()
                .setQuery(query, Plan.class)
                .build();

        adapter = new PlansListAdapter(options);
        adapter.setOnClickListener(this::onPlanClick);

        rv = findViewById(R.id.plans_list_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    void moveToCreatePlan(String planId) {
        Intent i = new Intent(this, CreatePlanActivity.class);
        i.putExtra(CreatePlanActivity.PARAMS_GOAL_ID, goalId);
        if (planId != null) i.putExtra(CreatePlanActivity.PARAMS_PLAN_ID, planId);

        startActivity(i);
    }

}