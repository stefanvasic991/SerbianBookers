package com.easyswitch.serbianbookers.views.calendar;


import android.content.Intent;
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
import com.easyswitch.serbianbookers.views.dialog.PriceSnackBar;
import com.easyswitch.serbianbookers.views.dialog.SnackBarDialog;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: Stefan Vasic
 */
public class TenthRoomFragment extends Fragment {

    @BindView(R.id.rvCalendar)
    RecyclerView rvCalendar;

    List<AvailabilityData> calendarList = new ArrayList<>();
    CalendarAdapter calendarAdapter;
    User u;
    Availability av = new Availability();

    public static TenthRoomFragment newInstance() {
        Bundle args = new Bundle();
        TenthRoomFragment fragment = new TenthRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TenthRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tenth_room, container, false);
        ButterKnife.bind(this, view);

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        assert u != null;
        av.setKey(u.getKey());
        av.setAccount(u.getAccount());
        av.setLcode(u.getProperties().get(0).getLcode());
        av.setDfrom(LocalDate.now().toString());
        av.setDto(LocalDate.now().plusDays(30).toString());
        av.setArr("");

        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
        webApiClient.getAvailability(av).observe(this, new Observer<Availability>() {
            @Override
            public void onChanged(Availability availability) {

                if (availability == null) return;

                for (int i = 0; i < availability.getAvailabilityList().size(); i ++) {

                    if (availability.getAvailabilityList() != null) {
                        calendarList.clear();
                        calendarList.addAll(availability.getAvailabilityList().get(9).getData());
                        calendarAdapter.notifyDataSetChanged();
                    } else {
                        List<AvailabilityData> tmpList = new ArrayList<>();
                        tmpList.addAll(availability.getAvailabilityList().get(9).getData());
                        calendarAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        calendarAdapter = new CalendarAdapter(getActivity(), calendarList);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalendar.setAdapter(calendarAdapter);

        calendarAdapter.setOnPriceClickListener(new CalendarAdapter.OnPriceClickListener() {
            @Override
            public void onPriceClick(View view, int position, AvailabilityData av) {

                Intent i = new Intent(getActivity(), PriceSnackBar.class);
                i.putExtra("currentUser", u);
                i.putExtra("date", av.getDate());
                startActivity(i);
            }
        });

        calendarAdapter.setOnStatusChangeListener(new CalendarAdapter.OnStatusChangeListener() {
            @Override
            public void onStatusChanged(View view, int position, AvailabilityData av) {

                Intent i = new Intent(getActivity(), SnackBarDialog.class);
                i.putExtra("currentUser", u);
                startActivity(i);
            }
        });

        return view;
    }

}
