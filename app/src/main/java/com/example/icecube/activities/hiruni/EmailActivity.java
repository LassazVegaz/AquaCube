package com.example.icecube.activities.hiruni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icecube.databinding.ActivityEmailBinding;


public class EmailActivity extends AppCompatActivity {

      ActivityEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

        binding.sendbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email1 = binding.email.getText().toString();
                String subject1 = binding.subject.getText().toString();
                String msg1 = binding.msg.getText().toString();


                String[] addresses= email1.split(",");

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT,subject1);
                intent.putExtra(Intent.EXTRA_TEXT,msg1);

                if(intent.resolveActivity(getPackageManager()) != null) {

                    startActivity(intent);
                }else{
                    Toast.makeText(EmailActivity.this,"No app is installed to send mails",Toast.LENGTH_SHORT).show();
                }

            }
        
        });
}
    }
