package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.Day;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AvailabilitySnackBar extends AppCompatActivity {


    User u;
    String date, avail, noOta;
    Integer roomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        date  = getIntent().getStringExtra("datum");
        roomID = getIntent().getIntExtra("roomID", 0);
        avail = getIntent().getStringExtra("avail");
        noOta = getIntent().getStringExtra("ota");
    }

//    @OnClick(R.id.llSnackBar)
//    public void cancelSnackBar() { finish(); }

    @OnClick(R.id.tvReset)
    public void onReset() {
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        setResult(RESULT_OK);

        Day day = new Day();
        day.setAvail(Integer.valueOf(avail));

        List<Day> dayList = new ArrayList<>();
        dayList.add(day);

        NewValues newValues = new NewValues();
        newValues.setId("");
        newValues.setDays(dayList);

        List<NewValues> nwList = new ArrayList<>();
        nwList.add(newValues);

        InsertAvail ia = new InsertAvail();
        ia.setKey(App.getInstance().getCurrentUser().getKey());
        ia.setAccount(App.getInstance().getCurrentUser().getAccount());
        ia.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ia.setDfrom(date);
        ia.setOldValues(nwList);
        ia.setNewValues(nwList);
        ia.setMultipleIDs(Collections.singletonList(String.valueOf(roomID)));

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
