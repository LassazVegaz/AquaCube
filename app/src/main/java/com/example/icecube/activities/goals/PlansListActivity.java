package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icecube.R;

public class PlansListActivity extends AppCompatActivity {
    static final String PARAMS_GOAL_ID = "goalId";

    String goalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_list);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);

        findViewById(R.id.plans_list_add_btn).setOnClickListener(this::onAddButtonClicked);
    }


    // events
    void onAddButtonClicked(View v) {
        Intent i = new Intent(this, CreatePlanActivity.class);
        i.putExtra(CreatePlanActivity.PARAMS_GOAL_ID, goalId);
        startActivity(i);
    }

}