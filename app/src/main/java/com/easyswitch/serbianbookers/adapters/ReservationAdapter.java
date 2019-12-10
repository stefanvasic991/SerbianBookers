package com.easyswitch.serbianbookers.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Channel;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.Reservation;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {

    Context context;
    ArrayList<Reservation> reservations;
    OnReservationClickListener onReservationClickListener;

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dayFormat = new SimpleDateFormat("dd");
    @SuppressLint("SimpleDateFormat")
    private DateFormat monthDateFormat = new SimpleDateFormat("MMM");

    public ReservationAdapter(Context context) {
        this.context = context;
    }

    public ReservationAdapter(Context context, ArrayList<Reservation> reservations) {
        this.context = context;
        this.reservations = reservations;

        if (reservations == null) {
            this.reservations = new ArrayList<>();
        }
    }

    public interface OnReservationClickListener {
        void onReservationClick(View view, int position, Reservation reservation);
    }

    public void setOnReservationClickListener(OnReservationClickListener onReservationClickListener) {
        this.onReservationClickListener = onReservationClickListener;
    }

    @NonNull
    @Override
    public ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReservationHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_reservation, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReservationHolder holder, int position) {

        Reservation reservation = reservations.get(position);

        holder.tvStartDay.setText(getDayFromDate(reservation.getDateArrival()));
        holder.tvStartMonth.setText(getMonthFromDate(reservation.getDateArrival()));
        holder.tvEndDay.setText(getDayFromDate(reservation.getDateDeparture()));
        holder.tvEndMonth.setText(getMonthFromDate(reservation.getDateDeparture()));
        holder.tvName.setText(reservation.getCustomerName());
        holder.tvMenChildren.setText("Odrasli - " + reservation.getMen() + " " + "Deca - " + reservation.getChildren());

        if (reservation.getStatus() == String.valueOf(1)) {
            holder.ivStatus.setImageResource(R.drawable.ic_confirmed);
            holder.mcvStatus.setBackgroundResource(R.color.colorGreen);
        } else if (reservation.getStatus() == String.valueOf(5)) {
            holder.ivStatus.setImageResource(R.drawable.ic_canceled);
            holder.mcvStatus.setBackgroundResource(R.color.colorRed);
        }
//
        holder.tvTotalPrice.setText(reservation.getTotalPrice());
//        holder.tvPerNight.setText(reservation.getDayprices().get288968().get(0) + " / noć");

        holder.tvNights.setText(reservation.getNights() + " noćenja");
        holder.ivLogo.setImageResource(R.drawable.airbnb_logo_red);
        Data data = new Data();
//        if (reservation.getIdWoodoo().equals("110238")) {
//            Picasso.with(context).load(data.getChannels().get(0).getLogo()).into(holder.ivLogo);
//        } else if (reservation.getIdWoodoo().equals("110239")) {
//            Picasso.with(context).load(data.getChannels().get(1).getLogo()).into(holder.ivLogo);
//        } else if (reservation.getIdWoodoo().equals("110240")) {
//            Picasso.with(context).load(data.getChannels().get(2).getLogo()).into(holder.ivLogo);
//        } else if (reservation.getIdWoodoo().equals("141075")) {
//            Picasso.with(context).load(data.getChannels().get(3).getLogo()).into(holder.ivLogo);
//        }
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    private String getDayFromDate(String date){
        try {
            Date d = dateFormat.parse(date);

            return dayFormat.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
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

    public class ReservationHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvStartDay)
        TextView tvStartDay;
        @BindView(R.id.tvStartMonth)
        TextView tvStartMonth;
        @BindView(R.id.tvEndDay)
        TextView tvEndDay;
        @BindView(R.id.tvEndMonth)
        TextView tvEndMonth;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvMenChildren)
        TextView tvMenChildren;
        @BindView(R.id.mcvStatus)
        MaterialCardView mcvStatus;
        @BindView(R.id.ivStatus)
        ImageView ivStatus;
        @BindView(R.id.ivLogo)
        ImageView ivLogo;
        @BindView(R.id.tvTotalPrice)
        TextView tvTotalPrice;
        @BindView(R.id.tvPerNight)
        TextView tvPerNight;
        @BindView(R.id.tvNights)
        TextView tvNights;


        public ReservationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.mcvReservation)
        public void onReservationClick() {
            if (onReservationClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onReservationClickListener.onReservationClick(itemView, getAdapterPosition(), reservations.get(getAdapterPosition()));
            }
        }
    }
}