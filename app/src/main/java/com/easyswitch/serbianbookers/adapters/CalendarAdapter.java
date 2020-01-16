package com.easyswitch.serbianbookers.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.AvailabilityData;
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

    private Context context;
    private List<AvailabilityData> calendars;
    private OnCalendarClickListener onCalendarClickListener;
    private OnPriceClickListener onPriceClickListener;
    private OnStatusChangeListener onStatusChangeListener;
    private OnOpenClosureChangeListener onOpenClosureChangeListener;
    private OnCheckInListener onCheckInListener;
    private OnCheckOutListener onCheckOutListener;
    private OtaClickListener otaClickListener;
    private MinStayClickLitener minStayClickLitener;
    private MinStayArrClickLitener minStayArrClickLitener;
    private MaxStayClickLitener maxStayClickLitener;
    private OnAvailClickListener onAvailClickListener;

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

    public interface OnCalendarClickListener {
        void onCalendarClick(View view, int position, AvailabilityData av);
    }

    public interface OnAvailClickListener {
        void onAvailClick(View view, int position, AvailabilityData av);
    }

    public interface OnPriceClickListener {
        void onPriceClick(View view, int position, AvailabilityData av);
    }

    public interface OnStatusChangeListener {
        void onStatusChanged(View view, int position, AvailabilityData av);
    }

    public interface OnOpenClosureChangeListener {
        void onOpenClosureChanged(View view, int position, AvailabilityData av);
    }

    public interface OnCheckInListener {
        void onCheckIn(View view, int position, AvailabilityData av);
    }

    public interface OnCheckOutListener {
        void onCheckOut(View view, int position, AvailabilityData av);
    }

    public interface OtaClickListener{
        void otaClick(View view, int position, AvailabilityData av);
    }

    public interface MinStayClickLitener {
        void minStayClick(View view, int position, AvailabilityData av);
    }

    public interface MinStayArrClickLitener {
        void minStayArrClick(View view, int position, AvailabilityData av);
    }

    public interface MaxStayClickLitener {
        void maxStayClick(View view, int position, AvailabilityData av);
    }

    public void setOnCalendarClickListener(OnCalendarClickListener onCalendarClickListener) {
        this.onCalendarClickListener = onCalendarClickListener;
    }

    public void setOnAvailClickListener(OnAvailClickListener onAvailClickListener) {
        this.onAvailClickListener = onAvailClickListener;
    }

    public void setOnPriceClickListener(OnPriceClickListener onPriceClickListener) {
        this.onPriceClickListener = onPriceClickListener;
    }

    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        this.onStatusChangeListener = onStatusChangeListener;
    }

    public void setOnOpenClosureChangeListener(OnOpenClosureChangeListener onOpenClosureChangeListener) {
        this.onOpenClosureChangeListener = onOpenClosureChangeListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setOnCheckInListener(OnCheckInListener onCheckInListener) {
        this.onCheckInListener = onCheckInListener;
    }

    public void setOnCheckOutListener(OnCheckOutListener onCheckOutListener) {
        this.onCheckOutListener = onCheckOutListener;
    }

    public void setOtaClickListener(OtaClickListener otaClickListener) {
        this.otaClickListener = otaClickListener;
    }

    public void setMinStayClickLitener(MinStayClickLitener minStayClickLitener) {
        this.minStayClickLitener = minStayClickLitener;
    }

    public void setMinStayArrClickLitener(MinStayArrClickLitener minStayArrClickLitener) {
        this.minStayArrClickLitener = minStayArrClickLitener;
    }

    public void setMaxStayClickLitener(MaxStayClickLitener maxStayClickLitener) {
        this.maxStayClickLitener = maxStayClickLitener;
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
        holder.tv1_0.setText(ad.getBooked().toString() + "/");
        holder.tvNoAvail.setText(ad.getAvail().toString());
        holder.etNoAvail.setText(ad.getAvail().toString());

        if (ad.getDay().equals("0")) holder.tvDay.setText("Ned");
        else if (ad.getDay().equals("1")) holder.tvDay.setText("Pon");
        else if (ad.getDay().equals("2")) holder.tvDay.setText("Uto");
        else if (ad.getDay().equals("3")) holder.tvDay.setText("Sre");
        else if (ad.getDay().equals("4")) holder.tvDay.setText("Čet");
        else if (ad.getDay().equals("5")) holder.tvDay.setText("Pet");
        else if (ad.getDay().equals("6")) holder.tvDay.setText("Sub");

//        double pp = ad.getPricingPlan();
//        int pricingPlan  = (int) pp;
//        holder.tvPrice.setText(String.valueOf(pricingPlan));
//        holder.etPrice.setText(String.valueOf(pricingPlan));

        holder.tvPrice.setText(ad.getPrice().toString());
        holder.etPrice.setText(ad.getPrice().toString());

        if (ad.getClosed() == 0) {
            holder.tvClosure.setText("Otvoreno");
            holder.mbStatus.getBackground().setColorFilter(context.getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
        }
        else {
            holder.tvClosure.setText("Zatvoreno");
            holder.mbStatus.getBackground().setColorFilter(context.getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
        }

        if (ad.getClosedArrival() == 0) holder.tvOnCheckIn.setText("Otvoreno");
        else holder.tvOnCheckIn.setText("Zatvoreno");

        if (ad.getClosedDeparture().equals("0")) holder.tvOnCheckOut.setText("Otvoreno");
        else holder.tvOnCheckOut.setText("Zatvoreno");

        if (ad.getNoOta() == 0) holder.tvOTA.setText("Ne");
        else holder.tvOTA.setText("Da");

        holder.tvMinStay.setText(ad.getMinStay().toString());
        holder.etMinStay.setText(ad.getMinStay().toString());
        holder.tvMinStayArr.setText(ad.getMinStayArrival().toString());
        holder.etMinStayArr.setText(ad.getMinStayArrival().toString());
        holder.tvMaxStay.setText(ad.getMaxStay().toString());
        holder.etMaxStay.setText(ad.getMaxStay().toString());
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
        @BindView(R.id.tvNoAvail)
        TextView tvNoAvail;
        @BindView(R.id.etNoAvail)
        EditText etNoAvail;
//        @BindView(R.id.tvPrice)
//        TextView tvPrice;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.etPrice)
        EditText etPrice;
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
        @BindView(R.id.etMinStay)
        EditText etMinStay;
        @BindView(R.id.tvMinStayArr)
        TextView tvMinStayArr;
        @BindView(R.id.etMinStayArr)
        EditText etMinStayArr;
        @BindView(R.id.tvMaxStay)
        TextView tvMaxStay;
        @BindView(R.id.etMaxStay)
        EditText etMaxStay;


        public CalendarHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.ivClose)
        public void onCalendarClick() {
            if (onCalendarClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onCalendarClickListener.onCalendarClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvNoAvail)
        public void onAvailClick() {
            if (onAvailClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onAvailClickListener.onAvailClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvPrice)
        public void onPriceClick() {
            if (onPriceClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onPriceClickListener.onPriceClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
         }

         @OnClick({R.id.mbStatus, R.id.tvClosure})
        public void changeStatus() {
             if (onStatusChangeListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                 onStatusChangeListener.onStatusChanged(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
             }

         }

//         @OnClick(R.id.tvClosure)
//        public void ChangeClosure() {
//             if (onOpenClosureChangeListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
//                 onOpenClosureChangeListener.onOpenClosureChanged(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
//             }
//         }

        @OnClick(R.id.tvOnCheckIn)
        public void ChangeOnCheckIn() {
            if (onCheckInListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onCheckInListener.onCheckIn(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvOnCheckOut)
        public void ChangeOnCheckOut() {
            if (onCheckOutListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onCheckOutListener.onCheckOut(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvOTA)
        public void changeOta() {
            if (otaClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                otaClickListener.otaClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvMinStay)
        public void minStay() {
            if (minStayClickLitener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                minStayClickLitener.minStayClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvMinStayArr)
        public void minStayArr() {
            if (minStayArrClickLitener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                minStayArrClickLitener.minStayArrClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tvMaxStay)
        public void maxStay() {
            if (maxStayClickLitener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                maxStayClickLitener.maxStayClick(itemView, getAdapterPosition(), calendars.get(getAdapterPosition()));
            }
        }
    }
}
