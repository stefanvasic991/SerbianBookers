package com.easyswitch.serbianbookers.views.calendar;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
@RequiresApi(api = Build.VERSION_CODES.O)
public class JednosobniFragment extends Fragment {

    @BindView(R.id.rvCalendar)
    RecyclerView rvCalendar;

    List<AvailabilityData> calendarList = new ArrayList<>();
    CalendarAdapter calendarAdapter;
    User u;

    public static JednosobniFragment newInstance() {
        
        Bundle args = new Bundle();
        
        JednosobniFragment fragment = new JednosobniFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public JednosobniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jednosobni, container, false);
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
                    calendarList.addAll(availability.getAvailabilityList().get_288967());
                    calendarAdapter.notifyDataSetChanged();
                } else {
                        List<AvailabilityData> tmpList = new ArrayList<>();
                        tmpList.addAll(availability.getAvailabilityList().get_288967());
                }
            }
        });

        calendarAdapter = new CalendarAdapter(getActivity(), calendarList);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalendar.setAdapter(calendarAdapter);

        calendarAdapter.setOnCalendarClickListener(new CalendarAdapter.OnCalendarClickListener() {
            @Override
            public void onCalendarClick(View view, int position, AvailabilityData av) {
                
            }
        });

        return view;
    }
}
