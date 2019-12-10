package com.easyswitch.serbianbookers.views.calendar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyswitch.serbianbookers.R;

/**
 * Created by: Stefan Vasic
 */
public class StudioFragment extends Fragment {

    public static StudioFragment newInstance() {

        Bundle args = new Bundle();

        StudioFragment fragment = new StudioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public StudioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_studio, container, false);
    }

}
