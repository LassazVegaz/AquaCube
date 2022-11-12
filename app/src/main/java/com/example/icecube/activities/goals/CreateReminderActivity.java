package com.example.icecube.activities.goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.icecube.R;
import com.example.icecube.fragments.HeaderFragment;
import com.example.icecube.models.Reminder;
import com.example.icecube.services.ServiceLocator;
import com.example.icecube.services.goals.PlansService;
import com.example.icecube.services.goals.RemindersService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class CreateReminderActivity extends AppCompatActivity {
    public static final String
            PARAMS_GOAL_ID = "goalId", PARAMS_PLAN_ID = "planId", PARAMS_REMINDER_ID = "reminderId";

    private final static String
            MTV_TEXT_BAD = "%d cups are remaining", MTV_EMOJI_BAD = "😬",
            MTV_TEXT_GOOD = "This is a great plan", MTV_EMOJI_GOOD = "🫡";

    RemindersService rs;

    String goalId, planId, reminderId;
    EditText timeTxt, noOfCupsTxt;
    SwitchMaterial soundSwitch, vibrationSwitch;
    FrameLayout spinner;
    Button discardBtn;
    TextView mtvTxt, mtvEmojiTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        spinner = findViewById(R.id.create_rem_spinner);
        timeTxt = findViewById(R.id.create_rem_time_txt);
        soundSwitch = findViewById(R.id.create_rem_sound_switch);
        vibrationSwitch = findViewById(R.id.create_rem_vibration_switch);
        mtvTxt = findViewById(R.id.create_rem_mtv_txt);
        mtvEmojiTxt = findViewById(R.id.create_rem_mtv_emoji);

        noOfCupsTxt = findViewById(R.id.create_rem_cups_txt);
        noOfCupsTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setMotivationText();
            }
        });

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

        setMotivationText();
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

    void setMotivationText() {
        PlansService ps = ServiceLocator.getInstance().getPlansService(goalId);
        ps.getRemainingCups(planId, cups -> {
            String txt = MTV_TEXT_GOOD, emo = MTV_EMOJI_GOOD;

            try {
                int _cups = Integer.parseInt(noOfCupsTxt.getText().toString());
                cups = cups - _cups;
            } catch (Exception e) {
            }

            if (cups > 0) {
                txt = String.format(MTV_TEXT_BAD, cups);
                emo = MTV_EMOJI_BAD;
            }

            String finalTxt = txt;
            String finalEmo = emo;
            runOnUiThread(() -> {
                mtvTxt.setText(finalTxt);
                mtvEmojiTxt.setText(finalEmo);
            });
        });
    }

}