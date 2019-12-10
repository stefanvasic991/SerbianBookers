package com.easyswitch.serbianbookers.views.calendar;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.easyswitch.serbianbookers.R;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarDescriptionActivity extends AppCompatActivity {

    @BindView(R.id.tvDate)
    TextView tvDate;
//    @BindView(R.id.tvMonth)
//    TextView tvMonth;
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.tv1_0)
    TextView tv1_0;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.mbStatus)
    MaterialButton mbStatus;
    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.tvClosure)
    TextView tvClosure;
    @BindView(R.id.tvOnCheckIn)
    TextView tvOnCheckIn;
    @BindView(R.id.tvOnCheckOut)
    TextView tvOnCheckOut;
    @BindView(R.id.tvOTA)
    TextView tvOTA;
    @BindView(R.id.tvMinStay)
    TextView tvMinStay;
    @BindView(R.id.tvMinStayArr)
    TextView tvMinStayArr;
    @BindView(R.id.tvMaxStay)
    TextView tvMaxStay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_description);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        setFinishOnTouchOutside(true);
    }

    @OnClick(R.id.ivClose)
    public void close() {
        onBackPressed();
    }
}
