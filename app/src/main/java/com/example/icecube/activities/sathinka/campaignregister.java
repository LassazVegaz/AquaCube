package com.example.icecube.activities.sathinka;

import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.icecube.R;
import com.google.android.gms.tasks.OnCompleteListener;
import java.util.HashMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class campaignregister extends AppCompatActivity {
    EditText txtID5, txtID6, txtID7, txtID8,txtID9;
    Button button4,btnshow,btnupdate;
    Details details;
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_campaignregister);
        txtID5 = (EditText) findViewById((R.id.txtID5));
        txtID6 = (EditText) findViewById((R.id.txtID6));
        txtID7 = (EditText) findViewById((R.id.txtID7));
        txtID8 = (EditText) findViewById((R.id.txtID8));
        txtID9 = (EditText) findViewById((R.id.txtID9));

        button4 = (Button) findViewById(R.id.button4);
        btnshow = (Button) findViewById(R.id.btnshow);
        btnupdate = (Button) findViewById(R.id.btnupdate);


        details = new Details();
        dbref = FirebaseDatabase.getInstance().getReference().child("details");
        //insert
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                details.setName(txtID5.getText().toString().trim());
                details.setAge(txtID6.getText().toString().trim());
                details.setArea(txtID7.getText().toString().trim());
                details.setPreferDate(txtID8.getText().toString().trim());
                details.setFeedback(txtID9.getText().toString().trim());


                dbref.push().setValue(details);
                Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();

            }
        });
        //retrieve
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Details").child("details2");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Name = dataSnapshot.child("Name").getValue().toString();
                        String Age = dataSnapshot.child("Age").getValue().toString();
                        String Area = dataSnapshot.child("Area").getValue().toString();
                        String PreferDate = dataSnapshot.child("PreferDate").getValue().toString();
                        String Feedback = dataSnapshot.child("Feedback").getValue().toString();

                        txtID5.setText(Name);
                        txtID6.setText(Age);
                        txtID7.setText(Area);
                        txtID8.setText(PreferDate);
                        txtID9.setText(Feedback);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //update
        btnupdate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String Name= txtID5.getText().toString().trim();
                String Age= txtID6.getText().toString().trim();
                String Area= txtID7.getText().toString().trim();
                String PreferDate= txtID8.getText().toString().trim();
                String Feedback= txtID9.getText().toString().trim();
                updateDetails(Name, Age, Area, PreferDate,Feedback);
            }
        });
    }

    private void updateDetails(String Name,String Age,String Area, String PreferDate, String Feedback){
        HashMap details = new HashMap();
        details.put("Name", Name);
        details.put("Age", Age);
        details.put("Area", Area);
        details.put("PreferDate", PreferDate);
        details.put("Feedback", Feedback);

        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Details").child("details2");
        upRef.updateChildren(details).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(campaignregister.this, "updated successfully!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(campaignregister.this, "Updating Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }}