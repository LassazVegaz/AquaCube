package com.example.icecube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ComplaintsAdapter extends FirebaseRecyclerAdapter<ComplaintsModel,ComplaintsAdapter.myViewHolder>{


    public ComplaintsAdapter(@NonNull FirebaseRecyclerOptions<ComplaintsModel>options){
        super(options);

    }
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ComplaintsModel model) {
             holder.Area.setText(model.getArea());
             holder.Date.setText(model.getDate());
             holder.Issue.setText(model.getIssue());






    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaints_item,parent,false);
        return null;
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView Area,Date,Issue;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            Area = (TextView) itemView.findViewById(R.id.area);
            Date =(TextView) itemView.findViewById(R.id.date);
            Issue=(TextView)itemView.findViewById(R.id.issue);




        }
    }
}
