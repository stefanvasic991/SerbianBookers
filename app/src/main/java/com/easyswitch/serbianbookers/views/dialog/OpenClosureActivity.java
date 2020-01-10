package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.easyswitch.serbianbookers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenClosureActivity extends AppCompatActivity {

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_closure);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void sendDate() {
        date = getIntent().getStringExtra("datum");
//        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        i.putExtra("datum", date);
        setResult(RESULT_OK, i);
    }

    @OnClick(R.id.rbOpen)
    public void setOpen() {
        setResult(RESULT_OK);
        sendDate();
        finish();
    }

    @OnClick(R.id.rbClosed)
    public void setClosed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
