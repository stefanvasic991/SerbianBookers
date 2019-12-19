package com.easyswitch.serbianbookers.views.calendar;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import com.easyswitch.serbianbookers.models.AvailabilityList;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.dialog.SavePriceDialog;
import com.easyswitch.serbianbookers.views.dialog.SnackBarDialog;


import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

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

        calendarAdapter.setOnPriceClickListener(new CalendarAdapter.OnPriceClickListener() {
            @Override
            public void onPriceClick(View view, int position, AvailabilityData av) {
                view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            Timber.v("menja");
                            Intent i = new Intent(getActivity(), SnackBarDialog.class);
                            i.putExtra("1", "1");
                            i.putExtra("currentUser", u);
//                i.putExtra("date", ad.getDate());
//                i.putExtra("price", holder.tvPrice.getText().toString());
                            startActivity(i);
                        }
                    }
                });
            }
        });

        calendarAdapter.setOnStatusChangeListener(new CalendarAdapter.OnStatusChangeListener() {
            @Override
            public void onStatusChanged(View view, int position, AvailabilityData av) {

                Intent i = new Intent(getActivity(), SnackBarDialog.class);
                i.putExtra("currentUser", u);
                i.putExtra("2", "2");
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
