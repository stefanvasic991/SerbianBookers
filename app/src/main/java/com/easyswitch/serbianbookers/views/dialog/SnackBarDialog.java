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
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackBarDialog extends AppCompatActivity {

    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String one = "0";
    String two = "0";
    String date, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        one = getIntent().getStringExtra("1");
        two = getIntent().getStringExtra("2");
        date  = getIntent().getStringExtra("date");
        price = getIntent().getStringExtra("price");
    }

    @OnClick(R.id.llSnackBar)
    public void cancelSnackBar() { finish(); }

    @OnClick(R.id.tvReset)
    public void onReset() { finish(); }

    @OnClick(R.id.tvSave)
    public void onSave() {
        if (one.equals("1")) {
            InsertPrice insertPrice = new InsertPrice();
            insertPrice.setKey(u.getKey());
            insertPrice.setAccount(u.getAccount());
            insertPrice.setLcode(u.getProperties().get(0).getLcode());
            insertPrice.setDfrom(date);
            insertPrice.setPid("");
            insertPrice.setOldValues("");
            insertPrice.setNewValues(price);

            WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
            webApiClient.getInsertPrice(insertPrice).observe(this, new Observer<InsertPrice>() {
                @Override
                public void onChanged(InsertPrice insertPrice) {
                }
            });
        }
//            if (two.equals("2")) {
//        }
        else Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
    }

}
