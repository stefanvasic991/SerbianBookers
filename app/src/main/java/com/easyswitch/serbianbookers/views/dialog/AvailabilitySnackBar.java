package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertAvailData;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.models.insert.Avail;
import com.easyswitch.serbianbookers.models.insert.MaxStay;
import com.easyswitch.serbianbookers.models.insert.MinStay;
import com.easyswitch.serbianbookers.models.insert.MinStayArr;
import com.easyswitch.serbianbookers.models.insert.NoOta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AvailabilitySnackBar extends AppCompatActivity {


    User u;
    String date, roomID, avail, noOta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        date  = getIntent().getStringExtra("datum");
        roomID = getIntent().getStringExtra("roomID");
        avail = getIntent().getStringExtra("avail");
        noOta = getIntent().getStringExtra("ota");
    }

    @OnClick(R.id.llSnackBar)
    public void cancelSnackBar() { finish(); }

    @OnClick(R.id.tvReset)
    public void onReset() {
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        setResult(RESULT_OK);

        List<NewValues> nwList = new ArrayList<>();

        InsertAvailData av = new InsertAvailData();

        Avail a = new Avail();
        a.setAvail(avail);

        NoOta ota = new NoOta();
        ota.setNoOta(noOta);

        av.setAvail(a);
        av.setNo_ota(ota);

        NewValues newValues = new NewValues();
        newValues.setAvailabilityData(Collections.singletonList(av));
        nwList.add(newValues);


        InsertAvail ia = new InsertAvail();
        ia.setKey(App.getInstance().getCurrentUser().getKey());
        ia.setAccount(App.getInstance().getCurrentUser().getAccount());
        ia.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ia.setDfrom(date);
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
