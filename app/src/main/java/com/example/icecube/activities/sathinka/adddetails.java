package com.example.icecube.activities.sathinka;


import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.icecube.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class adddetails extends AppCompatActivity {
    EditText txtID, txtID2, txtID3, txtID4;
    Button button2;
    Campaign campaign;
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_campaignorganize);





        txtID = (EditText) findViewById((R.id.txtID));
        txtID2 = (EditText) findViewById((R.id.txtID2));
        txtID3 = (EditText) findViewById((R.id.txtID3));
        txtID4 = (EditText) findViewById((R.id.txtID4));

        button2 = (Button) findViewById(R.id.button2);

        campaign = new Campaign();
        dbref = FirebaseDatabase.getInstance().getReference().child("Campaign");
        //insert
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                campaign.setProgramType(txtID.getText().toString().trim());
                campaign.setDate(txtID2.getText().toString().trim());
                campaign.setLocation(txtID3.getText().toString().trim());
                campaign.setOther(txtID4.getText().toString().trim());


                dbref.push().setValue(campaign);
                Toast.makeText(getApplicationContext(), "Campaign data inserted successfully", Toast.LENGTH_SHORT).show();

            }

        });





    }}