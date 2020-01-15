package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertAvailData;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.models.insert.MaxStay;
import com.easyswitch.serbianbookers.models.insert.MinStay;
import com.easyswitch.serbianbookers.views.home.HomeFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestrictionSnackBar extends AppCompatActivity {

    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String empty = "";
    String date, roomID;
    BroadcastReceiver broadcastReceiver;
    String closure, onCheckIn, onCheckOut, minStay, minStayArr, maxStay, avail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

//        IntentFilter filter = new IntentFilter("sendData");
//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                minStay = intent.getExtras().getString("oldMin");
//            }
//        };
//        registerReceiver(broadcastReceiver, filter);


//        Toast.makeText(this, minStay, Toast.LENGTH_SHORT).show();

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("datum");

        roomID = getIntent().getStringExtra("roomID");
        closure = getIntent().getStringExtra("closure");
        onCheckIn = getIntent().getStringExtra("onCheckIn");
        onCheckOut = getIntent().getStringExtra("onCheckOut");
        minStay = getIntent().getStringExtra("minStay");
        minStayArr = getIntent().getStringExtra("minStayArr");
        maxStay = getIntent().getStringExtra("maxStay");
        avail = getIntent().getStringExtra("avail");

        if (closure ==  null) {
            closure = "/";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
//}

    @OnClick(R.id.tvReset)
    public void onReset() {
        setResult(RESULT_CANCELED);
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        setResult(RESULT_OK);

        List<NewValues> nwList = new ArrayList<>();

        Toast.makeText(getApplicationContext(), minStay+minStayArr+maxStay, Toast.LENGTH_SHORT).show();

        InsertAvailData av = new InsertAvailData();
//        if (minStay == null) {
//            av.setMinStay(0);
//            Toast.makeText(this, minStay, Toast.LENGTH_SHORT).show();
//        } else {
//            av.setMinStay(minStay);
//            Toast.makeText(this, minStay, Toast.LENGTH_SHORT).show();
//        }

        MinStay ms = new MinStay();
        ms.setMinStay(minStay);

        MaxStay maxs = new MaxStay();
        maxs.setMaxStay(maxStay);

        av.setMinStay(ms);
        av.setMinStayArrival(2);
        av.setMaxStay(maxs);

        NewValues newValues = new NewValues();
        newValues.setRoomId("229827");
        newValues.setAvailabilityData(Collections.singletonList(av));
        nwList.add(newValues);


        InsertAvail ia = new InsertAvail();
        ia.setKey(App.getInstance().getCurrentUser().getKey());
        ia.setAccount(App.getInstance().getCurrentUser().getAccount());
        ia.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ia.setDfrom(date);
//        ir.setPid(data.getRestrictions().get(0).getId());
//        ia.setPid("55482");
        ia.setOldValues("");
        ia.setNewValues(nwList);

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getInsertAvail(ia).observe(this, new Observer<InsertAvail>() {
            @Override
            public void onChanged(InsertAvail insertAvail) {

            }
        });

        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
