package com.easyswitch.serbianbookers.views.dialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.NewPrice;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.home.HomeFragment;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceSnackBar extends AppCompatActivity {
    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String date, price, oldPrice;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("datum");
        id = getIntent().getIntExtra("roomID", 1);
        oldPrice = getIntent().getStringExtra("staraCena");
        price = getIntent().getStringExtra("cena");
    }

    @OnClick(R.id.tvReset)
    public void onReset() {
        Intent i = new Intent();
        i.putExtra("oldPrice", oldPrice);
        setResult(RESULT_CANCELED, i);
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        setResult(RESULT_OK);
        AvailabilityData av = new AvailabilityData();
        Integer prc = av.setPrice(Integer.valueOf(price));

        List<Integer> priceList = new ArrayList<>();
        priceList.add(prc);

        NewPrice newValues = new NewPrice();
        newValues.setRoomId(String.valueOf(id));
        newValues.setAvailabilityData(priceList);

        InsertPrice ip = new InsertPrice();
        ip.setKey(App.getInstance().getCurrentUser().getKey());
        ip.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ip.setAccount(App.getInstance().getCurrentUser().getAccount());
        ip.setDfrom(date);
        ip.setPid("");
        ip.setOldValues("");
        ip.setNewValues(newValues);

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getInsertPrice(ip).observe(this, new Observer<InsertPrice>() {
            @Override
            public void onChanged(InsertPrice insertPrice) {
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
