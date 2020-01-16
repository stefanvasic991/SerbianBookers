package com.easyswitch.serbianbookers.views.filter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.Channel;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Search;
import com.google.android.material.button.MaterialButton;
import com.thomashaertel.widget.MultiSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
//    @BindView(R.id.mbCanDateFrom)
//    MaterialButton mbCanDateFrom;
//    @BindView(R.id.mbCanDateTo)
//    MaterialButton mbCanDateTo;

    @BindView(R.id.statusSpinner)
    Spinner statusSpinner;
    @BindView(R.id.channelSpinner)
    Spinner channelSpinner;
    @BindView(R.id.typeSpinner)
    MultiSpinner typeSpinner;
    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.tvCancelDateRes)
    TextView tvCancelDateRes;
    @BindView(R.id.tvCancelDateArr)
    TextView tvCancelDateArr;
    @BindView(R.id.tvCancelDateDep)
    TextView tvCancelDateDep;
//    @BindView(R.id.tvCancelDateCan)
//    TextView tvCancelDateCan;

    BroadcastReceiver broadcastReceiver;
    String arrDateFrom, arrDateTo, depDateFrom, depDateTo, resDateFrom, resDateTo;
    String selected;
    List<String> channels = new ArrayList<>();
    List<String> rooms = new ArrayList<>();
    List<String> Id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrID", 6);
                i.putExtra("query", query);
                sendBroadcast(i);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        DataBody dataBody = new DataBody();
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("date_arrival");
        dataBody.setNewsOrderType("ASC");
        dataBody.setNewsDfrom("");
        dataBody.setEventsDfrom("");
        dataBody.setEventsDto("");
        dataBody.setCalendarDfrom("2019-12-21");
        dataBody.setCalendarDto("2019-12-28");
        dataBody.setReservationsDfrom("2019-12-21");
        dataBody.setReservationsDto("2019-12-28");
        dataBody.setReservationsOrderBy("3");
        dataBody.setReservationsFilterBy("2019-10-21");
        dataBody.setReservationsOrderType("");
        dataBody.setGuestsOrderBy("135");
        dataBody.setGuestsOrderType("");

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getData(dataBody).observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {

                if (data == null) return;

                statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected = parent.getSelectedItem().toString();
                        Intent i = new Intent();
                        i.setAction("date");
                        i.putExtra("arrID", 4);
                        i.putExtra("channel", selected);
                        sendBroadcast(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                for (int i = 0; i < data.getChannels().size(); i++) {
                    channels.addAll(Collections.singleton(data.getChannels().get(i).getName()));
                    Id.addAll(Collections.singleton(data.getChannels().get(i).getId()));
                }

                ArrayAdapter<String> cAdapter = new ArrayAdapter<>(getApplication(),
                        android.R.layout.simple_spinner_dropdown_item, channels);
                channelSpinner.setAdapter(cAdapter);
                channelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected = parent.getSelectedItem().toString();

                        Intent i = new Intent();
                        i.setAction("date");
                        i.putExtra("arrID", 5);
                        i.putExtra("channel", selected);
                        sendBroadcast(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                for (int i = 0; i < data.getRooms().size(); i++) {
                    rooms.addAll(Collections.singleton(data.getRooms().get(i).getShortname()));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(),
                        android.R.layout.simple_spinner_item, rooms);
                typeSpinner.setAdapter(adapter, false, onSelectedListener);

                boolean[] selectedItems = new boolean[adapter.getCount()];
                selectedItems[0] = true; // select first item
                typeSpinner.setSelected(selectedItems);

        }
        });
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            String s = String.valueOf(selected);
            mbDepDateTo.setText(s);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
//    }

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

//    @OnClick(R.id.tvCancelDateCan)
//    public void four() {
//        mbCanDateFrom.setText("");
//        mbCanDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
//        mbCanDateTo.setText("");
//        mbCanDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
//
//        tvCancelDateCan.setVisibility(View.GONE);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Consts.REQ_DATE_PICKER) {
            if (resultCode == RESULT_OK) {
                resDateFrom = data.getStringExtra("data");
                mbResDateFrom.setText(resDateFrom);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateRes.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                resDateTo = data.getStringExtra("data");
                mbResDateTo.setText(resDateTo);
                mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrID", 3);
                i.putExtra("resDateFrom", resDateFrom);
                i.putExtra("resDateTo", resDateTo);
                sendBroadcast(i);

                tvCancelDateRes.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                arrDateFrom = data.getStringExtra("data");
                mbArrDateFrom.setText(arrDateFrom);
                mbArrDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbArrDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateArr.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                arrDateTo = data.getStringExtra("data");
                mbArrDateTo.setText(arrDateTo);
                mbArrDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbArrDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrID", 1);
                i.putExtra("arrDateFrom", arrDateFrom);
                i.putExtra("arrDateTo", arrDateTo);
                sendBroadcast(i);

                tvCancelDateArr.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                depDateFrom = data.getStringExtra("data");
                mbDepDateFrom.setText(depDateFrom);
                mbDepDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbDepDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                tvCancelDateDep.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                depDateTo = data.getStringExtra("data");
                mbDepDateTo.setText(depDateTo);
                mbDepDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbDepDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrID", 2);
                i.putExtra("depDateFrom", depDateFrom);
                i.putExtra("depDateTo", depDateTo);
                sendBroadcast(i);

                tvCancelDateDep.setVisibility(View.VISIBLE);
            }
        }

//        if (requestCode == 9) {
//            if (resultCode == RESULT_OK) {
//                String status = data.getStringExtra("data");
//                mbCanDateFrom.setText(status);
//                mbCanDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
//                mbCanDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
//
//                tvCancelDateCan.setVisibility(View.VISIBLE);
//            }
//        }
//
//        if (requestCode == 10) {
//            if (resultCode == RESULT_OK) {
//                String status = data.getStringExtra("data");
//                mbCanDateTo.setText(status);
//                mbCanDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
//                mbCanDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
//
//                tvCancelDateCan.setVisibility(View.VISIBLE);
//            }
//        }
    }

    @OnClick(R.id.btnBack)
    public void back() {
        onBackPressed();
    }

    @OnClick(R.id.mbResDateFrom)
    public void resDateFrom() {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivityForResult(i, Consts.REQ_DATE_PICKER);
    }

    @OnClick(R.id.mbResDateTo)
    public void resDateTo() {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivityForResult(i, 4);
    }

    @OnClick(R.id.mbArrDateFrom)
    public void arrDateFrom() {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivityForResult(i, 5);
    }

    @OnClick(R.id.mbArrDateTo)
    public void arrDateTo() {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivityForResult(i, 6);
    }

    @OnClick(R.id.mbDepDateFrom)
    public void depDateFrom() {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivityForResult(i, 7);
    }

    @OnClick(R.id.mbDepDateTo)
    public void depDateTo() {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivityForResult(i, 8);
    }

//    @OnClick(R.id.mbCanDateFrom)
//    public void canDatefrom() {
//        Intent i = new Intent(this, CalendarActivity.class);
//        startActivityForResult(i, 9);
//    }
//
//    @OnClick(R.id.mbCanDateTo)
//    public void canDateTo() {
//        Intent i = new Intent(this, CalendarActivity.class);
//        startActivityForResult(i, 10);
//    }

    @OnClick(R.id.btnFilter)
    public void filter() {
        setResult(RESULT_OK);
        finish();
    }
}
