package com.example.icecube.fragments.goals;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.icecube.R;

import java.util.ArrayList;
import java.util.List;

public class DaySelectorFragment extends Fragment {

    int onColor, offColor, disabledColor;
    TextView[] tvs = new TextView[7];

    public DaySelectorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // store args in vars
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_selector, container, false);

        onColor = getResources().getColor(R.color.day_selector_selected);
        offColor = getResources().getColor(R.color.day_selector_unselected);
        disabledColor = getResources().getColor(R.color.day_selector_disabled);

        tvs[0] = view.findViewById(R.id.day_selector_mo);
        tvs[1] = view.findViewById(R.id.day_selector_tu);
        tvs[2] = view.findViewById(R.id.day_selector_we);
        tvs[3] = view.findViewById(R.id.day_selector_th);
        tvs[4] = view.findViewById(R.id.day_selector_fr);
        tvs[5] = view.findViewById(R.id.day_selector_sa);
        tvs[6] = view.findViewById(R.id.day_selector_su);

        for (TextView tv : tvs)
            tv.setOnClickListener(this::onDayButtonClick);

        return view;
    }


    // events
    void onDayButtonClick(@NonNull View v) {
        ColorDrawable bg = (ColorDrawable) v.getBackground();
        if (bg.getColor() == disabledColor) return;
        v.setBackgroundColor(bg.getColor() == onColor ? offColor : onColor);
    }


    // utils
    public List<Boolean> getDays() {
        List<Boolean> ls = new ArrayList<>();

        for (TextView tv : tvs) {
            ColorDrawable bg = (ColorDrawable) tv.getBackground();
            ls.add(bg.getColor() == onColor);
        }

        return ls;
    }

    public void setDays(@NonNull List<Boolean> days) {
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i))
                tvs[i].setBackgroundColor(onColor);
        }
    }

    public void setDisabledDays(@NonNull List<Boolean> disabledDays) {
        for (int i = 0; i < disabledDays.size(); i++) {
            if (!disabledDays.get(i))
                tvs[i].setBackgroundColor(offColor);
        }
    }
}