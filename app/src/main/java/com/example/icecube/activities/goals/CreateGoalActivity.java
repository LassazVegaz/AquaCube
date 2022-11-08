package com.example.icecube.activities.goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.R;

public class CreateGoalActivity extends AppCompatActivity {
    int selectedPotionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        findViewById(R.id.create_goal_back_icon).setOnClickListener(this::onBackIconClicked);

        findViewById(R.id.create_goal_view_plans_btn).setOnClickListener(this::onViewPlansButtonClicked);

        findViewById(R.id.create_goal_portion_size_btn).setOnClickListener(this::onSelectPotionButtonClick);

        findViewById(R.id.create_goal_save_btn).setOnClickListener(this::onSaveButtonClick);
    }

    void onBackIconClicked(View v) {
        finish();
    }

    void onViewPlansButtonClicked(View v) {
        Intent i = new Intent(this, PlansListActivity.class);
        startActivity(i);
    }

    void onSelectPotionButtonClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a potion");
        final String[] items = new String[]{"250ml Cup", "300ml Cup", "350ml Cup"};

        builder.setSingleChoiceItems(items, selectedPotionIndex, (d, which) -> {
            selectedPotionIndex = which;
            ((Button) v).setText(items[selectedPotionIndex]);
            d.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void onSaveButtonClick(View v) {
    }

}