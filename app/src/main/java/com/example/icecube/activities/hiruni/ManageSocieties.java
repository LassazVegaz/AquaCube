package com.example.icecube.activities.hiruni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icecube.R;
import com.example.icecube.activities.mithun.SocietiesInsert;
import com.example.icecube.activities.mithun.SocietiesList;

public class ManageSocieties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_societies);
    }

    public void onSocsBtnClick(View v) {
        Intent i = new Intent(this, SocietiesList.class);
        startActivity(i);
    }

    public void onPlusBtnClick(View v) {
        Intent i = new Intent(this, SocietiesInsert.class);
        startActivity(i);
    }


}