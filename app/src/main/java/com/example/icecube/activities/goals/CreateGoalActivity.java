package com.example.icecube.activities.goals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.DTOs.in.GoalIn;
import com.example.icecube.R;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.GoalsService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

public class CreateGoalActivity extends AppCompatActivity {
    int selectedPotionIndex = 0;
    GoalsService gs = ServiceLocator.getInstance().getGoalsService();
    final int[] potionSizes = new int[]{200, 250, 300};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        findViewById(R.id.create_goal_back_icon).setOnClickListener(this::onBackIconClicked);

        findViewById(R.id.create_goal_view_plans_btn).setOnClickListener(this::onViewPlansButtonClicked);

        findViewById(R.id.create_goal_portion_size_btn).setOnClickListener(this::onSelectPotionButtonClick);

        findViewById(R.id.create_goal_save_btn).setOnClickListener(this::onSaveButtonClick);
    }

    String[] getPotionsStr() {
        ArrayList<String> arr = new ArrayList<>();

        for (int p : potionSizes) {
            arr.add(p + "ml Cup");
        }

        String[] res = new String[arr.size()];
        return arr.toArray(res);
    }

    // events
    void onBackIconClicked(View v) {
        finish();
    }

    void onViewPlansButtonClicked(View v) {
        GoalIn goal = buildGoal();
        gs.createGoal(goal, task -> {
            Intent i = new Intent(this, PlansListActivity.class);
            startActivity(i);
        });
    }

    void onSelectPotionButtonClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a potion");
        final String[] items = getPotionsStr();

        builder.setSingleChoiceItems(items, selectedPotionIndex, (d, which) -> {
            selectedPotionIndex = which;
            ((Button) v).setText(items[selectedPotionIndex]);
            d.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void onSaveButtonClick(View v) {
        GoalIn goal = buildGoal();
        gs.createGoal(goal, task -> Log.d("goals", "Goal was created"));
    }

    // utils
    @NonNull
    private GoalIn buildGoal() {
        GoalIn goal = new GoalIn();
        goal.name = ((EditText) findViewById(R.id.create_goal_goal_name_txt)).getText().toString();
        goal.waterAmount = Integer.parseInt(((EditText) findViewById(R.id.create_goal_water_amount_txt)).getText().toString());
        goal.potionSize = potionSizes[selectedPotionIndex];
        return goal;
    }

}