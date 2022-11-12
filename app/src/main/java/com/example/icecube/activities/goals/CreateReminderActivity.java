package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.example.icecube.R;
import com.example.icecube.fragments.HeaderFragment;
import com.example.icecube.models.Reminder;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.RemindersService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class CreateReminderActivity extends AppCompatActivity {
    public static final String
            PARAMS_GOAL_ID = "goalId", PARAMS_PLAN_ID = "planId", PARAMS_REMINDER_ID = "reminderId";

    RemindersService rs;

    String goalId, planId, reminderId;
    EditText timeTxt, noOfCupsTxt;
    SwitchMaterial soundSwitch, vibrationSwitch;
    FrameLayout spinner;
    Button discardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        spinner = findViewById(R.id.create_rem_spinner);
        timeTxt = findViewById(R.id.create_rem_time_txt);
        noOfCupsTxt = findViewById(R.id.create_rem_cups_txt);
        soundSwitch = findViewById(R.id.create_rem_sound_switch);
        vibrationSwitch = findViewById(R.id.create_rem_vibration_switch);

        discardBtn = findViewById(R.id.create_rem_discard_btn);
        discardBtn.setOnClickListener(this::onDiscardClick);

        findViewById(R.id.create_rem_save_btn).setOnClickListener(this::onSaveClick);

        Bundle bundle = getIntent().getExtras();
        goalId = bundle.getString(PARAMS_GOAL_ID);
        planId = bundle.getString(PARAMS_PLAN_ID);
        rs = ServiceLocator.getInstance().getRemindersService(goalId, planId);

        if (bundle.containsKey(PARAMS_REMINDER_ID)) {
            reminderId = bundle.getString(PARAMS_REMINDER_ID);
            discardBtn.setText("Delete");
            loadData();
        }
    }


    // events
    void onSaveClick(View v) {
        showSpinner();
        saveWork(r -> {
            hideSpinner();
            discardBtn.setText("Delete");
        });
    }

    void onDiscardClick(View v) {
        if (reminderId == null)
            finish();
        else {
            showSpinner();
            rs.deleteReminder(reminderId, u -> {
                hideSpinner();
                finish();
            });
        }
    }


    // utils
    void saveWork(OnSuccessListener<Reminder> onSuccessListener) {
        Reminder r = buildReminder();
        if (reminderId == null)
            rs.createReminder(r, rem -> {
                reminderId = rem.id;
                onSuccessListener.onSuccess(r);
            });
        else
            rs.updateReminder(reminderId, r, rem -> {
                onSuccessListener.onSuccess(r);
            });
    }

    void loadData() {
        showSpinner();
        rs.getReminder(reminderId, r -> {
            hideSpinner();

            HeaderFragment header = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.create_rem_header);
            header.setTitle("Reminder Details");

            noOfCupsTxt.setText(String.valueOf(r.noOfCups));
            timeTxt.setText(r.time);
            soundSwitch.setChecked(r.enableSound);
            vibrationSwitch.setChecked(r.enableVibration);
        });
    }

    Reminder buildReminder() {
        Reminder r = new Reminder();

        r.enableSound = soundSwitch.isChecked();
        r.enableVibration = vibrationSwitch.isChecked();
        r.time = timeTxt.getText().toString();
        r.noOfCups = Integer.parseInt(noOfCupsTxt.getText().toString());

        return r;
    }

    void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    void hideSpinner() {
        spinner.setVisibility(View.GONE);
    }

}