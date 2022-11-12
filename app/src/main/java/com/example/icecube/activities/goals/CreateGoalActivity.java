package com.example.icecube.activities.goals;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.models.Goal;
import com.example.icecube.R;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.GoalsService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class CreateGoalActivity extends AppCompatActivity {
    public final static String PARAMS_GOAL_ID = "goalId";

    final static String MTV_TXT = "You have to drink only %d cups of water per day";

    String goalId;
    int selectedPotionIndex = 0;
    GoalsService gs = ServiceLocator.getInstance().getGoalsService();
    final int[] potionSizes = new int[]{200, 250, 300};

    EditText nameTxt, waterAmountTxt;
    TextView mtvTxt, activatorLbl;
    Button potionBtn;
    FrameLayout spinner;
    Button deleteBtn;
    SwitchMaterial activatorSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        spinner = findViewById(R.id.create_goal_spinner);
        mtvTxt = findViewById(R.id.create_goal_mtv_txt);
        nameTxt = findViewById(R.id.create_goal_goal_name_txt);
        activatorLbl = findViewById(R.id.create_goal_activate_lbl);
        activatorSwitch = findViewById(R.id.create_goal_activate_switch);

        waterAmountTxt = findViewById(R.id.create_goal_water_amount_txt);
        waterAmountTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                onWaterAmountChange();
            }
        });

        deleteBtn = findViewById(R.id.create_goal_delete_btn);
        deleteBtn.setOnClickListener(this::onDeleteButtonClick);

        potionBtn = findViewById(R.id.create_goal_portion_size_btn);
        potionBtn.setOnClickListener(this::onSelectPotionButtonClick);

        findViewById(R.id.create_goal_back_icon).setOnClickListener(this::onBackIconClicked);
        findViewById(R.id.create_goal_view_plans_btn).setOnClickListener(this::onViewPlansButtonClicked);
        findViewById(R.id.create_goal_save_btn).setOnClickListener(this::onSaveButtonClick);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(PARAMS_GOAL_ID)) {
            goalId = bundle.getString(PARAMS_GOAL_ID);
            deleteBtn.setVisibility(View.VISIBLE);
            loadGoalData();
        }
    }


    // events
    void onBackIconClicked(View v) {
        finish();
    }

    void onViewPlansButtonClicked(View v) {
        showSpinner();
        saveWork(g -> {
            hideSpinner();
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
            setMotivationText(null);
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void onSaveButtonClick(View v) {
        showSpinner();
        saveWork(g -> {
            hideSpinner();
            deleteBtn.setVisibility(View.VISIBLE);
        });
    }

    void onDeleteButtonClick(View v) {
        showSpinner();
        gs.deleteGoal(goalId, u -> {
            hideSpinner();
            finish();
        });
    }

    void onWaterAmountChange() {
        setMotivationText(null);
    }


    // utils
    @NonNull
    Goal buildGoal() {
        Goal goal = new Goal();
        goal.name = nameTxt.getText().toString();
        goal.waterAmount = Float.parseFloat(waterAmountTxt.getText().toString());
        goal.potionSize = potionSizes[selectedPotionIndex];
        goal.active = activatorSwitch.isChecked();
        return goal;
    }

    void loadGoalData() {
        showSpinner();
        gs.getGoalData(goalId, g -> {
            hideSpinner();
            nameTxt.setText(g.name);
            waterAmountTxt.setText(String.valueOf(g.waterAmount));
            potionBtn.setText(g.potionSize + "ml Cup");
            activatorSwitch.setChecked(g.active);

            selectedPotionIndex = findIndex(potionSizes, g.potionSize);
            if (selectedPotionIndex == -1) selectedPotionIndex = 0;

            setMotivationText(g.waterAmount);
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

    void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }

    void setMotivationText(Float waterAmount) {
        float _waterAmount = 0;

        if (waterAmount == null) {
            try {
                _waterAmount = Float.parseFloat(waterAmountTxt.getText().toString());
            } catch (Exception e) {
                return;
            }
        } else
            _waterAmount = waterAmount;

        int cups = (int) Math.ceil(_waterAmount * 1000 / potionSizes[selectedPotionIndex]);
        mtvTxt.setText(String.format(MTV_TXT, cups));
    }

    void hideActivator() {
        activatorSwitch.setVisibility(View.GONE);
        activatorLbl.setVisibility(View.GONE);
    }

}