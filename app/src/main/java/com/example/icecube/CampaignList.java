package com.example.icecube;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CampaignList extends ArrayAdapter<CampaignOrg> {
    private Activity context;
    private List<CampaignOrg>artistList;

    public CampaignList(Activity context, List<CampaignOrg> artistList) {
        super(context, R.layout.list_layout, artistList);
        this.context = context;
        this.artistList = artistList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        CampaignOrg artist = artistList.get(position);
        textViewName.setText(artist.getLocation());
        textViewGenre.setText(artist.getProgramType());

        return listViewItem;
    }
}
