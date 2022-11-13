package com.example.icecube.activities.hiruni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.icecube.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComplaintsList extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<ComplaintsModel> recycleList;




    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recycleList = new ArrayList<>();

       firebaseDatabase= FirebaseDatabase.getInstance();
        ComplaintsAdapter recyclerAdapter = new ComplaintsAdapter(recycleList,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);

        firebaseDatabase.getReference().child("Complaints").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    ComplaintsModel complaintsModel = dataSnapshot.getValue(ComplaintsModel.class);
                    recycleList.add(complaintsModel);

            }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}

