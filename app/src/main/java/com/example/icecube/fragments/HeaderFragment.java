package com.example.icecube.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.icecube.R;

public class HeaderFragment extends Fragment {
    final static String PARAM_TEXT = "text";

    CharSequence mText;
    TextView tv;

    public static HeaderFragment newInstance(CharSequence text) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();
        args.putCharSequence(PARAM_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FragmentArguments);
        mText = a.getText(R.styleable.FragmentArguments_android_text);
        a.recycle();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mText = getArguments().getCharSequence(PARAM_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_header, container, false);
        tv = v.findViewById(R.id.header_text);
        tv.setText(mText == null ? "" : mText);

        v.findViewById(R.id.header_backIcon).setOnClickListener(this::onBackButtonClick);

        return v;
    }


    // events
    void onBackButtonClick(View v) {
        getActivity().onBackPressed();
    }


    // utils
    public void setTitle(String title) {
        tv.setText(title);
    }
}