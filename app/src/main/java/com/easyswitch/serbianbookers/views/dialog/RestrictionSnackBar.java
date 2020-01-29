package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValuesRestriction;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.models.Restriction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class RestrictionSnackBar extends AppCompatActivity {

    @BindView(R.id.tvReset)
    TextView tvReset;
    @BindView(R.id.tvSave)
    TextView tvSave;

    User u;
    String empty = "";
    BroadcastReceiver broadcastReceiver;
    String date, closure, onCheckIn, onCheckOut, minStay, minStayArr, maxStay;
    Integer roomID, restrictionID, arrID, closedInOut, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("datum");
        Timber.e(date);

        roomID = getIntent().getIntExtra("roomID", 0);
        closure = getIntent().getStringExtra("closure");
        closedInOut = getIntent().getIntExtra("closed", 0);
        onCheckIn = getIntent().getStringExtra("onCheckIn");
        onCheckOut = getIntent().getStringExtra("onCheckOut");
        minStay = getIntent().getStringExtra("minStay");
        minStayArr = getIntent().getStringExtra("minStayArr");
        maxStay = getIntent().getStringExtra("maxStay");


        IntentFilter filter = new IntentFilter("sendDateToChild");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                arrID = intent.getIntExtra("arrID", 0);
                restrictionID = intent.getIntExtra("restrictionId", 0);

                restrictionID = id;
            }
        };
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
}

    @OnClick(R.id.tvReset)
    public void onReset() {
        setResult(RESULT_CANCELED);
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        setResult(RESULT_OK);
//
        Restriction day = new Restriction();
        if (minStay == null) {
            Timber.e("Empty min stay");
        } else {
            day.setMinStay(Integer.valueOf(minStay));
        }

        if (minStayArr == null) {
            Timber.e("Empty min stay arr");
        } else {
            day.setMinStayArr(Integer.valueOf(minStayArr));
        }

        if (maxStay == null) {
            Timber.e("Empty max stay arr");
        } else {
            day.setMaxStay(Integer.valueOf(maxStay));
        }

        List<Restriction> list = new ArrayList<>();
        list.add(day);

        NewValuesRestriction newValues = new NewValuesRestriction();
        newValues.setId("");
        newValues.setDays(list);

        List<NewValuesRestriction> nwList = new ArrayList<>();
        nwList.add(newValues);

        InsertRestriction ir = new InsertRestriction();
        ir.setKey(App.getInstance().getCurrentUser().getKey());
        ir.setAccount(App.getInstance().getCurrentUser().getAccount());
        ir.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ir.setDfrom(date);
        ir.setPid(App.getInstance().getData().getRestrictions().get(1).getId());
        ir.setOldValues(nwList);
        ir.setNewValues(nwList);
        ir.setMultipleIDs(Collections.singletonList(String.valueOf(roomID)));

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
