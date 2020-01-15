package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.PriceAdapter;
import com.easyswitch.serbianbookers.adapters.RoomListAdapter;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityList;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Price;
import com.easyswitch.serbianbookers.views.home.HomeFragment;
import com.google.gson.Gson;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickRoomDialog extends AppCompatActivity {

    @BindView(R.id.rvRoomList)
    RecyclerView rvRoomList;
    List<Price> priceList = new ArrayList<>();
    PriceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_room_dialog);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        DataBody dataBody = new DataBody();
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("2019-12-25");
        dataBody.setNewsOrderType("");
        dataBody.setNewsDfrom("");
        dataBody.setEventsDfrom("");
        dataBody.setEventsDto("");
        dataBody.setCalendarDfrom("2019-12-25");
        dataBody.setCalendarDto("2020-12-24");
        dataBody.setReservationsDfrom("2020-12-25");
        dataBody.setReservationsDto("2020-01-24");
        dataBody.setReservationsOrderBy("3");
        dataBody.setReservationsFilterBy("2019-12-24");
        dataBody.setReservationsOrderType("");
        dataBody.setGuestsOrderBy("135");
        dataBody.setGuestsOrderType("");

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getData(dataBody).observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {

                if (data == null) return;

                priceList.clear();
                priceList.addAll(data.getPrices());
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new PriceAdapter(this, priceList);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        rvRoomList.setAdapter(adapter);

        adapter.setOnPriceClickListener(new PriceAdapter.OnPriceClickListener() {
            @Override
            public void onPricingPlanClick(View view, int position, Price price) {
                Intent i = new Intent();
                i.putExtra("pricingPlanName", price.getName());
                i.putExtra("pricingPlanID", price.getId());
                setResult(RESULT_OK, i);
                Toast.makeText(PickRoomDialog.this, price.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.tvCancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.tvOkej)
    public void okej() {
        finish();
    }
}
