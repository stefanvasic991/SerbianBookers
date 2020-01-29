package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Channel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelHOlder> {

    private Context context;
    private List<Channel> channelList;
    OnChannelClickListener onChannelClickListener;

    public ChannelAdapter(Context context, List<Channel> channelList) {
        this.context = context;
        this.channelList = channelList;
    }

    @NonNull
    @Override
    public ChannelHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChannelHOlder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_price_restrictions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelHOlder holder, int position) {
        Channel channel = channelList.get(position);

        holder.rbRoom.setText(channel.getName());
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public interface OnChannelClickListener {
        void onChannelClick(View view, int position, Channel channel);
    }

    public void setOnChannelClickListener(OnChannelClickListener onChannelClickListener) {
        this.onChannelClickListener = onChannelClickListener;
    }

    class ChannelHOlder extends RecyclerView.ViewHolder {

        @BindView(R.id.rbRoom)
        RadioButton rbRoom;

        public ChannelHOlder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.rbRoom)
        public  void click() {
            if (onChannelClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onChannelClickListener.onChannelClick(itemView, getAdapterPosition(), channelList.get(getAdapterPosition()));
            }
        }
    }
}
