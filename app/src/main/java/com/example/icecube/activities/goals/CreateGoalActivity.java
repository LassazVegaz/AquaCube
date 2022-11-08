package com.example.icecube.activities.goals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.icecube.R;

public class CreateGoalActivity extends AppCompatActivity {
    private int selectedPotionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        ImageView backImg = findViewById(R.id.create_goal_back_icon);
        backImg.setOnClickListener(this::onBackIconClicked);

        Button viewPlansBtn = findViewById(R.id.create_goal_view_plans_btn);
        viewPlansBtn.setOnClickListener(this::onViewPlansButtonClicked);

        Button selectPotionBtn = findViewById(R.id.create_goal_portion_size_btn);
        selectPotionBtn.setOnClickListener(this::onSelectPotionButtonClick);
    }

    private void onBackIconClicked(View v) {
        finish();
    }

    private void onViewPlansButtonClicked(View v) {
        Intent i = new Intent(this, PlansListActivity.class);
        startActivity(i);
    }

    private void onSelectPotionButtonClick(View v) {
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

}