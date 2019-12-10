package com.easyswitch.serbianbookers.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyswitch.serbianbookers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GantogramFragment extends Fragment {

    public static GantogramFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GantogramFragment fragment = new GantogramFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public GantogramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gantogram, container, false);
    }

}
