package com.example.icecube.activities.hiruni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icecube.R;

public class PopulationMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_population_menu);
    }

    public void btnSocieties(View view) {
        Intent intent = new Intent(this, ManageSocieties.class);
        startActivity(intent);
    }
}