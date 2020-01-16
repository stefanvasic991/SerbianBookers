package com.easyswitch.serbianbookers.views.home;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.adapters.ReservationAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Event;
import com.easyswitch.serbianbookers.models.News;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.dialog.StatusDialog;
import com.easyswitch.serbianbookers.views.dialog.TimeDialog;
import com.google.gson.Gson;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import org.threeten.bp.LocalDate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by: Stefan Vasic
 */
public class HomeFragment extends Fragment {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

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
    @BindView(R.id.tvConfirmed)
    TextView tvConfirmed;
    @BindView(R.id.tvCanceled)
    TextView tvCanceled;
    @BindView(R.id.tvReservationTxt)
    TextView tvReservationTxt;
    @BindView(R.id.tvConfirmedTxt)
    TextView tvConfirmedTxt;
    @BindView(R.id.tvCanceledTxt)
    TextView tvCanceledTxt;

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

    @BindView(R.id.noReservations)
    TextView noReservations;

    Double sumaProgress;
    User u;
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

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        DataBody dataBody = new DataBody();
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("2019-12-25");
        dataBody.setNewsOrderType("");
        dataBody.setNewsDfrom("");
        dataBody.setEventsDfrom("");
        dataBody.setEventsDto("");
        dataBody.setCalendarDfrom("2019-12-25");
        dataBody.setCalendarDto("2020-12-24");
        dataBody.setReservationsDfrom("2020-12-25");
        dataBody.setReservationsDto("2020-01-24");
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

                double yest = Double.parseDouble(data.getOccupancy().getYesterday());
                int yesterday  = (int) yest;
                pbYesterday.setProgress(yesterday);

                double t = Double.parseDouble(data.getOccupancy().getToday());
                int today = (int) t;
                pbToday.setProgress(today);
                tvYesterdayPercentage.setText(yesterday + "%");
                tvTodayPercentage.setText(today + "%");

                sumaProgress = Double.parseDouble(data.getOccupancy().getToday())
                        - Double.parseDouble(data.getOccupancy().getYesterday());
                if (sumaProgress < 0) {
                    circularProgressBar.setProgress((float) - Double.parseDouble(String.valueOf(sumaProgress)));
                } else {
                    circularProgressBar.setProgress((float) Double.parseDouble(String.valueOf(sumaProgress)));
                }


                DecimalFormat format = new DecimalFormat("##.##");
                String formatted = format.format(sumaProgress);

                if (Double.parseDouble(data.getOccupancy().getToday())
                        < Double.parseDouble(data.getOccupancy().getYesterday())) {
                    tvToday.setTextColor(getResources().getColor(R.color.colorOrange));
                    pbToday.setProgressColors(getResources().getColor(R.color.colorText), getResources().getColor(R.color.colorOrange));

                    txtProgress.setText("" + formatted);
                    circularProgressBar.setColor(getResources().getColor(R.color.colorOrange));
                } else {
                    txtProgress.setText("+" + formatted);
                    circularProgressBar.setColor(getResources().getColor(R.color.colorBlue));
                }

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

                tvConfirmed.setOnClickListener(v -> {
                    if (Double.parseDouble(data.getOccupancy().getToday())
                            < Double.parseDouble(data.getOccupancy().getYesterday())) {
                        tvConfirmed.setBackground(getResources().getDrawable(R.drawable.reservation_shape_negative));
                        tvConfirmedTxt.setTextColor(getResources().getColor(R.color.colorOrange));
                    } else {
                        tvConfirmed.setBackground(getResources().getDrawable(R.drawable.reservation_shape_positive));
                        tvConfirmedTxt.setTextColor(getResources().getColor(R.color.colorBlue));
                    }
                });

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

                int res = data.getReceived().size() + data.getCanceled().size();
                tvReservation.setText(String.valueOf(res));
                tvConfirmed.setText(String.valueOf(data.getReceived().size()));
                tvCanceled.setText(String.valueOf(data.getCanceled().size()));
            }
        });

        News news = new News();
        news.setKey(App.getInstance().getCurrentUser().getKey());
        news.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        news.setAccount(App.getInstance().getCurrentUser().getAccount());
        news.setNewsOrderBy("date_arrival");
        news.setNewsOrderType("ASC");
        news.setNewsDfrom(LocalDate.now().toString());

        webApiClient.getNews(news).observe(this, new Observer<News>() {
            @Override
            public void onChanged(News news) {

                if (news == null) return;

                pbLoading.setVisibility(View.GONE);

                if (news.getReceived().isEmpty()) {
                    noReservations.setVisibility(View.VISIBLE);
                } else if (news.getReceived() != null) {
                    reservationList.clear();
                    reservationList.addAll(news.getReceived());
                    reservationAdapter.notifyDataSetChanged();
                    noReservations.setVisibility(View.GONE);
                } else {
                    ArrayList<Reservation> tmpList = new ArrayList<>();
                    tmpList.addAll(news.getReceived());
                    noReservations.setVisibility(View.GONE);
                }
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
//        TimeDialog.show(HomeFragment.this,
//                getString(R.string.yesterday),
//                getString(R.string.today),
//                getString(R.string.tomorrow),
//        );
//                getString(R.string.add_filter)
        Intent i = new Intent(getActivity(), TimeDialog.class);
        i.putExtra("currentUser", u);
        startActivityForResult(i, Consts.REQ_TIME_DIALOG);
    }

    @OnClick(R.id.clStatus)
    public void openStatusDialog() {
//        StatusDialog.show(HomeFragment.this,
//                getString(R.string.arrival),
//                getString(R.string.stay),
//                getString(R.string.departure),
//                getString(R.string.add_filter)
//        );
        Intent i = new Intent(getActivity(), StatusDialog.class);
        i.putExtra("currentUser", u);
        startActivityForResult(i, Consts.REQ_STATUS_DIALOG);
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


                if (status.equals(Consts.STAY)) {
                    Event e = new Event();
                    e.setKey(App.getInstance().getCurrentUser().getKey());
                    e.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                    e.setAccount(App.getInstance().getCurrentUser().getAccount());
                    e.setEventsDfrom(LocalDate.now().minusDays(1).toString());
                    e.setEventsDto(LocalDate.now().toString());

                    WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
                    webApiClient.getEvents(e).observe(this, new Observer<Event>() {
                        @Override
                        public void onChanged(Event event) {
                            if (event == null) {
                                noReservations.setVisibility(View.VISIBLE);
                                return;
                            } else
                                noReservations.setVisibility(View.GONE);

                                if (event.getStay() != null) {
                                    reservationList.clear();
                                    reservationList.addAll(event.getStay());
                                    reservationAdapter.notifyDataSetChanged();
                                } else {
                                    ArrayList<Reservation> tmpList = new ArrayList<>();
                                    tmpList.addAll(event.getStay());
                                }
                        }
                    });
                } else  if (status.equals(Consts.ARRIVAL)) {
                    Event e = new Event();
                    e.setKey(App.getInstance().getCurrentUser().getKey());
                    e.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                    e.setAccount(App.getInstance().getCurrentUser().getAccount());
                    e.setEventsDfrom(LocalDate.now().minusDays(1).toString());
                    e.setEventsDto(LocalDate.now().toString());

                    WebApiManager.get(getActivity()).getWebApi().event(e).enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Call<Event> call, Response<Event> response) {
                            if (response.isSuccessful()) {
                                if (response.body() == null) {
                                    noReservations.setVisibility(View.VISIBLE);
                                    return;
                                } else
                                    noReservations.setVisibility(View.GONE);

                                if (response.body().getArrivals() != null) {
                                    reservationList.clear();
                                    reservationList.addAll(response.body().getArrivals());
                                    reservationAdapter.notifyDataSetChanged();
                                } else {
                                    ArrayList<Reservation> tmpList = new ArrayList<>();
                                    tmpList.addAll(response.body().getArrivals());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Event> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                } else if (status.equals(Consts.DEPARTURE)) {
                    Event e = new Event();
                    e.setKey(App.getInstance().getCurrentUser().getKey());
                    e.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                    e.setAccount(App.getInstance().getCurrentUser().getAccount());
                    e.setEventsDfrom(LocalDate.now().minusDays(1).toString());
                    e.setEventsDto(LocalDate.now().toString());

                    WebApiManager.get(getActivity()).getWebApi().event(e).enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Call<Event> call, Response<Event> response) {
                            if (response.isSuccessful()) {

                                if (response.body() == null) {
                                    noReservations.setVisibility(View.VISIBLE);
                                    return;
                                } else
                                    noReservations.setVisibility(View.GONE);

                                if (response.body().getDepartures() != null) {
                                    reservationList.clear();
                                    reservationList.addAll(response.body().getDepartures());
                                    reservationAdapter.notifyDataSetChanged();
                                } else {
                                    ArrayList<Reservation> tmpList = new ArrayList<>();
                                    tmpList.addAll(response.body().getDepartures());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Event> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }
        }

        if (requestCode == Consts.REQ_TIME_DIALOG) {
            if (resultCode == TimeDialog.RESULT_OK) {
                String days = data.getStringExtra("data");
                tvTime.setText(days);
                tvTime.setTextColor(getResources().getColor(R.color.colorWhite));
                clTime.setBackgroundResource(R.drawable.selected_filter_shape);

                 if (days.equals(Consts.YESTERDAY)) {
                    News news = new News();
                    news.setKey(App.getInstance().getCurrentUser().getKey());
                    news.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                    news.setAccount(App.getInstance().getCurrentUser().getAccount());
                    news.setNewsOrderBy("date_arrival");
                    news.setNewsOrderType("ASC");
                    news.setNewsDfrom(LocalDate.now().minusDays(1).toString());

                     WebApiManager.get(getActivity()).getWebApi().news(news).enqueue(new Callback<News>() {
                         @Override
                         public void onResponse(Call<News> call, Response<News> response) {
                             if (response.isSuccessful()) {

                                 if (response.body() == null) {
                                     noReservations.setVisibility(View.VISIBLE);
                                     return;
                                 } else
                                     noReservations.setVisibility(View.GONE);

                                 if (response.body().getReceived() != null) {
                                     reservationList.clear();
                                     reservationList.addAll(response.body().getReceived());
                                     reservationAdapter.notifyDataSetChanged();
                                 } else {
                                     ArrayList<Reservation> tmpList = new ArrayList<>();
                                     tmpList.addAll(response.body().getReceived());
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<News> call, Throwable t) {

                         }
                     });
                } else if (days.equals(Consts.TOMMOROW)) {
                    News news = new News();
                    news.setKey(App.getInstance().getCurrentUser().getKey());
                    news.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                    news.setAccount(App.getInstance().getCurrentUser().getAccount());
                    news.setNewsOrderBy("date_arrival");
                    news.setNewsOrderType("ASC");
                    news.setNewsDfrom(LocalDate.now().plusDays(1).toString());

                     WebApiManager.get(getActivity()).getWebApi().news(news).enqueue(new Callback<News>() {
                         @Override
                         public void onResponse(Call<News> call, Response<News> response) {
                             if (response.isSuccessful()) {

                                 if (response.body() == null) {
                                     noReservations.setVisibility(View.VISIBLE);
                                     return;
                                 } else
                                     noReservations.setVisibility(View.GONE);

                                 if (response.body().getReceived() != null) {
                                     reservationList.clear();
                                     reservationList.addAll(response.body().getReceived());
                                     reservationAdapter.notifyDataSetChanged();
                                 } else {
                                     ArrayList<Reservation> tmpList = new ArrayList<>();
                                     tmpList.addAll(response.body().getReceived());
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<News> call, Throwable t) {
                             t.printStackTrace();
                             Timber.v("onFailure");
                         }
                     });

                }
            }
        }

    }

    @OnClick(R.id.ivSearch)
    public void openSearch() {
        ivLogo.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);

        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                }
            }
        });

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
