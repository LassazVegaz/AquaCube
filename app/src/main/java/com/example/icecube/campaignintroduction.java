package com.example.icecube;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class campaignintroduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_campaign);
    }
    public void btn(View view) {
        Intent intent = new Intent(this, campaigndetail.class);
        startActivity(intent);
    }

}