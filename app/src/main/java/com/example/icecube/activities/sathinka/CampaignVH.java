package com.example.icecube.activities.sathinka;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecube.R;

public class CampaignVH extends RecyclerView.ViewHolder
{
    public TextView txt_name,txt_position,txt_area,txt_option;
    public CampaignVH(@NonNull View itemView)
    {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_position = itemView.findViewById(R.id.txt_position);
        txt_area = itemView.findViewById(R.id.txt_area);

        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
