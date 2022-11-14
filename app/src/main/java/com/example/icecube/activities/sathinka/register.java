package com.example.icecube.activities.sathinka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.R;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

    }
    public void AddRegister(View view) {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }
}