package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.icecube.R;

public class GoalsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);

        Button addBtn = (Button) findViewById(R.id.goals_list_add_btn);
        addBtn.setOnClickListener((v) -> onAddButtonClick());
    }

    private void onAddButtonClick() {
        Intent i = new Intent(this, CreateGoalActivity.class);
        startActivity(i);
    }

}