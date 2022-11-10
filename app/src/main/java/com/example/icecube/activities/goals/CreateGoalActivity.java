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

import com.example.icecube.models.Goal;
import com.example.icecube.R;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.GoalsService;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CreateGoalActivity extends AppCompatActivity {
    public final static String PARAMS_GOAL_ID = "goalId";

    String goalId;
    int selectedPotionIndex = 0;
    GoalsService gs = ServiceLocator.getInstance().getGoalsService();
    final int[] potionSizes = new int[]{200, 250, 300};

    EditText nameTxt, waterAmount;
    Button potionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(PARAMS_GOAL_ID)) {
            goalId = bundle.getString(PARAMS_GOAL_ID);
            loadGoalData();
        }

        potionBtn = findViewById(R.id.create_goal_portion_size_btn);

        findViewById(R.id.create_goal_back_icon).setOnClickListener(this::onBackIconClicked);
        findViewById(R.id.create_goal_view_plans_btn).setOnClickListener(this::onViewPlansButtonClicked);
        findViewById(R.id.create_goal_save_btn).setOnClickListener(this::onSaveButtonClick);
        potionBtn.setOnClickListener(this::onSelectPotionButtonClick);

        nameTxt = findViewById(R.id.create_goal_goal_name_txt);
        waterAmount = findViewById(R.id.create_goal_water_amount_txt);
    }


    // events
    void onBackIconClicked(View v) {
        finish();
    }

    void onViewPlansButtonClicked(View v) {
        saveWork(g -> {
            Intent i = new Intent(this, PlansListActivity.class);
            i.putExtra(PlansListActivity.PARAMS_GOAL_ID, goalId);
            startActivity(i);
        });
    }

    void onSelectPotionButtonClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a potion");
        final String[] items = getPotionsStr();

        builder.setSingleChoiceItems(items, selectedPotionIndex, (d, which) -> {
            selectedPotionIndex = which;
            potionBtn.setText(items[selectedPotionIndex]);
            d.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void onSaveButtonClick(View v) {
        saveWork(g -> {
        });
    }


    // utils
    @NonNull
    Goal buildGoal() {
        Goal goal = new Goal();
        goal.name = nameTxt.getText().toString();
        goal.waterAmount = Float.parseFloat(waterAmount.getText().toString());
        goal.potionSize = potionSizes[selectedPotionIndex];
        return goal;
    }

    void loadGoalData() {
        gs.getGoalData(goalId, g -> {
            nameTxt.setText(g.name);
            waterAmount.setText(String.valueOf(g.waterAmount));
            potionBtn.setText(g.potionSize + "ml Cup");

            selectedPotionIndex = findIndex(potionSizes, g.potionSize);
            if (selectedPotionIndex == -1) selectedPotionIndex = 0;
        });
    }

    int findIndex(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target) return i;
        return -1;
    }

    String[] getPotionsStr() {
        ArrayList<String> arr = new ArrayList<>();

        for (int p : potionSizes) {
            arr.add(p + "ml Cup");
        }

        String[] res = new String[arr.size()];
        return arr.toArray(res);
    }

    void saveWork(OnSuccessListener<Goal> onSuccessListener) {
        Goal goal = buildGoal();
        if (goalId == null)
            gs.createGoal(goal, g -> {
                goalId = g.id;
                onSuccessListener.onSuccess(g);
            });
        else
            gs.updateGoal(goalId, goal, onSuccessListener);
    }

}