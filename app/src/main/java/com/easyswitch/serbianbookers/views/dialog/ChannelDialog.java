package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.adapters.ChannelAdapter;
import com.easyswitch.serbianbookers.models.Channel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelDialog extends AppCompatActivity {

    @BindView(R.id.rvRoomList)
    RecyclerView rvRoomList;
    List<Channel> channelList = new ArrayList<>();
    ChannelAdapter adapter;
    String rName, rID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_dialog);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        channelList.clear();
        channelList.addAll(App.getInstance().getData().getChannels());

        adapter = new ChannelAdapter(this, channelList);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        rvRoomList.setAdapter(adapter);

        adapter.setOnChannelClickListener(new ChannelAdapter.OnChannelClickListener() {
            @Override
            public void onChannelClick(View view, int position, Channel channel) {
                Channel clickedChannel = channelList.get(position);
                rName = clickedChannel.getName();
                rID = clickedChannel.getId();
                Intent i = new Intent();
                i.putExtra("channelName", rName);
                i.putExtra("channelID", rID);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    @OnClick(R.id.tvCancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

//    @OnClick(R.id.tvOkej)
//    public void okej() {
//        setResult(RESULT_OK);
//        finish();
//    }
}
