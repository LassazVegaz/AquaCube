package com.example.icecube.fragments.goals;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icecube.R;

public class DaySelectorFragment extends Fragment {

    int onColor, offColor;

    public DaySelectorFragment() {
    }

    public static DaySelectorFragment newInstance() {
        DaySelectorFragment fragment = new DaySelectorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // store args in vars
        }
        onColor = getResources().getColor(R.color.day_selector_selected);
        offColor = getResources().getColor(R.color.day_selector_unselected);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_selector, container, false);

        view.findViewById(R.id.day_selector_mo).setOnClickListener(this::onDayButtonClick);
        view.findViewById(R.id.day_selector_tu).setOnClickListener(this::onDayButtonClick);
        view.findViewById(R.id.day_selector_we).setOnClickListener(this::onDayButtonClick);
        view.findViewById(R.id.day_selector_th).setOnClickListener(this::onDayButtonClick);
        view.findViewById(R.id.day_selector_fr).setOnClickListener(this::onDayButtonClick);
        view.findViewById(R.id.day_selector_sa).setOnClickListener(this::onDayButtonClick);
        view.findViewById(R.id.day_selector_su).setOnClickListener(this::onDayButtonClick);

        return view;
    }


    // events
    void onDayButtonClick(View v) {
        ColorDrawable bg = (ColorDrawable) v.getBackground();
        if (bg.getColor() == onColor) v.setBackgroundColor(offColor);
        else v.setBackgroundColor(onColor);
    }
}