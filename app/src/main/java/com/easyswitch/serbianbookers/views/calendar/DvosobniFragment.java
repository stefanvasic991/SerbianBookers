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
public class DvosobniFragment extends Fragment {

    public static DvosobniFragment newInstance() {

        Bundle args = new Bundle();

        DvosobniFragment fragment = new DvosobniFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DvosobniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dvosobni, container, false);
    }

}
