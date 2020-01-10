package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.Toast;

import com.easyswitch.serbianbookers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StayActivity extends AppCompatActivity {

    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rb6)
    RadioButton rb6;
    @BindView(R.id.rb7)
    RadioButton rb7;

    String day, date, oldMaxStay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        date = getIntent().getStringExtra("datum");
    }

    public void getDay() {
        if (rb1.isChecked()) {
            day = "1";
        }
        if (rb2.isChecked()) {
            day = "2";
        }
        if (rb3.isChecked()) {
            day = "3";
        }
        if (rb4.isChecked()) {
            day = "4";
        }
        if (rb5.isChecked()) {
            day = "5";
        }
        if (rb6.isChecked()) {
            day = "6";
        }
        if (rb7.isChecked()) {
            day = "7";
        }

        Intent sendDay = new Intent();
        sendDay.putExtra("datum", day);
        sendDay.putExtra("date", date);
        setResult(RESULT_OK, sendDay);
    }

    @OnClick({R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5, R.id.rb6, R.id.rb7})
    public void checkDay() {
        setResult(RESULT_OK);
        getDay();
        finish();
    }
}
