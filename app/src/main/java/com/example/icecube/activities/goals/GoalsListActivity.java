package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.icecube.R;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.GoalsService;

public class GoalsListActivity extends AppCompatActivity {
    final GoalsService gs = ServiceLocator.getInstance().getGoalsService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);

        Button addBtn = findViewById(R.id.goals_list_add_btn);
        addBtn.setOnClickListener((v) -> onAddButtonClick());

        gs.getGoals(goals -> {
        });
    }

    private void onAddButtonClick() {
        Intent i = new Intent(this, CreateGoalActivity.class);
        startActivity(i);
    }

}