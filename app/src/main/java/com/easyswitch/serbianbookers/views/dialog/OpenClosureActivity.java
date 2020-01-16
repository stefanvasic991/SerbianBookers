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

    String date, closure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_closure);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        setFinishOnTouchOutside(false);
    }

    @OnClick(R.id.rbOpen)
    public void setOpen() {
        date = getIntent().getStringExtra("datum");
        closure = "Otvoreno";
        Intent i = new Intent();
        i.putExtra("datum", date);
        i.putExtra("closure", closure);
        setResult(RESULT_OK, i);
        finish();
    }

    @OnClick(R.id.rbClosed)
    public void setClosed() {
        date = getIntent().getStringExtra("datum");
        closure = "Zatvoreno";
        Intent i = new Intent();
        i.putExtra("datum", date);
        i.putExtra("closure", closure);
        setResult(RESULT_OK, i);
        finish();
    }
}
