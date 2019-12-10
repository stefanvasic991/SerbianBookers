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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.easyswitch.serbianbookers.CalendarUnitKt;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.adapters.RoomViewPagerAdapter;
import com.easyswitch.serbianbookers.views.GantogramActivity;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by: Stefan Vasic
 */
public class CalendarFragment extends Fragment {

    @BindView(R.id.mbCurrentDate)
    MaterialButton mbCurrentDate;
    @BindView(R.id.ivList)
    ImageView ivList;
    @BindView(R.id.ivGantogram)
    ImageView ivGantogram;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.cvCalendar)
    CalendarView cvCalendar;

    RoomViewPagerAdapter adapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);
        AndroidThreeTen.init(getActivity());

        viewPager.setVisibility(View.VISIBLE);
        ivList.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_list_selected));

        YearMonth currentMonth = YearMonth.now();
        YearMonth lastMonth = currentMonth.plusMonths(12);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date(System.currentTimeMillis());
        mbCurrentDate.setText(formatter.format(date));

        adapter = new RoomViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

        gantogram();

        return view;
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

    @OnClick( R.id.ivList)
    public void openList() {
        ivList.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_list_selected));
        ivGantogram.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_gantogram_shape));
        cvCalendar.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
    }

    @OnClick( R.id.ivGantogram)
    public void openGantogram() {
//        Intent i = new Intent(getActivity(), GantogramActivity.class);
//        startActivity(i);

        ivList.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_list_shape));
        ivGantogram.setBackgroundDrawable(getResources().getDrawable(R.drawable.diagram_gantogram_selected));
        cvCalendar.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.GONE);
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }
}
