package com.example.icecube.activities.mithun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.icecube.R;

public class SingleSociety extends AppCompatActivity {


    TextView singlename, singlearea, singleissue, singledis, singlephone, singledate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mithun);

        singlename = findViewById(R.id.singlename);
        singlearea = findViewById(R.id.singlearea);
        singleissue = findViewById(R.id.singleissue);
        singledis = findViewById(R.id.singledis);
        singlephone = findViewById(R.id.singlephone);
        singledate = findViewById(R.id.singledate);


        singlename.setText(getIntent().getStringExtra("singlename"));
        singlearea.setText(getIntent().getStringExtra("singlearea"));
        singleissue.setText(getIntent().getStringExtra("singleissue"));
        singledis.setText(getIntent().getStringExtra("singledis"));
        singlephone.setText(getIntent().getStringExtra("singlephone"));
        singledate.setText(getIntent().getStringExtra("singledate"));


    }



}