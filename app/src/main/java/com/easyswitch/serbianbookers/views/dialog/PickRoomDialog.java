package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.RoomListAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Price;
import com.easyswitch.serbianbookers.models.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickRoomDialog extends AppCompatActivity {

    @BindView(R.id.rvRoomList)
    RecyclerView rvRoomList;
    @BindView(R.id.tvOkej)
    TextView ok;
    List<Room> roomList = new ArrayList<>();
    List<Room> checkedRoom = new ArrayList<>();
    RoomListAdapter adapter;
    String rName, rID;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_room_dialog);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        roomList.clear();
        roomList.addAll(App.getInstance().getData().getRooms());

        adapter = new RoomListAdapter(this, roomList);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        rvRoomList.setAdapter(adapter);

        adapter.setOnRoomClickListener(new RoomListAdapter.OnRoomClickListener() {
            @Override
            public void onRoomClick(View view, int position, Room room) {
//                Room checkedRooms = roomList.get(position);
                CheckBox cbRoom = view.findViewById(R.id.cbRoom);

                if (cbRoom.isChecked()) {
                    checkedRoom.addAll(Collections.singleton(roomList.get(position)));
                } else {
                    checkedRoom.remove(roomList.get(position));
                }
            }
        });
    }

    @OnClick(R.id.tvCancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

//    @OnClick(R.id.tvClear)
//    public void clear () {
//        checkedRoom.removeAll(roomList);
//    }
//
//    @OnClick(R.id.tvSelectAll)
//    public void selectAll () {
//
//    }

    @OnClick(R.id.tvOkej)
    public void okej() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.putParcelableArrayListExtra("checkedList", (ArrayList<? extends Parcelable>) checkedRoom);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
