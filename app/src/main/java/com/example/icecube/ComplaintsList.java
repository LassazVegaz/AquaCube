package com.example.icecube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintsList extends AppCompatActivity {

    RecyclerView recyclerView;
    ComplaintsAdapter complaintsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ComplaintsModel> options
                = new FirebaseRecyclerOptions.Builder<ComplaintsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Complaints"), ComplaintsModel.class)
                .build();


        complaintsAdapter = new ComplaintsAdapter(options);
        recyclerView.setAdapter(complaintsAdapter);




    }

    @Override
    protected void onStart() {
        super.onStart();
        complaintsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        complaintsAdapter.stopListening();
    }

}

