package com.easyswitch.serbianbookers.views.dialog;

import android.content.Intent;
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
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.User;

import org.threeten.bp.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceSnackBar extends AppCompatActivity {
    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String date, price, oldPrice;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("datum");
        oldPrice = getIntent().getStringExtra("staraCena");
        price = getIntent().getStringExtra("cena");
    }

    @OnClick(R.id.tvReset)
    public void onReset() {
        Intent i = new Intent();
        i.putExtra("oldPrice", oldPrice);
        setResult(RESULT_CANCELED, i);
        finish(); }

    @OnClick(R.id.tvSave)
    public void onSave() {

        InsertPrice ip = new InsertPrice();
        ip.setKey(App.getInstance().getCurrentUser().getKey());
        ip.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        ip.setAccount(App.getInstance().getCurrentUser().getAccount());
        ip.setDfrom(date);
        ip.setPid("");
        ip.setOldValues("");
        ip.setNewValues(price);

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getInsertPrice(ip).observe(this, new Observer<InsertPrice>() {
            @Override
            public void onChanged(InsertPrice insertPrice) {
            }
        });
        finish();
    }
}
