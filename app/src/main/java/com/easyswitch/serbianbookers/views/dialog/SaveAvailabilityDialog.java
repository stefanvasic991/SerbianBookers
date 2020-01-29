package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.models.Day;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.NewValues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveAvailabilityDialog extends AppCompatActivity {

    @BindView(R.id.cancel)
    TextView c;

    String dFrom;
    Integer avail, daysBetween;
    List<String> multipleIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        dFrom = getIntent().getStringExtra("dFrom");
        avail = getIntent().getIntExtra("insertedAvail", 0);
        daysBetween = getIntent().getIntExtra("daysBetween", 0);
        multipleIDs = getIntent().getStringArrayListExtra("multipleIDs");
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish(); }

    @OnClick(R.id.ok)
    public void ok() {
        setResult(RESULT_OK);
        Day day = new Day();
        day.setAvail(avail);

        List<Day> dayList = new ArrayList<>();
        dayList.add(day);

        NewValues newValues = new NewValues();
        newValues.setId("");
        newValues.setDays(dayList);

        List<NewValues> nwList = new ArrayList<>();
        for (int i = 0; i < daysBetween; i ++) {

            nwList.add(i, newValues);
        }

        InsertAvail ia = new InsertAvail();
        ia.setKey(App.getInstance().getCurrentUser().getKey());
        ia.setAccount(App.getInstance().getCurrentUser().getAccount());
        ia.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ia.setDfrom(dFrom);
        ia.setOldValues(nwList);
        ia.setNewValues(nwList);
        ia.setMultipleIDs(multipleIDs);

        WebApiManager.get(this).getWebApi().insertAvail(ia).enqueue(new Callback<InsertAvail>() {
            @Override
            public void onResponse(Call<InsertAvail> call, Response<InsertAvail> response) {

            }

            @Override
            public void onFailure(Call<InsertAvail> call, Throwable t) {

            }
        });
        finish();
    }
}
