package com.example.icecube;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ComplaintsListAdapter extends ArrayAdapter<ViewComplaints> {


    public ComplaintsListAdapter(Context context, ArrayList<ViewComplaints> viewComplaintsArrayList){

        super(context,R.layout.list_item_complaints,viewComplaintsArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewComplaints viewComplaints = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_complaints,parent,false);

        }


        TextView Name = convertView.findViewById(R.id.Name);
        TextView City = convertView.findViewById(R.id.City);



        Name.setText(viewComplaints.Name);
        City.setText(viewComplaints.City);



        return convertView;
    }
}