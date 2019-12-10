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
public class TrosobniFragment extends Fragment {

    public static TrosobniFragment newInstance() {

        Bundle args = new Bundle();

        TrosobniFragment fragment = new TrosobniFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public TrosobniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trosobni, container, false);
    }

}
