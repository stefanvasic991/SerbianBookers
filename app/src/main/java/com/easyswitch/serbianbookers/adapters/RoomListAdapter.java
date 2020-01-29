package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityList;
import com.easyswitch.serbianbookers.models.Room;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.RoomListHolder> {

    Context context;
    List<Room> roomList;
    OnRoomClickListener onRoomClickListener;

    public RoomListAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    public interface OnRoomClickListener {
        void onRoomClick(View view, int position, Room room);
    }

    public void setOnRoomClickListener(OnRoomClickListener onRoomClickListener) {
        this.onRoomClickListener = onRoomClickListener;
    }

    @NonNull
    @Override
    public RoomListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomListHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_room_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListHolder holder, int position) {
        Room room = roomList.get(position);

        holder.cbRoom.setText(room.getShortname());
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

        @OnClick(R.id.cbRoom)
        public  void click() {
            if (onRoomClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onRoomClickListener.onRoomClick(itemView, getAdapterPosition(), roomList.get(getAdapterPosition()));
            }
        }
    }
}
