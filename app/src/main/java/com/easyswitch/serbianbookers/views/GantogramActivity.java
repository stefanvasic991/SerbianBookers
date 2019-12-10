package com.easyswitch.serbianbookers.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.easyswitch.serbianbookers.CalendarUnitKt;
import com.easyswitch.serbianbookers.R;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.model.ScrollMode;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;


import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.Unit;

public class GantogramActivity extends AppCompatActivity {

    @BindView(R.id.cvCalendar)
    CalendarView cvCalendar;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");
    private DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEE");
    private DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM");

    private LocalDate selectedDate = null;
    LocalDate oldDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gantogram);
        ButterKnife.bind(this);
        AndroidThreeTen.init(this);

        YearMonth currentMonth = YearMonth.now();

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
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

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void bind(@NotNull DayViewContainer container, @NotNull CalendarDay day) {
                container.calendarDay = day;
                container.tvMonthText.setText(monthFormatter.format(day.getDate()));
                container.tvDateText.setText(dateFormatter.format(day.getDate()));
                container.tvDayText.setText(dayFormatter.format(day.getDate()));

                if (day.getDate().isEqual(LocalDate.now())) {
                    container.tvMonthText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorBlue));
                    container.tvDateText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorBlue));
                    container.tvDayText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorBlue));
                } else if (day.getOwner() != DayOwner.THIS_MONTH) {
                    container.tvMonthText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorText));
                    container.tvDateText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorText));
                    container.tvDayText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorText));
                }  else {
                    container.tvMonthText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorTextLight));
                    container.tvDateText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorTextLight));
                    container.tvDayText.setTextColor(ContextCompat.getColor(GantogramActivity.this, R.color.colorTextLight));
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
    public void openGantogram() {
        finish();
    }
}
