package com.example.icecube.activities.hiruni;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.icecube.R;
import com.example.icecube.databinding.ActivityRelavantPartiesBinding;

public class RelavantPartiesActivity extends AppCompatActivity{

    ActivityRelavantPartiesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRelavantPartiesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String Name = intent.getStringExtra("Name");
            String NameA = intent.getStringExtra("NameA");
            String PhoneNo = intent.getStringExtra("PhoneNo");
            String Dis = intent.getStringExtra("Dis");
            int ImageId = intent.getIntExtra("ImageId", R.drawable.a);

            binding.NameProfile.setText(Name);
            binding.NameAProfile.setText(NameA);
            binding.PhoneProfile.setText(PhoneNo);
            binding.DisProfile.setText(Dis);



        }

    }


}