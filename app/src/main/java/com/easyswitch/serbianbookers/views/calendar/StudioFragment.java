package com.easyswitch.serbianbookers.views.calendar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.CalendarAdapter;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.User;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: Stefan Vasic
 */
public class StudioFragment extends Fragment {

    @BindView(R.id.rvCalendar)
    RecyclerView rvCalendar;

    List<AvailabilityData> calendarList = new ArrayList<>();
    CalendarAdapter calendarAdapter;
    User u;

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
        View view = inflater.inflate(R.layout.fragment_studio, container, false);
        ButterKnife.bind(this, view);

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        Availability av = new Availability();
        av.setKey(u.getKey());
        av.setAccount(u.getAccount());
        av.setLcode(u.getProperties().get(0).getLcode());
        av.setDfrom(LocalDate.now().toString());
        av.setDto(LocalDate.now().plusDays(30).toString());

        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
        webApiClient.getAvailability(av).observe(this, new Observer<Availability>() {
            @Override
            public void onChanged(Availability availability) {

                if (availability == null) return;

                if (availability.getAvailabilityList() != null) {
                    calendarList.clear();
                    calendarList.addAll(availability.getAvailabilityList().get_387450());
                    calendarAdapter.notifyDataSetChanged();
                } else {
                    List<AvailabilityData> tmpList = new ArrayList<>();
                    tmpList.addAll(availability.getAvailabilityList().get_387450());
                }
            }
        });

        calendarAdapter = new CalendarAdapter(getActivity(), calendarList);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalendar.setAdapter(calendarAdapter);

        return view;
    }

}
