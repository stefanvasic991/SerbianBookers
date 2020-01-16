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
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.InsertAvailData;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.models.insert.Closed;
import com.easyswitch.serbianbookers.models.insert.MaxStay;
import com.easyswitch.serbianbookers.models.insert.MinStay;
import com.easyswitch.serbianbookers.models.insert.MinStayArr;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClosureSnackBar extends AppCompatActivity {

    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String date, tag;
    int closedOptions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("date");
        tag  =getIntent().getStringExtra("status");
        closedOptions = getIntent().getIntExtra("closedOptions", 0);
//        Toast.makeText(this, date + tag + closure, Toast.LENGTH_SHORT).show();
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

        Closed c = new Closed();
        c.setClosed(closedOptions);

        InsertAvailData av = new InsertAvailData();
        av.setClosed(c);

        NewValues newValues = new NewValues();
        newValues.setRoomId("416694");
        newValues.setAvailabilityData(Collections.singletonList(av));

        InsertRestriction ir = new InsertRestriction();
        ir.setKey(App.getInstance().getCurrentUser().getKey());
        ir.setAccount(App.getInstance().getCurrentUser().getAccount());
        ir.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ir.setDfrom(date);
//        ir.setPid(data.getRestrictions().get(0).getId());
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
