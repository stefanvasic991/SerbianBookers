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
import android.os.Parcelable;
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
import com.easyswitch.serbianbookers.models.Room;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.views.dialog.ChannelDialog;
import com.easyswitch.serbianbookers.views.dialog.PickRoomDialog;
import com.easyswitch.serbianbookers.views.dialog.PricingPlanDialog;
import com.google.android.material.button.MaterialButton;
import com.thomashaertel.widget.MultiSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

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
    @BindView(R.id.tvChannel)
    TextView tvChannel;
    @BindView(R.id.tvRoomType)
    TextView tvRoomType;
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
    String selected, channelId, roomId, rid;
    int roomNumber;
    List<Room> a = new ArrayList<>();
    List<String> multilpeIDs = new ArrayList<>();

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

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getSelectedItem().toString();
                Timber.e(selected);
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

    }

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

        if (requestCode == 33) {
            if (resultCode == RESULT_OK) {
                String a = data.getStringExtra("channelName");
                channelId = data.getStringExtra("channelID");
                tvChannel.setText(a);

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrID", 5);
                i.putExtra("channelId", channelId);
                sendBroadcast(i);
            }
        }


        if (requestCode == 44) {
            if (resultCode == RESULT_OK) {assert data != null;
                a = data.getExtras().getParcelableArrayList("checkedList");
                for (int i = 0; i < a.size(); i++) {
                    if (a.size() <= 3) {
                        tvRoomType.setText(tvRoomType.getText() + a.get(i).getShortname() + " ");
                        multilpeIDs.add(a.get(i).getId());
                        Timber.e(String.valueOf(multilpeIDs));
                    } else if (a.size() == 4) {
                        tvRoomType.setText("4 selektovane sobe");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 5) {
                        tvRoomType.setText("5 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 6) {
                        tvRoomType.setText("6 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 7) {
                        tvRoomType.setText("7 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 8) {
                        tvRoomType.setText("8 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 9) {
                        tvRoomType.setText("9 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    }  else {
                        tvRoomType.setText("Sve sobe su selektovane");
                        multilpeIDs.add(a.get(i).getId());
                    }

                    roomNumber = a.size();
                }

                Intent i = new Intent();
                i.setAction("date");
                i.putExtra("arrID", 8);
                i.putStringArrayListExtra("checkedList", (ArrayList<String>) multilpeIDs);
                sendBroadcast(i);
            }
        }
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

    @OnClick(R.id.tvChannel)
    public void channel() {
        Intent i  = new Intent(this, ChannelDialog.class);
        startActivityForResult(i, 33);
    }

    @OnClick(R.id.tvRoomType)
    public void openPrice() {
        Intent i  = new Intent(this, PickRoomDialog.class);
        startActivityForResult(i, 44);
        tvRoomType.setText("");
    }

    @OnClick(R.id.btnFilter)
    public void filter() {
        setResult(RESULT_OK);
        finish();
    }
}
