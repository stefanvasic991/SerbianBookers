package com.easyswitch.serbianbookers.views.filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.easyswitch.serbianbookers.R;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.WeekFields;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.Unit;

public class CalendarFilterActivity extends AppCompatActivity {

    @BindView(R.id.tvCurrentDate)
    TextView tvCurrentDate;
    @BindView(R.id.cvCalendarView)
    CalendarView cvCalendarView;
    @BindView(R.id.tvMonthText)
    TextView tvMonthText;

    String tmp;
//    ArrayList<String> tmp = new ArrayList<>();
//    Set<LocalDate> selectedDays = new HashSet<>();

    LocalDate selectedDays = null;
    LocalDate today = LocalDate.now();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_filter);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        YearMonth currentMonth = YearMonth.now();
        YearMonth lastMonth = currentMonth.plusMonths(12);
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        cvCalendarView.setup(currentMonth, lastMonth, firstDayOfWeek);
        cvCalendarView.scrollToMonth(currentMonth);


        cvCalendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @NotNull
            @Override
            public DayViewContainer create(@NotNull View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(@NotNull DayViewContainer container, @NotNull CalendarDay day) {
                container.calendarDay = day;
                container.tvDayText.setText(String.valueOf(day.getDate().getDayOfMonth()));

                if (day.getOwner() == DayOwner.THIS_MONTH) {
                    container.tvDayText.setVisibility(View.VISIBLE);
                    if (container.calendarDay.getDate().isEqual(LocalDate.now())) {
//                        container.tvDayText.setBackground(ContextCompat.getDrawable(CalendarFilterActivity.this, R.drawable.circle_background_blue));
                        container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorBlue));
                    }
                }

                if (selectedDays == day.getDate()) {
                    container.tvDayText.setBackground(ContextCompat.getDrawable(CalendarFilterActivity.this, R.drawable.circle_background_blue));
                    container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorWhite));
                }

//                if (selectedDays.contains(day.getDate())) {
//                    container.tvDayText.setBackground(ContextCompat.getDrawable(CalendarFilterActivity.this, R.drawable.circle_background_blue));
//                } else {
//                    container.tvDayText.setBackground(null);
//                }
//
//                if (selectedDays.contains(day.getDate())) {
//                    container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorWhite));
//                } else
//                    if (day.getDate().isEqual(LocalDate.now())) {
//                    container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorBlue));
//                } else
                    if (day.getOwner() != DayOwner.THIS_MONTH) {
                    container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorText));
                } else if (day.getDate().isAfter(LocalDate.now())) {
                    container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorBlack));
                } else {
                    container.tvDayText.setTextColor(ContextCompat.getColor(CalendarFilterActivity.this, R.color.colorTextLight));
                }
            }
        });

        cvCalendarView.setMonthScrollListener(calendarMonth -> {
            tvMonthText.setText(calendarMonth.getYearMonth().format(DateTimeFormatter.ofPattern("MMMM"))
                    + " " + calendarMonth.getYear());

            return Unit.INSTANCE;
        });
    }

    public class DayViewContainer extends ViewContainer {

        @BindView(R.id.tvCalendarFilterDay)
        TextView tvDayText;
        CalendarDay calendarDay;

        public DayViewContainer(@NotNull View view) {
            super(view);
            ButterKnife.bind(this, view);

            tvDayText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((calendarDay.getDate().isAfter(LocalDate.now()) || calendarDay.getDate().isEqual(LocalDate.now())) &&
                            calendarDay.getOwner() == DayOwner.THIS_MONTH) {
//                        if (selectedDays.contains(calendarDay.getDate()))
//                            selectedDays.remove(calendarDay.getDate());
//                        else
//                            selectedDays.add(calendarDay.getDate());
//
//
//                        cvCalendarView.notifyDayChanged(calendarDay);
//                    }
//                    if (calendarDay.getOwner() == DayOwner.THIS_MONTH) {
                        if (selectedDays == calendarDay.getDate()) {
                            selectedDays = null;
                            cvCalendarView.notifyDayChanged(calendarDay);
                        } else {
                            LocalDate oldDate = selectedDays;
                            selectedDays = calendarDay.getDate();
                            cvCalendarView.notifyDateChanged(calendarDay.getDate());
                        }
//                    }

                        LocalDate localDate = selectedDays;
                        tmp = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));

                        Intent sendData = new Intent();
                        sendData.putExtra("data", tmp);
                        setResult(RESULT_OK, sendData);
                        finish();
                    }
                }
            });
        }
    }

    public String getSelectedDays() {

        LocalDate localDate = selectedDays;
        tmp = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));

        Intent sendData = new Intent();
        sendData.putExtra("data", tmp);
        setResult(RESULT_OK, sendData);

        return tmp;
    }

    @OnClick(R.id.tvCancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.tvConfirmDate)
    public void confirmDate() {
        setResult(RESULT_OK);
//        getSelectedDays();
        finish();
    }
}
