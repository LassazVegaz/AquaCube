package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.icecube.R;
import com.example.icecube.adapters.goals.GoalsListAdapter;
import com.example.icecube.models.Goal;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.GoalsService;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class GoalsListActivity extends AppCompatActivity {
    final GoalsService gs = ServiceLocator.getInstance().getGoalsService();
    GoalsListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);

        Button addBtn = findViewById(R.id.goals_list_add_btn);
        addBtn.setOnClickListener((v) -> onAddButtonClick());

        setupAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    // events
    private void onAddButtonClick() {
        Intent i = new Intent(this, CreateGoalActivity.class);
        startActivity(i);
    }


    // utils
    private void setupAdapter() {
        Query query = gs.getGoalsQuery();
        FirestoreRecyclerOptions<Goal> options = new FirestoreRecyclerOptions.Builder<Goal>()
                .setQuery(query, Goal.class)
                .build();
        adapter = new GoalsListAdapter(options);

        RecyclerView goalsListRv = findViewById(R.id.goals_list_rv);
        goalsListRv.setLayoutManager(new LinearLayoutManager(this));
        goalsListRv.setAdapter(adapter);
    }

}