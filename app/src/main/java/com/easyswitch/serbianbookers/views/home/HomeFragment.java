package com.easyswitch.serbianbookers.views.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.ReservationAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.dialog.StatusDialog;
import com.easyswitch.serbianbookers.views.dialog.TimeDialog;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by: Stefan Vasic
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.progressBar2)
    ProgressBar pbLoading;
    @BindView(R.id.progressBar)
    CircularProgressBar circularProgressBar;
    @BindView(R.id.txtProgress)
    TextView txtProgress;
    @BindView(R.id.pbTotalRes)
    RoundedHorizontalProgressBar pbYesterday;
    @BindView(R.id.pbToday)
    RoundedHorizontalProgressBar pbToday;
    @BindView(R.id.tvYesterdayPercentage)
    TextView tvYesterdayPercentage;
    @BindView(R.id.tvTodayPercentage)
    TextView tvTodayPercentage;
    @BindView(R.id.tvToday)
    TextView tvToday;

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.tvReservation)
    TextView tvReservation;
    @BindView(R.id.tvCanceled)
    TextView tvCanceled;
    @BindView(R.id.tvAbsence)
    TextView tvAbsensce;
    @BindView(R.id.tvReservationTxt)
    TextView tvReservationTxt;
    @BindView(R.id.tvCanceledTxt)
    TextView tvCanceledTxt;
    @BindView(R.id.tvAbsenceTxt)
    TextView tvAbsensceTxt;

    @BindView(R.id.rvReservation)
    RecyclerView rvReservation;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.clTime)
    ConstraintLayout clTime;
    @BindView(R.id.clStatus)
    ConstraintLayout clStatus;


    ArrayList<Reservation> reservationList = new ArrayList<>();
    private ReservationAdapter reservationAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        circularProgressBar.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorGray));

        searchView.onWindowFocusChanged(true);

        pbLoading.setVisibility(View.VISIBLE);

        DataBody dataBody = new DataBody();
        User user = new User();
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("2019-12-05");
        dataBody.setNewsOrderType("");
        dataBody.setNewsDfrom("");
        dataBody.setEventsDfrom("");
        dataBody.setEventsDto("");
        dataBody.setCalendarDfrom("2019-12-05");
        dataBody.setCalendarDto("2019-12-24");
        dataBody.setReservationsDfrom("2019-12-05");
        dataBody.setReservationsDto("2019-12-24");
        dataBody.setReservationsOrderBy("3");
        dataBody.setReservationsFilterBy("2019-12-24");
        dataBody.setReservationsOrderType("");
        dataBody.setGuestsOrderBy("135");
        dataBody.setGuestsOrderType("");

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getData(dataBody).observe(this, new Observer<Data>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Data data) {

                if (data == null) return;

                pbLoading.setVisibility(View.GONE);
                circularProgressBar.setProgress((float) Double.parseDouble(data.getOccupancy().getToday()));

                pbYesterday.setProgress((int) Double.parseDouble(data.getOccupancy().getYesterday()));
                pbToday.setProgress((int) Double.parseDouble(data.getOccupancy().getToday()));
                tvYesterdayPercentage.setText( data.getOccupancy().getYesterday() + "%");
                tvTodayPercentage.setText(data.getOccupancy().getToday() + "%");


                Double sumaProgress = Double.parseDouble(data.getOccupancy().getToday())
                        - Double.parseDouble(data.getOccupancy().getYesterday());

                if (Double.parseDouble(data.getOccupancy().getToday())
                        < Double.parseDouble(data.getOccupancy().getYesterday())) {
                    tvToday.setTextColor(getResources().getColor(R.color.colorOrange));
                    pbToday.setProgressColors(getResources().getColor(R.color.colorText), getResources().getColor(R.color.colorOrange));

                    txtProgress.setText("" + sumaProgress);
                    circularProgressBar.setColor(getResources().getColor(R.color.colorOrange));
                } else {
                    txtProgress.setText("+" + sumaProgress);
                    circularProgressBar.setColor(getResources().getColor(R.color.colorBlue));
                }
//
                tvReservation.setOnClickListener(v -> {
                    if (Double.parseDouble(data.getOccupancy().getToday())
                            < Double.parseDouble(data.getOccupancy().getYesterday())) {
                        tvReservation.setBackground(getResources().getDrawable(R.drawable.reservation_shape_negative));
                        tvReservationTxt.setTextColor(getResources().getColor(R.color.colorOrange));
                    } else {
                        tvReservation.setBackground(getResources().getDrawable(R.drawable.reservation_shape_positive));
                        tvReservationTxt.setTextColor(getResources().getColor(R.color.colorBlue));
                    }
                });
//
                tvCanceled.setOnClickListener(v -> {
                    if (Double.parseDouble(data.getOccupancy().getToday())
                            < Double.parseDouble(data.getOccupancy().getYesterday())) {
                        tvCanceled.setBackground(getResources().getDrawable(R.drawable.reservation_shape_negative));
                        tvCanceledTxt.setTextColor(getResources().getColor(R.color.colorOrange));
                    } else {
                        tvCanceled.setBackground(getResources().getDrawable(R.drawable.reservation_shape_positive));
                        tvCanceledTxt.setTextColor(getResources().getColor(R.color.colorBlue));
                    }
                });

                tvAbsensce.setOnClickListener(v -> {
                    if (Double.parseDouble(data.getOccupancy().getToday())
                            < Double.parseDouble(data.getOccupancy().getYesterday())) {
                        tvAbsensce.setBackground(getResources().getDrawable(R.drawable.reservation_shape_negative));
                        tvAbsensceTxt.setTextColor(getResources().getColor(R.color.colorOrange));
                    } else {
                        tvAbsensce.setBackground(getResources().getDrawable(R.drawable.reservation_shape_positive));
                        tvAbsensceTxt.setTextColor(getResources().getColor(R.color.colorBlue));
                    }
                });

                if (data.getReceived() != null) {
                    reservationList.clear();
                    reservationList.addAll(data.getReceived());
                    reservationAdapter.notifyDataSetChanged();
                } else {
                    ArrayList<Reservation> tmpList = new ArrayList<>();
                    tmpList.addAll(data.getReceived());
                }

                tvReservation.setText(String.valueOf(data.getReceived().size()));
                tvCanceled.setText(String.valueOf(data.getCanceled().size()));
            }
        });

        rvReservation.setLayoutManager(new LinearLayoutManager(getActivity()));
        reservationAdapter = new ReservationAdapter(getActivity(), reservationList);
        rvReservation.setAdapter(reservationAdapter);

        reservationAdapter.setOnReservationClickListener((v, position, reservation) -> {
            Intent intent = new Intent(getActivity(), ReservationDescActivity.class);
            intent.putExtra("reservation", reservation);
            startActivity(intent);
        });

        return view;
    }

    @OnClick(R.id.clTime)
    public void openTimeDialog() {
        TimeDialog.show(HomeFragment.this,
                getString(R.string.yesterday),
                getString(R.string.today),
                getString(R.string.tomorrow),
                getString(R.string.add_filter)
        );
    }

    @OnClick(R.id.clStatus)
    public void openStatusDialog() {
        StatusDialog.show(HomeFragment.this,
                getString(R.string.arrival),
                getString(R.string.stay),
                getString(R.string.departure),
                getString(R.string.add_filter)
        );
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }



    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.v("onActivityResult");

        if (requestCode == Consts.REQ_STATUS_DIALOG) {
            if (resultCode == TimeDialog.RESULT_OK) {
                String status = data.getStringExtra("status");
                tvStatus.setText(status);
                tvStatus.setTextColor(getResources().getColor(R.color.colorWhite));
                clStatus.setBackgroundResource(R.drawable.selected_filter_shape);
            }
        }

        if (requestCode == Consts.REQ_TIME_DIALOG) {
            if (resultCode == TimeDialog.RESULT_OK) {
                String days = data.getStringExtra("data");
                tvTime.setText(days);
                tvTime.setTextColor(getResources().getColor(R.color.colorWhite));
                clTime.setBackgroundResource(R.drawable.selected_filter_shape);
            }
        }
    }

    @OnClick(R.id.ivSearch)
    public void openSearch() {
        ivLogo.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Search search = new Search();
                search.setKey(App.getInstance().getCurrentUser().getKey());
                search.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                search.setAccount(App.getInstance().getCurrentUser().getAccount());
                search.setKeyword(query);

                WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
                webApiClient.getSearch(search).observe(getActivity(), new Observer<Search>() {
                    @Override
                    public void onChanged(Search search) {
                        if (search == null) return;

                        if (search.getReservationList() != null) {
                            reservationList.clear();
                            reservationList.addAll(search.getReservationList());
                            reservationAdapter.notifyDataSetChanged();
                        } else {
                            ArrayList<Reservation> tmpList = new ArrayList<>();
                            tmpList.addAll(search.getReservationList());
                        }
                    }
                });
                searchView.setVisibility(View.GONE);
                ivLogo.setVisibility(View.VISIBLE);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
