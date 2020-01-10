package com.easyswitch.serbianbookers.views.filter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Search;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.mbResDateFrom)
    MaterialButton mbResDateFrom;
    @BindView(R.id.mbResDateTo)
    MaterialButton mbResDateTo;
    @BindView(R.id.mbArrDateFrom)
    MaterialButton mbArrDateFrom;
    @BindView(R.id.mbArrDateTo)
    MaterialButton mbArrDateTo;
    @BindView(R.id.mbDepDateFrom)
    MaterialButton mbDepDateFrom;
    @BindView(R.id.mbDepDateTo)
    MaterialButton mbDepDateTo;
    @BindView(R.id.mbCanDateFrom)
    MaterialButton mbCanDateFrom;
    @BindView(R.id.mbCanDateTo)
    MaterialButton mbCanDateTo;

    @BindView(R.id.statusSpinner)
    Spinner statusSpinner;
    @BindView(R.id.channelSpinner)
    Spinner channelSpinner;
    @BindView(R.id.typeSpinner)
    Spinner typeSpinner;
    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.tvCancelDateRes)
    TextView tvCancelDateRes;
    @BindView(R.id.tvCancelDateArr)
    TextView tvCancelDateArr;
    @BindView(R.id.tvCancelDateDep)
    TextView tvCancelDateDep;
    @BindView(R.id.tvCancelDateCan)
    TextView tvCancelDateCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent sendQuery = new Intent();
                sendQuery.putExtra("query", query);
                setResult(RESULT_OK, sendQuery);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @OnClick(R.id.tvCancelDateRes)
    public void one() {
        mbResDateFrom.setText("");
        mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        mbResDateTo.setText("");
        mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        tvCancelDateRes.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvCancelDateArr)
    public void two() {
        mbArrDateFrom.setText("");
        mbArrDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        mbArrDateTo.setText("");
        mbArrDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        tvCancelDateArr.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvCancelDateDep)
    public void three() {
        mbDepDateFrom.setText("");
        mbDepDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        mbDepDateTo.setText("");
        mbDepDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        tvCancelDateDep.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvCancelDateCan)
    public void four() {
        mbCanDateFrom.setText("");
        mbCanDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        mbCanDateTo.setText("");
        mbCanDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        tvCancelDateCan.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Consts.REQ_DATE_PICKER) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbResDateFrom.setText(status);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("resDateFrom", status);
                sendBroadcast(i);

                tvCancelDateRes.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 4) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbResDateTo.setText(status);
                mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateRes.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 5) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbArrDateFrom.setText(status);
                mbArrDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbArrDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrDateFrom", status);
                sendBroadcast(i);

                tvCancelDateArr.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 6) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbArrDateTo.setText(status);
                mbArrDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbArrDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrDateTo", status);
                sendBroadcast(i);

                tvCancelDateArr.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 7) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbDepDateFrom.setText(status);
                mbDepDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbDepDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateDep.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 8) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbDepDateTo.setText(status);
                mbDepDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbDepDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateDep.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 9) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbCanDateFrom.setText(status);
                mbCanDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbCanDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateCan.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 10) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbCanDateTo.setText(status);
                mbCanDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbCanDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateCan.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.btnBack)
    public void back() {
        onBackPressed();
    }

    @OnClick(R.id.mbResDateFrom)
    public void resDateFrom() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, Consts.REQ_DATE_PICKER);
    }

    @OnClick(R.id.mbResDateTo)
    public void resDateTo() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 4);
    }

    @OnClick(R.id.mbArrDateFrom)
    public void arrDateFrom() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 5);
    }

    @OnClick(R.id.mbArrDateTo)
    public void arrDateTo() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 6);
    }

    @OnClick(R.id.mbDepDateFrom)
    public void depDateFrom() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 7);
    }

    @OnClick(R.id.mbDepDateTo)
    public void depDateTo() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 8);
    }

    @OnClick(R.id.mbCanDateFrom)
    public void canDatefrom() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 9);
    }

    @OnClick(R.id.mbCanDateTo)
    public void canDateTo() {
        Intent i = new Intent(this, CalendarFilterActivity.class);
        startActivityForResult(i, 10);
    }

    @OnClick(R.id.btnFilter)
    public void filter() {
        setResult(RESULT_OK);
        finish();
    }
}
