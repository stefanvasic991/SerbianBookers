package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RadioButton;

import com.easyswitch.serbianbookers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeRestrictionActivity extends AppCompatActivity {

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

    String restriction, date;
    Integer canceled = 0;
    Integer cancelInOut = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_restriction);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
    }


    public void getDay() {
        if (rb1.isChecked()) {
            restriction = "-";
        }
        if (rb2.isChecked()) {
            restriction = getResources().getString(R.string.open);
            canceled = 0;
        }
        if (rb3.isChecked()) {
            restriction = getResources().getString(R.string.closed);
            canceled = 1;
        }
        if (rb4.isChecked()) {
            restriction = getResources().getString(R.string.openNoCheckIn);
            canceled = 0;
            cancelInOut = 1;
        }
        if (rb5.isChecked()) {
            restriction = getResources().getString(R.string.openNoCheckOut);
            canceled = 0;
            cancelInOut = 1;
        }
        if (rb6.isChecked()) {
            restriction = getResources().getString(R.string.openNoCheckInCheckOut);
            canceled = 0;
            cancelInOut = 1;
        }

        Intent sendRes = new Intent();
        sendRes.putExtra("restriction", restriction);
        sendRes.putExtra("datum", date);
        sendRes.putExtra("canceled", canceled);
        sendRes.putExtra("cancelInOut", cancelInOut);
        setResult(RESULT_OK, sendRes);
    }

    @OnClick({R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5, R.id.rb6})
    public void checkDay() {
        setResult(RESULT_OK);
        getDay();
        finish();
    }
}
