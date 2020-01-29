package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.Values;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavePriceDialog extends AppCompatActivity {

    String dFrom;
    Integer price, daysBetween, priceID;
    List<String> multipleIDs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        dFrom = getIntent().getStringExtra("dFrom");
        price = getIntent().getIntExtra("insertedPrice", 0);
        daysBetween = getIntent().getIntExtra("daysBetween", 0);
        priceID = getIntent().getIntExtra("priceID", 0);
        multipleIDs = getIntent().getStringArrayListExtra("multipleIDs");
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish(); }

    @OnClick(R.id.ok)
    public void ok() {
        setResult(RESULT_OK);
        AvailabilityData av = new AvailabilityData();
        Integer prc = av.setPrice(price);

        List<Integer> priceList = new ArrayList<>();
        for (int i = 0; i < daysBetween; i ++) {

            priceList.add(i, prc);
        }

        Values values = new Values();
        values.setId("");
        values.setDays(priceList);

        List<Values> valuesList = new ArrayList<>();
        valuesList.add(values);

        InsertPrice ip = new InsertPrice();
        ip.setKey(App.getInstance().getCurrentUser().getKey());
        ip.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ip.setAccount(App.getInstance().getCurrentUser().getAccount());
        ip.setDfrom(dFrom);
        ip.setPid(String.valueOf(priceID));
        ip.setOldValues(valuesList);
        ip.setNewValues(valuesList);
        ip.setMultipleIDs(multipleIDs);

        WebApiManager.get(this).getWebApi().insertPrice(ip).enqueue(new Callback<InsertPrice>() {
            @Override
            public void onResponse(Call<InsertPrice> call, Response<InsertPrice> response) {

            }

            @Override
            public void onFailure(Call<InsertPrice> call, Throwable t) {

            }
        });
        finish();
    }
}
