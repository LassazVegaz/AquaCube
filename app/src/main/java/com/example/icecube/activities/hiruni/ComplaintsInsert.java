package com.example.icecube.activities.hiruni;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icecube.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintsInsert extends AppCompatActivity {


    TextView name, area, issue, dis, phone, date;
    Button save, discard;
    Uri ImageUri;

    DatabaseReference studentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_insert);

        name = findViewById(R.id.name);
        area = findViewById(R.id.area);
        issue = findViewById(R.id.issue);
        dis = findViewById(R.id.dis);
        phone = findViewById(R.id.phone);
        date = findViewById(R.id.date);

        save = findViewById(R.id.save);
        discard = findViewById(R.id.discard);


        studentDbRef = FirebaseDatabase.getInstance().getReference().child("Complaints");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertStudentDate();

            }
        });
    }

    private void insertStudentDate() {

        String name1 = name.getText().toString();
        String area1 = area.getText().toString();
        String issue1 = issue.getText().toString();
        String dis1 = dis.getText().toString();
        String phone1 = phone.getText().toString();
        String date1 = date.getText().toString();

        ComplaintsModel complaintsModel = new ComplaintsModel(name1, area1, issue1, dis1, phone1, date1);

        studentDbRef.push().setValue(complaintsModel);
        Toast.makeText(ComplaintsInsert.this, "Complaint Inserted Successfully!!", Toast.LENGTH_SHORT).show();


    }
}