package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.StatsChannel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelStatsAdapter extends RecyclerView.Adapter<ChannelStatsAdapter.ChannelStatsHolder> {


    Context context;
    List<StatsChannel> channelRoomList;

    public ChannelStatsAdapter(Context context, List<StatsChannel> channelRoomList) {
        this.context = context;
        this.channelRoomList = channelRoomList;
    }

    @NonNull
    @Override
    public ChannelStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChannelStatsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_units_stats, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelStatsHolder holder, int position) {

        StatsChannel statsChannel = channelRoomList.get(position);

//        holder.channel.setText(String.valueOf(statsChannel.getShortName()));
        holder.channel.setText(String.valueOf(statsChannel.getId()));
        holder.noRes.setText(String.valueOf(statsChannel.getCount()));

        double dIncome = statsChannel.getIncome();
        int income = (int) dIncome;
        holder.income.setText(String.valueOf(income));

        double dCosts = statsChannel.getCosts();
        int costs = (int) dCosts;
        holder.costs.setText(String.valueOf(costs));

        double price = statsChannel.getAvgIncome();
        int avPrice = (int) price;
        holder.earnings.setText(String.valueOf(avPrice));

        double dc = statsChannel.getCanceled();
        int cancel = (int) dc;
        holder.canceled.setText(String.valueOf(cancel));
    }
    @Override
    public int getItemCount() {
        return channelRoomList.size();
    }

    class ChannelStatsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.room)
        TextView channel;
        @BindView(R.id.r1noRes)
        TextView noRes;
        @BindView(R.id.r1Income)
        TextView income;
        @BindView(R.id.r1noNights)
        TextView costs;
        @BindView(R.id.r1AvPrice)
        TextView earnings;
        @BindView(R.id.r1AvNights)
        TextView canceled;

        public ChannelStatsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
