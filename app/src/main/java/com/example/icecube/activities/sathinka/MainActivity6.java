package com.example.icecube.activities.sathinka;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icecube.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {


    EditText editTextName;
    Button buttonAdd;
    Spinner spinnerGenres;


    DatabaseReference databaseArtists;
    ListView listViewArtists;
    List<CampaignOrg> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        databaseArtists = FirebaseDatabase.getInstance().getReference("campaigns");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAdd = (Button) findViewById(R.id.buttonAddArtist);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);

        listViewArtists = (ListView) findViewById(R.id.listViewArtists);

        artistList=new ArrayList<>();


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
            }
        });
    }

    public void ListV
            (View view) {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    @Override

    protected void onStart(){
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                artistList.clear();

                for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()){
                    CampaignOrg artist=artistSnapshot.getValue(CampaignOrg.class);

                    artistList.add(artist);
                }
                CampaignList adapter=new CampaignList(MainActivity6.this,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    private void addArtist() {

        String location = editTextName.getText().toString().trim();
        String programType = spinnerGenres.getSelectedItem().toString();
        if (!TextUtils.isEmpty(location)) {
            String id = databaseArtists.push().getKey();
            CampaignOrg artist = new CampaignOrg(id, location, programType);

            databaseArtists.child(id).setValue(artist);

            editTextName.setText("");
            Toast.makeText(this, "Campaign added", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Please enter a Location", Toast.LENGTH_LONG).show();
        }
    }



    }

