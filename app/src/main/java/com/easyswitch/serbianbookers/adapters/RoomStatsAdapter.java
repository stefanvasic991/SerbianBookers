package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.StatsRoom;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomStatsAdapter extends RecyclerView.Adapter<RoomStatsAdapter.RoomStatsHolder> {

    Context context;
    List<StatsRoom> statsRoomList;

    public RoomStatsAdapter(Context context, List<StatsRoom> statsRoomList) {
        this.context = context;
        this.statsRoomList = statsRoomList;
    }

    @NonNull
    @Override
    public RoomStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomStatsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_units_stats, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomStatsHolder holder, int position) {
        StatsRoom statsRoom = statsRoomList.get(position);

        holder.room.setText(String.valueOf(statsRoom.getShortName()));

        double dIncome = statsRoom.getIncome();
        int income = (int) dIncome;
        holder.income.setText(String.valueOf(income));
        holder.noRes.setText(String.valueOf(statsRoom.getCount()));
        holder.noNights.setText(String.valueOf(statsRoom.getNights()));

        double price = statsRoom.getAvgIncome();
        int avPrice = (int) price;
        holder.avPrice.setText(String.valueOf(avPrice));
    }

    @Override
    public int getItemCount() {
        return statsRoomList.size();
    }

    class RoomStatsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.room)
        TextView room;
        @BindView(R.id.r1Income)
        TextView income;
        @BindView(R.id.r1noRes)
        TextView noRes;
        @BindView(R.id.r1noNights)
        TextView noNights;
        @BindView(R.id.r1AvPrice)
        TextView avPrice;

        public RoomStatsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
