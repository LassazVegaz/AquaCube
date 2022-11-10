package com.example.icecube.adapters.goals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecube.R;
import com.example.icecube.models.Reminder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RemindersAdapter extends FirestoreRecyclerAdapter<Reminder, RemindersAdapter.ReminderListItemViewHolder> {
    final OnListItemClickListener onListItemClickListener;

    public RemindersAdapter(@NonNull FirestoreRecyclerOptions<Reminder> options, OnListItemClickListener onListItemClickListener) {
        super(options);
        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ReminderListItemViewHolder holder, int position, @NonNull Reminder model) {
        holder.setText(model.noOfCups, model.time);
        holder.tv.setOnClickListener(v -> onListItemClickListener.onClick(v, model.id));
    }

    @NonNull
    @Override
    public ReminderListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reminder_list_item, parent, false);
        return new ReminderListItemViewHolder(v);
    }

    static class ReminderListItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView tv;

        public ReminderListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.rem_li_text);
        }

        public void setText(int noOfCups, String time) {
            tv.setText(noOfCups + " Cups at " + time);
        }
    }

}
