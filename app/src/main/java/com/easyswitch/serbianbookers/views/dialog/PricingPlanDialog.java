package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.PriceAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Price;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PricingPlanDialog extends AppCompatActivity {

    @BindView(R.id.rvRoomList)
    RecyclerView rvRoomList;
    List<Price> priceList = new ArrayList<>();
    PriceAdapter adapter;
    String rName, rID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_dialog);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);



        priceList.clear();
        priceList.addAll(App.getInstance().getData().getPrices());

        adapter = new PriceAdapter(this, priceList);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        rvRoomList.setAdapter(adapter);

        adapter.setOnPriceClickListener(new PriceAdapter.OnPriceClickListener() {
            @Override
            public void onPricingPlanClick(View view, int position, Price price) {
                Price clicedPrice = priceList.get(position);
                rName = clicedPrice.getName();
                rID = clicedPrice.getId();
                Intent i = new Intent();
                i.putExtra("pricingPlanName", rName);
                i.putExtra("pricingPlanID", rID);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    @OnClick(R.id.tvCancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

//    @OnClick(R.id.tvOkej)
//    public void okej() {
//        setResult(RESULT_OK);
//        finish();
//    }
}
