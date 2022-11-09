package com.example.icecube.adapters.goals;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecube.R;
import com.example.icecube.models.Goal;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class GoalsListAdapter extends FirestoreRecyclerAdapter<Goal, GoalsListAdapter.GoalsListItemViewHolder> {

    public GoalsListAdapter(@NonNull FirestoreRecyclerOptions<Goal> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull GoalsListItemViewHolder holder, int position, @NonNull Goal model) {
        holder.nameTxt.setText(model.name);
        holder.setWaterAmount(model.waterAmount);
        holder.statusTxt.setText("Active");
    }

    @NonNull
    @Override
    public GoalsListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_goal_list_item, parent, false);
        return new GoalsListItemViewHolder(v);
    }


    public static class GoalsListItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, waterAmountTxt, statusTxt;
        ConstraintLayout container;

        public GoalsListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.goal_li_container);
            nameTxt = itemView.findViewById(R.id.goal_li_name);
            waterAmountTxt = itemView.findViewById(R.id.goal_li_water_amount);
            statusTxt = itemView.findViewById(R.id.goal_li_status);
        }

        public void setWaterAmount(float liters) {
            waterAmountTxt.setText(String.format("%s liters per day", liters));
        }

    }

}
