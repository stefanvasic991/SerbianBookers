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
import com.easyswitch.serbianbookers.models.insert.Closed;
import com.easyswitch.serbianbookers.models.insert.ClosedInOut;
import com.easyswitch.serbianbookers.models.insert.MaxStay;
import com.easyswitch.serbianbookers.models.insert.MinStay;
import com.easyswitch.serbianbookers.models.insert.MinStayArr;
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
    String closure, onCheckIn, onCheckOut, minStay, minStayArr, maxStay;
    Integer closedInOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("datum");

        roomID = getIntent().getStringExtra("roomID");
        closure = getIntent().getStringExtra("closure");
        closedInOut = getIntent().getIntExtra("closed", 0);
        onCheckIn = getIntent().getStringExtra("onCheckIn");
        onCheckOut = getIntent().getStringExtra("onCheckOut");
        minStay = getIntent().getStringExtra("minStay");
        minStayArr = getIntent().getStringExtra("minStayArr");
        maxStay = getIntent().getStringExtra("maxStay");

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
        InsertAvailData av = new InsertAvailData();

        Closed cio = new Closed();
        cio.setClosed(closedInOut);

        MinStay ms = new MinStay();
        ms.setMinStay(minStay);

        MinStayArr msa = new MinStayArr();
        msa.setMinStayArr(minStayArr);

        MaxStay maxs = new MaxStay();
        maxs.setMaxStay(maxStay);

        av.setMin_stay(ms);
        av.setMin_stay_arrival(msa);
        av.setMax_stay(maxs);
        av.setClosedArrival(cio);
        av.setClosedDeparture(cio);

        NewValues newValues = new NewValues();
        newValues.setRoomId("416694");
        newValues.setAvailabilityData(Collections.singletonList(av));

        InsertRestriction ir = new InsertRestriction();
        ir.setKey(App.getInstance().getCurrentUser().getKey());
        ir.setAccount(App.getInstance().getCurrentUser().getAccount());
        ir.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ir.setDfrom(date);
        ir.setPid("");
        ir.setOldValues("");
        ir.setNewValues(newValues);

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getInsertRestriction(ir).observe(this, new Observer<InsertRestriction>() {
            @Override
            public void onChanged(InsertRestriction insertRestriction) {

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
