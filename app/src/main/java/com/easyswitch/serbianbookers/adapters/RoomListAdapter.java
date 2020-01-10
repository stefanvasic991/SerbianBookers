package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.RoomListHolder> {

    Context context;
    List<Availability> roomList;

    public RoomListAdapter(Context context, List<Availability> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomListHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_room_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListHolder holder, int position) {
        Availability availability = roomList.get(position);

//        holder.cbRoom.setText(availability.getAvailabilityList().get(0).getShortName());
        for (int i = 0; i < availability.getAvailabilityList().size(); i++) {
            holder.cbRoom.setText(availability.getAvailabilityList().get(i).getShortName());
        }
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    class RoomListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cbRoom)
        CheckBox cbRoom;

        public RoomListHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
