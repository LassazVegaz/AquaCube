package com.example.icecube.activities.hiruni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.icecube.R;

import java.util.ArrayList;

public class RelavantPartiesAdapter extends ArrayAdapter<RelavantParties> {


    public RelavantPartiesAdapter(Context context, ArrayList<RelavantParties> relavantPartiesArrayList){

        super(context, R.layout.list_item_relavant_parties,relavantPartiesArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        RelavantParties relavantParties = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_relavant_parties,parent,false);

        }


        TextView Name = convertView.findViewById(R.id.Name);
        TextView City = convertView.findViewById(R.id.City);
        TextView Time = convertView.findViewById(R.id.Time);


        Name.setText(relavantParties.Name);
        City.setText(relavantParties.City);
        Time.setText(relavantParties.Time);


        return convertView;
    }
}