package com.easyswitch.serbianbookers.views.home;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.dialog.TimeDialog;
import com.easyswitch.serbianbookers.views.filter.FilterActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

/**
 * Created by: Stefan Vasic
 */
public class ReservationFragment extends Fragment implements View.OnKeyListener {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.clFilter)
    ConstraintLayout clFilter;
    @BindView(R.id.rvReservation)
    RecyclerView rvReservation;

    @BindView(R.id.ivFilter)
    ImageView ivFilter;
    @BindView(R.id.tvFilter)
    TextView tvFilter;
    @BindView(R.id.clCancelFilter)
    ConstraintLayout clCancelFilter;

    User u;
    ArrayList<Reservation> reservationList = new ArrayList<>();
    ReservationAdapter reservationAdapter;
    BroadcastReceiver broadcastReceiver;
    DataBody dataBody = new DataBody();
    String dateFromBroadcast;

    public static ReservationFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ReservationFragment fragment = new ReservationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public ReservationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        ButterKnife.bind(this, view);

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("2019-10-21");
        dataBody.setNewsOrderType("");
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

                App.getInstance().setData(data);

                if (data.getReceived() != null) {
                    reservationList.clear();
                    reservationList.addAll(data.getReceived());
                    reservationAdapter.notifyDataSetChanged();
                } else {
                    ArrayList<Reservation> tmpList = new ArrayList<>();
                    tmpList.addAll(data.getReceived());
                }

//                SharedPreferences sp = getActivity().getSharedPreferences(HomeFragment.MY_PREFS_NAME, Context.MODE_PRIVATE);
//                SharedPreferences.Editor prefsEditor = sp.edit();
//                Gson gson = new Gson();
//                String json = gson.toJson(data);
//                prefsEditor.putString("Data", json);
//                prefsEditor.apply();
            }
        });

        reservationAdapter = new ReservationAdapter(getActivity(), reservationList);
        rvReservation.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvReservation.setAdapter(reservationAdapter);

        reservationAdapter.setOnReservationClickListener((v, position, reservation) -> {
            Intent intent = new Intent(getActivity(), ReservationDescActivity.class);
            intent.putExtra("reservation", reservation);
            intent.putExtra("currentUser", u);
            startActivity(intent);
        });

        clFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FilterActivity.class);
                startActivityForResult(i, Consts.REQ_FILTER_DIALOG);

            }
        });

        return view;
    }

    @OnClick(R.id.clCancelFilter)
    public void cancelFilter() {

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        DataBody dataBody = new DataBody();
        dataBody.setKey(u.getKey());
        dataBody.setLcode(u.getProperties().get(0).getLcode());
        dataBody.setAccount(u.getAccount());
        dataBody.setNewsOrderBy("2019-10-21");
        dataBody.setNewsOrderType("");
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

                if (data.getReceived() != null) {
                    reservationList.clear();
                    reservationList.addAll(data.getReceived());
                    reservationAdapter.notifyDataSetChanged();
                } else {
                    ArrayList<Reservation> tmpList = new ArrayList<>();
                    tmpList.addAll(data.getReceived());
                }
            }
        });

        ivFilter.setColorFilter(getResources().getColor(R.color.colorText));
        tvFilter.setTextColor(getResources().getColor(R.color.colorText));
        tvFilter.setText(getResources().getString(R.string.filter));
        clFilter.getBackground().setColorFilter(getResources().getColor(R.color.filterColor), PorterDuff.Mode.SRC_ATOP);
        clCancelFilter.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.v("onActivityResult");

        if (requestCode == Consts.REQ_FILTER_DIALOG) {
            if (resultCode == RESULT_OK) {

                ivFilter.setColorFilter(getResources().getColor(R.color.colorWhite));
                tvFilter.setTextColor(getResources().getColor(R.color.colorWhite));
                tvFilter.setText(getResources().getString(R.string.changeFilter));
                clFilter.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
                clCancelFilter.setVisibility(View.VISIBLE);

//                String query = data.getStringExtra("query");

//                Search search = new Search();
//                search.setKey(App.getInstance().getCurrentUser().getKey());
//                search.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
//                search.setAccount(App.getInstance().getCurrentUser().getAccount());
//                search.setKeyword(query);
//
//                WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
//                webApiClient.getSearch(search).observe(this, new Observer<Search>() {
//                    @Override
//                    public void onChanged(Search search) {
//                        if (search == null) return;
//
//                        if (search.getReservationList() != null) {
//                            reservationList.clear();
//                            reservationList.addAll(search.getReservationList());
//                            reservationAdapter.notifyDataSetChanged();
//                        } else {
//                            ArrayList<Reservation> tmpList = new ArrayList<>();
//                            tmpList.addAll(search.getReservationList());
//                            reservationAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });

                WebApiManager.get(getContext()).getWebApi().data(dataBody).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if (response.isSuccessful()) {
                            for (int i = 0; i < response.body().getReceived().size(); i++) {
                                if (!response.body().getReceived().get(i).getDateArrival().equals(dateFromBroadcast)) {

                                    reservationList.clear();
                                    reservationList.addAll(response.body().getReceived());
                                    reservationAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter("date");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                dateFromBroadcast = intent.getExtras().getString("arrDateFrom");
                String dateFromBroadcast1 = intent.getExtras().getString("arrDateTo");


            }
        };
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @OnClick(R.id.ivSearch)
    public void openSearch() {
        ivLogo.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Search search = new Search();
                search.setKey("6229dbc00c2d22e48b68db46b1e80c662a267931");
                search.setLcode("1521199571");
                search.setAccount("PV117");
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
        }

        return true;
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }
}
