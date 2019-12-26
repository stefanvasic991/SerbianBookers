package com.easyswitch.serbianbookers.views.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.easyswitch.serbianbookers.CalendarUnitKt;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.calendar.EighthRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.FifthRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.NinthRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.SecondRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.FirstRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.FourthRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.SeventhRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.SixthRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.TenthRoomFragment;
import com.easyswitch.serbianbookers.views.calendar.ThirdRoomFragment;
import com.easyswitch.serbianbookers.views.filter.CalendarFilterActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by: Stefan Vasic
 */
public class CalendarFragment extends Fragment {

    @BindView(R.id.mbCurrentDate)
    MaterialButton mbCurrentDate;
//    @BindView(R.id.ivList)
//    ImageView ivList;
//    @BindView(R.id.ivGantogram)
//    ImageView ivGantogram;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.cvCalendar)
    CalendarView cvCalendar;

    CalendarPagerAdapter calendarPagerAdapter;
    List<String> calendarList = new ArrayList<>();
    List<FirstRoomFragment> fragmentList = new ArrayList<>();
    User u;
    Availability av = new Availability();

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");
    private DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEE");
    private DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");

    private LocalDate selectedDate = null;
    LocalDate oldDate;

    public static CalendarFragment newInstance() {

        Bundle args = new Bundle();
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CalendarFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);
        AndroidThreeTen.init(getActivity());

        viewPager.setVisibility(View.VISIBLE);

        YearMonth currentMonth = YearMonth.now();
        YearMonth lastMonth = currentMonth.plusMonths(12);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date(System.currentTimeMillis());
        mbCurrentDate.setText(formatter.format(date));


        calendarPagerAdapter = new CalendarPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(calendarPagerAdapter);
        viewPager.setOffscreenPageLimit(calendarList.size());

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        assert u != null;
        av.setKey(u.getKey());
        av.setAccount(u.getAccount());
        av.setLcode(u.getProperties().get(0).getLcode());
        av.setDfrom(LocalDate.now().toString());
        av.setDto(LocalDate.now().plusDays(30).toString());
        av.setArr("");

        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
        webApiClient.getAvailability(av).observe(this, availability -> {

            if (availability == null) return;

            for(int i = 0; i < availability.getAvailabilityList().size(); i++){
                addTab(availability.getAvailabilityList().get(i).getShortName());
            }
        });

        tabLayout.setupWithViewPager(viewPager);

//        gantogram();

        return view;
    }

    private void addTab(String title) {
        tabLayout.addTab(tabLayout.newTab().setText(title));
        addTabPage(title);
    }

    public void addTabPage(String title) {
        calendarList.add(title);
        calendarPagerAdapter.notifyDataSetChanged();
    }

    class CalendarPagerAdapter extends FragmentPagerAdapter {


        public CalendarPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NotNull
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FirstRoomFragment.newInstance();
                case 1:
                    return SecondRoomFragment.newInstance();
                case 2:
                    return ThirdRoomFragment.newInstance();
                case 3:
                    return FourthRoomFragment.newInstance();
                case 4:
                    return FifthRoomFragment.newInstance();
                case 5:
                    return SixthRoomFragment.newInstance();
                case 6:
                    return SeventhRoomFragment.newInstance();
                case 7:
                    return EighthRoomFragment.newInstance();
                case 8:
                    return NinthRoomFragment.newInstance();
                case 9:
                    return TenthRoomFragment.newInstance();
                default:
                    return FirstRoomFragment.newInstance();
            }

        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return calendarList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return calendarList.get(position);
        }

        public void addFlag(FirstRoomFragment jednosobniFragment) {
            fragmentList.add(jednosobniFragment);
        }
    }

    public void gantogram() {
        YearMonth currentMonth = YearMonth.now();

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        cvCalendar.setDayWidth(dm.widthPixels/5);
        cvCalendar.setDayHeight((int) (cvCalendar.getDayWidth() *1.25));

        cvCalendar.setup(currentMonth, currentMonth.plusMonths(3), CalendarUnitKt.daysOfWeekFromLocale()[0]);
        cvCalendar.scrollToDate(LocalDate.now());
        cvCalendar.setDayBinder(new DayBinder<DayViewContainer>() {
            @NotNull
            @Override
            public DayViewContainer create(@NotNull View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(@NotNull DayViewContainer container, @NotNull CalendarDay day) {
                container.calendarDay = day;
                container.tvMonthText.setText(monthFormatter.format(day.getDate()));
                container.tvDateText.setText(dateFormatter.format(day.getDate()));
                container.tvDayText.setText(dayFormatter.format(day.getDate()));

                if (day.getDate().isEqual(LocalDate.now())) {
                    container.tvMonthText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlue));
                    container.tvDateText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlue));
                    container.tvDayText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlue));
                } else if (day.getOwner() != DayOwner.THIS_MONTH) {
                    container.tvMonthText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText));
                    container.tvDateText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText));
                    container.tvDayText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText));
                }  else {
                    container.tvMonthText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextLight));
                    container.tvDateText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextLight));
                    container.tvDayText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextLight));
                }
            }
        });
    }

    public class DayViewContainer extends ViewContainer {
        FrameLayout tvCalendarDayText;
        TextView tvMonthText, tvDateText, tvDayText;
        CalendarDay calendarDay;

        public DayViewContainer(@NotNull View view) {
            super(view);

            tvCalendarDayText = view.findViewById(R.id.tvCalendarDayText);
            tvMonthText = view.findViewById(R.id.tvMonthText);
            tvDateText = view.findViewById(R.id.tvDateText);
            tvDayText = view.findViewById(R.id.tvDayText);

            tvCalendarDayText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CalendarDay firstDay = cvCalendar.findFirstVisibleDay();
                    CalendarDay lastDay = cvCalendar.findLastVisibleDay();
                    if (firstDay == calendarDay) {
                        cvCalendar.smoothScrollToDate(calendarDay.getDate());
                    } else if (lastDay == calendarDay) {
                        cvCalendar.smoothScrollToDate(calendarDay.getDate().minusDays(4));
                    }

                    if (selectedDate != calendarDay.getDate()) {
                        oldDate = selectedDate;
                        selectedDate = calendarDay.getDate();
                        cvCalendar.notifyDateChanged(calendarDay.getDate());
                    }
                }
            });
        }
    }

//    @OnClick( R.id.ivList)
//    public void openList() {
//        ivList.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_list_selected));
//        ivGantogram.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_gantogram_shape));
//        cvCalendar.setVisibility(View.GONE);
//        viewPager.setVisibility(View.VISIBLE);
//    }
//
//    @OnClick( R.id.ivGantogram)
//    public void openGantogram() {
//
//        ivList.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_list_shape));
//        ivGantogram.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_gantogram_selected));
//        cvCalendar.setVisibility(View.VISIBLE);
//        viewPager.setVisibility(View.GONE);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String date = data.getStringExtra("data");

        if (requestCode == Consts.REQ_FILTER_DIALOG) {
            if (resultCode == RESULT_OK) {
                mbCurrentDate.setText(date);

                Intent sendDate = new Intent();
                sendDate.setAction("sendDateToChild");
                sendDate.putExtra("date", date);
                getActivity().sendBroadcast(sendDate);
            }

        }
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }

    @OnClick(R.id.mbCurrentDate)
    public void changeCalendarDate() {
        Intent intent = new Intent(getActivity(), CalendarFilterActivity.class);
        startActivityForResult(intent, Consts.REQ_FILTER_DIALOG);
    }
}

