package com.example.icecube;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.icecube.databinding.ActivityViewComplaintsBinding;

public class ViewComplaintsActivity extends AppCompatActivity{

    ActivityViewComplaintsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewComplaintsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String Name = intent.getStringExtra("Name");
            String NameA = intent.getStringExtra("NameA");
            String PhoneNo = intent.getStringExtra("PhoneNo");
            String Dis = intent.getStringExtra("Dis");
            int ImageId = intent.getIntExtra("ImageId",R.drawable.a);

            binding.NameProfile.setText(Name);
            binding.NameAProfile.setText(NameA);
            binding.PhoneProfile.setText(PhoneNo);
            binding.DisProfile.setText(Dis);



        }

    }
    public void btnRelavantParties(View view) {
        Intent intent1 = new Intent(this, RelavantPartiesMain.class);
        startActivity(intent1);
    }
}