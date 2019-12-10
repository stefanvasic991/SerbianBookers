package com.easyswitch.serbianbookers.views.calendar;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.CalendarAdapter;
import com.easyswitch.serbianbookers.models.Restriction;
import com.easyswitch.serbianbookers.models.RestrictionData;


import org.threeten.bp.LocalDate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: Stefan Vasic
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class JednosobniFragment extends Fragment {

    @BindView(R.id.rvCalendar)
    RecyclerView rvCalendar;
//    @BindView(R.id.cvCalendar)
//    CalendarView cvCalendar;

//    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");
//    private DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEE");
//    private DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");

    ArrayList<RestrictionData> calendarList = new ArrayList<>();
    CalendarAdapter calendarAdapter;

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

//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = LocalDate.of(2019, 12, 29);
//
//        Stream.iterate(startDate, date -> date.plusDays(1))
//                .limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
//                .forEach(System.out::println);
//
//        try {
//            @SuppressLint("SimpleDateFormat")
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date startDate = formatter.parse("2019-11-26");
//            Date endDate = formatter.parse("2019-12-26");
//
//            Calendar start = Calendar.getInstance();
//            start.setTime(startDate);
//            Calendar end = Calendar.getInstance();
//            end.setTime(endDate);
//
//            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
//                // Do your job here with `date`.
//                calendarList.add(date);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Restriction r = new Restriction();
        r.setKey(App.getInstance().getCurrentUser().getKey());
        r.setAccount(App.getInstance().getCurrentUser().getAccount());
        r.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        r.setDfrom(LocalDate.now().toString());
        r.setDto(LocalDate.now().plusDays(30).toString());
        r.setId("1");

        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
        webApiClient.getRestrictions(r).observe(this, new Observer<Restriction>() {
            @Override
            public void onChanged(Restriction restriction) {

                if (restriction == null) return;

                if (restriction.getRestrictions() != null) {
                    calendarList.clear();
                    calendarList.addAll(restriction.getRestrictions().get_245025());
                    calendarAdapter.notifyDataSetChanged();
                } else {
                    ArrayList<RestrictionData> tmpList = new ArrayList<>();
                    tmpList.addAll(restriction.getRestrictions().get_245025());
                }
            }
        });
//        calendarList.add(new RestrictionData());
//        calendarList.add(new RestrictionData());
//        calendarList.add(new RestrictionData());

        calendarAdapter = new CalendarAdapter(getActivity(), calendarList);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalendar.setAdapter(calendarAdapter);

        calendarAdapter.setOnCalendarClickListener(new CalendarAdapter.OnCalendarClickListener() {
            @Override
            public void onCalendarClick(View view, int position, RestrictionData restrictionData) {
//                Intent intent = new Intent(getActivity(), CalendarDescriptionActivity.class);
////            intent.putExtra("calendar", calendar);
//                startActivity(intent);
            }
        });


        return view;
    }

//    public class DayViewContainer extends ViewContainer {
//        RelativeLayout tvCalendarDayText;
//        TextView tvMonthText, tvDateText, tvDayText;
//        CalendarDay calendarDay;
//
//        public DayViewContainer(@NotNull View view) {
//            super(view);
//
//            tvCalendarDayText = view.findViewById(R.id.rlHeather);
//            tvMonthText = view.findViewById(R.id.tvMonth);
//            tvDateText = view.findViewById(R.id.tvDate);
//            tvDayText = view.findViewById(R.id.tvDay);
//
//            tvCalendarDayText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    CalendarDay firstDay = cvCalendar.findFirstVisibleDay();
//                    CalendarDay lastDay = cvCalendar.findLastVisibleDay();
//                    if (firstDay == calendarDay) {
//                        cvCalendar.smoothScrollToDate(calendarDay.getDate());
//                    } else if (lastDay == calendarDay) {
//                        cvCalendar.smoothScrollToDate(calendarDay.getDate().minusDays(4));
//                    }
//
//                    if (selectedDate != calendarDay.getDate()) {
//                        oldDate = selectedDate;
//                        selectedDate = calendarDay.getDate();
//                        cvCalendar.notifyDateChanged(calendarDay.getDate());
//                    }
//                }
//            });
//        }
//    }
}
