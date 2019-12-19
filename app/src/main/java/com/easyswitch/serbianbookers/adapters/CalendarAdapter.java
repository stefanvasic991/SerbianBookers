package com.easyswitch.serbianbookers.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.views.dialog.SnackBarDialog;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder> {

    Context context;
    List<AvailabilityData> calendars;
    OnCalendarClickListener onCalendarClickListener;
    OnPriceClickListener onPriceClickListener;
    OnStatusChangeListener onStatusChangeListener;

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dayFormat = new SimpleDateFormat("dd");
    @SuppressLint("SimpleDateFormat")
    private DateFormat monthDateFormat = new SimpleDateFormat("MMM");

    public CalendarAdapter(Context context) {
        this.context = context;
    }

    public CalendarAdapter(Context context, List<AvailabilityData> data) {
        this.context = context;
        this.calendars = data;

        if (data == null) {
            this.calendars = new ArrayList<>();
        }
    }

    private interface OnCalendarClickListener {
        void onCalendarClick(View view, int position, AvailabilityData av);
    }

    public interface OnPriceClickListener {
        void onPriceClick(View view, int position, AvailabilityData av);
    }

    public interface OnStatusChangeListener {
        void onStatusChanged(View view, int position, AvailabilityData av);
    }

    public void setOnCalendarClickListener(OnCalendarClickListener onCalendarClickListener) {
        this.onCalendarClickListener = onCalendarClickListener;
    }

    public void setOnPriceClickListener(OnPriceClickListener onPriceClickListener) {
        this.onPriceClickListener = onPriceClickListener;
    }

    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        this.onStatusChangeListener = onStatusChangeListener;
    }

    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_calendar, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CalendarHolder holder, int position) {

        AvailabilityData ad = calendars.get(position);

        holder.tvDate.setText(getDate(ad.getDate()));
        holder.tvMonth.setText(getMonth(ad.getDate()));

        if (ad.getDay().equals("0")) holder.tvDay.setText("Ned");
        else if (ad.getDay().equals("1")) holder.tvDay.setText("Pon");
        else if (ad.getDay().equals("2")) holder.tvDay.setText("Uto");
        else if (ad.getDay().equals("3")) holder.tvDay.setText("Sre");
        else if (ad.getDay().equals("4")) holder.tvDay.setText("Čet");
        else if (ad.getDay().equals("5")) holder.tvDay.setText("Pet");
        else if (ad.getDay().equals("6")) holder.tvDay.setText("Sub");

        holder.tvPrice.setText(ad.getPrice() + "€");

        if (ad.getClosed() == 0) holder.tvClosure.setText("Zatvoreno");
        else holder.tvClosure.setText("Otvoreno");

        if (ad.getClosedArrival() == 0) holder.tvOnCheckIn.setText("Zatvoreno");
        else holder.tvOnCheckIn.setText("Otvoreno");

        if (ad.getClosedDeparture().equals("0")) holder.tvOnCheckOut.setText("Zatvoreno");
        else holder.tvOnCheckOut.setText("Otvoreno");

        if (ad.getNoOta() == 0) holder.tvOTA.setText("Ne");
        else holder.tvOTA.setText("Da");

        holder.tvMinStay.setText(ad.getMinStay().toString());
        holder.tvMinStayArr.setText(ad.getMinStayArrival().toString());
        holder.tvMaxStay.setText(ad.getMaxStay().toString());

        holder.tvPrice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                Intent i = new Intent(context, SnackBarDialog.class);
//                i.putExtra("1", "1");
//                i.putExtra("date", ad.getDate());
//                i.putExtra("price", holder.tvPrice.getText().toString());
//                context.startActivity(i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return calendars.size();
    }

    private String getDate(String date) {
        try {
            Date d = dateFormat.parse(date);

            return dayFormat.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private String getMonth(String date) {
        try {
            Date d = dateFormat.parse(date);

            return monthDateFormat.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    public class CalendarHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llInfo)
        LinearLayout llInfo;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvMonth)
        TextView tvMonth;
        @BindView(R.id.tvDay)
        TextView tvDay;
        @BindView(R.id.tv1_0)
        TextView tv1_0;
        @BindView(R.id.tvPrice)
        EditText tvPrice;
        @BindView(R.id.mbStatus)
        MaterialButton mbStatus;
        @BindView(R.id.ivClose)
        ImageView ivClose;

        @BindView(R.id.tvClosure)
        TextView tvClosure;
        @BindView(R.id.tvOnCheckIn)
        TextView tvOnCheckIn;
        @BindView(R.id.tvOnCheckOut)
        TextView tvOnCheckOut;
        @BindView(R.id.tvOTA)
        TextView tvOTA;
        @BindView(R.id.tvMinStay)
        TextView tvMinStay;
        @BindView(R.id.tvMinStayArr)
        TextView tvMinStayArr;
        @BindView(R.id.tvMaxStay)
        TextView tvMaxStay;


        public CalendarHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.ivClose)
        public void onCalendarClick() {
            if (onCalendarClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onCalendarClickListener.onCalendarClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }

            if (ivClose.getTag().equals("0")) {
                llInfo.setVisibility(View.VISIBLE);
                ivClose.setImageResource(R.drawable.ic_arrow_up);
                ivClose.setTag("1");
            }
             else if (ivClose.getTag().equals("1")){
                llInfo.setVisibility(View.GONE);
                ivClose.setImageResource(R.drawable.ic_arrow_down);
                ivClose.setTag("0");

            }
        }

        @OnClick(R.id.tvPrice)
        public void onPriceClick() {
            if (onPriceClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onPriceClickListener.onPriceClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
         }

         @OnClick(R.id.mbStatus)
        public void changeStatus() {
             if (onStatusChangeListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                 onStatusChangeListener.onStatusChanged(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
             }

             if (mbStatus.getTag().equals("0")) {
                 mbStatus.getBackground().setColorFilter(context.getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
                 mbStatus.setTag("1");
             } else if (mbStatus.getTag().equals("1")) {
                 mbStatus.getBackground().setColorFilter(context.getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
                 mbStatus.setTag("0");
             }
         }
    }

}
