package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClosureSnackBar extends AppCompatActivity {

    @BindView(R.id.tvReset)
    TextView tvReset;

    User u;
    String date, price;
    String closure, minStay, minStayArr, maxStay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        ButterKnife.bind(this);

        u = getIntent().getParcelableExtra("currentUser");
        date  = getIntent().getStringExtra("date");
//        price = getIntent().getStringExtra("price");
        closure = getIntent().getStringExtra("closure");
        minStay = getIntent().getStringExtra("minStay");
        Toast.makeText(this, maxStay, Toast.LENGTH_SHORT).show();
        minStayArr = getIntent().getStringExtra("minStayArr");
        maxStay = getIntent().getStringExtra("maxStay");
//        Toast.makeText(this, closure, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tvReset)
    public void onReset() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        setResult(RESULT_OK);
        finish();
    }
}
