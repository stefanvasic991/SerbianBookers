package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.NewValuesRestriction;
import com.easyswitch.serbianbookers.models.Restriction;
import com.easyswitch.serbianbookers.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ClosureSnackBar extends AppCompatActivity {

    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String date, tag;
    Integer closedOptions = 0;
    Integer roomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("datum");
        roomID = getIntent().getIntExtra("roomID", 0);
        date  = getIntent().getStringExtra("datum");
        tag  =getIntent().getStringExtra("status");
        closedOptions = getIntent().getIntExtra("closedOptions", 0);
    }

//    @OnClick(R.id.llSnackBar)
//    public void cancelSnackBar() { finish(); }

    @OnClick(R.id.tvReset)
    public void onReset() {
        Intent i = new Intent();
        i.putExtra("tag", tag);
        setResult(RESULT_CANCELED, i);

        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        Intent i = new Intent();
        i.putExtra("tag", tag);
        i.putExtra("closedOptions", closedOptions);
        setResult(RESULT_OK, i);

        Restriction day = new Restriction();
        day.setClosed(Integer.valueOf(closedOptions));

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
        if (App.getInstance().getData().getRestrictions().isEmpty()) {
            Toast.makeText(this, "Ne postoje restrikcije", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
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
