package com.example.icecube.activities.sathinka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.R;

public class campaigndetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_campaigndetail);
    }
    public void AddData(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}