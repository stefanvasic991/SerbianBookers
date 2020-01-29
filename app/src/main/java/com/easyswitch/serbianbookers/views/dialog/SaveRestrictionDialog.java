package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValuesRestriction;
import com.easyswitch.serbianbookers.models.Restriction;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveRestrictionDialog extends AppCompatActivity {

    String dFrom;
    Integer minStay, minStayArr, maxStay, daysBetween, restrictictionID, roomNumber, canceled, canceledInOut;
    List<String> multipleIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog); ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        dFrom = getIntent().getStringExtra("dFrom");
        minStay = getIntent().getIntExtra("minStay", 0);
        minStayArr = getIntent().getIntExtra("minStayArr", 0);
        maxStay = getIntent().getIntExtra("maxStay", 0);
        canceled = getIntent().getIntExtra("cancel", 0);
        canceledInOut = getIntent().getIntExtra("canceledInOut", 0);
        daysBetween = getIntent().getIntExtra("daysBetween", 0);
        roomNumber = getIntent().getIntExtra("roomNumber", 0);
        restrictictionID = getIntent().getIntExtra("restrictionID", 0);
        multipleIDs = getIntent().getStringArrayListExtra("multipleIDs");
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish(); }

    @OnClick(R.id.ok)
    public void ok() {
        setResult(RESULT_OK);

        Restriction day = new Restriction();
        day.setClosed(canceled);
        day.setClosedArrival(canceledInOut);
        day.setMinStay(minStay);
        day.setMinStayArr(minStayArr);
        day.setMaxStay(maxStay);
        List<Restriction> list = new ArrayList<>();
        for (int i = 0; i < daysBetween; i ++) {

            list.add(i, day);
        }

        NewValuesRestriction newValues = new NewValuesRestriction();
        newValues.setId("");
        newValues.setDays(list);

        List<NewValuesRestriction> nwList = new ArrayList<>();
        for (int i = 0; i < roomNumber; i++) {
            nwList.add(i, newValues);
        }

        InsertRestriction ir = new InsertRestriction();
        ir.setKey(App.getInstance().getCurrentUser().getKey());
        ir.setAccount(App.getInstance().getCurrentUser().getAccount());
        ir.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ir.setDfrom(dFrom);
        ir.setPid(String.valueOf(restrictictionID));
        ir.setOldValues(nwList);
        ir.setNewValues(nwList);
        ir.setMultipleIDs(multipleIDs);

        WebApiManager.get(this).getWebApi().insertRestriction(ir).enqueue(new Callback<InsertRestriction>() {
            @Override
            public void onResponse(Call<InsertRestriction> call, Response<InsertRestriction> response) {
            }

            @Override
            public void onFailure(Call<InsertRestriction> call, Throwable t) {

            }
        });
        finish();
    }
}
