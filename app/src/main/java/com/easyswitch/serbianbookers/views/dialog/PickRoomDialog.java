package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.RoomListAdapter;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityList;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.views.home.HomeFragment;
import com.google.gson.Gson;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickRoomDialog extends AppCompatActivity {

    @BindView(R.id.rvRoomList)
    RecyclerView rvRoomList;
    @BindView(R.id.tvCancel)
    TextView tcCancel;
    @BindView(R.id.tvClear)
    TextView tvClear;
    @BindView(R.id.tvSelectAll)
    TextView tvSelectAll;
    @BindView(R.id.tvOk)
    TextView tvOk;

    List<Availability> roomList = new ArrayList<>();
    RoomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_room_dialog);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        Availability av = new Availability();
        av.setKey(App.getInstance().getCurrentUser().getKey());
        av.setAccount(App.getInstance().getCurrentUser().getAccount());
        av.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        av.setDfrom(LocalDate.now().toString());
        av.setDto(LocalDate.now().plusDays(30).toString());
        av.setArr("");

//        SharedPreferences dataPrefs = getSharedPreferences(HomeFragment.MY_PREFS_NAME, MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = dataPrefs.getString("availability", "");
//        Availability availability = gson.fromJson(json, Availability.class);


        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getAvailability(av).observe(this, new Observer<Availability>() {
            @Override
            public void onChanged(Availability availability) {

                if (availability == null) return;

                if (availability != null) {
                    roomList.clear();
                }
            }
        });


        adapter = new RoomListAdapter(this, roomList);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        rvRoomList.setAdapter(adapter);
    }
}
