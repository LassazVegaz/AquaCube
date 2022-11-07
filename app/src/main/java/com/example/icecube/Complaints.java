package com.example.icecube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Complaints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
    }

    public void btnComplaints(View view) {
        Intent intent = new Intent(this, ViewComplaintsMain.class);
        startActivity(intent);
    }
}