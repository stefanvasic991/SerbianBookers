package com.easyswitch.serbianbookers.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.RestrictionData;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder> {

    Context context;
    ArrayList<RestrictionData> calendars;
    OnCalendarClickListener onCalendarClickListener;

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dayFormat = new SimpleDateFormat("dd");
    @SuppressLint("SimpleDateFormat")
    private DateFormat monthDateFormat = new SimpleDateFormat("MMM");

    public CalendarAdapter(Context context) {
        this.context = context;
    }

    public CalendarAdapter(Context context, ArrayList<RestrictionData> data) {
        this.context = context;
        this.calendars = data;

        if (data == null) {
            this.calendars = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarHolder holder, int position) {

        RestrictionData restrictionData = calendars.get(position);

        holder.tvDate.setText(restrictionData.getDate());
//        holder.tvMonth.setText(getMonthFromDate(restrictionData.getDate()));
    }

    @Override
    public int getItemCount() {
        return calendars.size();
    }

    public interface OnCalendarClickListener {
        void onCalendarClick(View view, int position, RestrictionData restrictionData);
    }

    public void setOnCalendarClickListener(OnCalendarClickListener onCalendarClickListener) {
        this.onCalendarClickListener = onCalendarClickListener;
    }

    public class CalendarHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llInfo)
        LinearLayout llInfo;
        @BindView(R.id.tvDate)
        TextView tvDate;
//        @BindView(R.id.tvMonth)
//        TextView tvMonth;
//        @BindView(R.id.tvDay)
//        TextView tvDay;
        @BindView(R.id.tv1_0)
        TextView tv1_0;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.mbStatus)
        MaterialButton mbStatus;

        boolean isOpen = false;

        public CalendarHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.rlHeather)
        public void onCalendarClick() {
            if (onCalendarClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onCalendarClickListener.onCalendarClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }

        }

        @OnClick(R.id.ivClose)
        public void ggg() {
            llInfo.setVisibility(View.GONE);
        }
    }

    private String getDayFromDate(String date){
//        try {
//            Date d = dateFormat.parse(date);
//
//            return dayFormat.format(d);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        Date d;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return new SimpleDateFormat("dd").format(d);
    }

    private String getMonthFromDate(String date){
        try {
            Date d = dateFormat.parse(date);

            return monthDateFormat.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

}
