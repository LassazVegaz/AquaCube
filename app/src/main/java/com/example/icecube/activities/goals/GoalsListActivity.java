package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

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
    RecyclerView rv;
    FrameLayout spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);

        spinner = findViewById(R.id.goals_list_spinner);

        Button addBtn = findViewById(R.id.goals_list_add_btn);
        addBtn.setOnClickListener((v) -> onAddButtonClick());

        setupAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        setNoGoalsBanner();
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
    private void onAddButtonClick() {
        Intent i = new Intent(this, CreateGoalActivity.class);
        startActivity(i);
    }


    // utils
    void setupAdapter() {
        Query query = gs.getGoalsQuery();
        FirestoreRecyclerOptions<Goal> options = new FirestoreRecyclerOptions.Builder<Goal>()
                .setQuery(query, Goal.class)
                .build();
        adapter = new GoalsListAdapter(options);

        rv = findViewById(R.id.goals_list_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    void setNoGoalsBanner() {
        showSpinner();
        gs.areGoalsEmpty(isEmpty -> {
            hideSpinner();
            if (isEmpty)
                findViewById(R.id.goals_list_no_goals_banner).setVisibility(View.VISIBLE);
        });
    }

    void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }

}