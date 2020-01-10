package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import com.easyswitch.serbianbookers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtaActivity extends AppCompatActivity {

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ota);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        date = getIntent().getStringExtra("datum");
    }

    @OnClick(R.id.rbOpen)
    public void setOpen() {
        Intent i = new Intent();
        i.putExtra("datum", date);
        setResult(RESULT_OK, i);
        finish();
    }

    @OnClick(R.id.rbClosed)
    public void setClosed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
