package com.easyswitch.serbianbookers.views.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.ReservationAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.Search;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.filter.FilterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    ArrayList<Reservation> reservationList = new ArrayList<>();
    ReservationAdapter reservationAdapter;

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

        DataBody dataBody = new DataBody();
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("2019-10-21");
        dataBody.setNewsOrderType("");
        dataBody.setNewsDfrom("");
        dataBody.setEventsDfrom("");
        dataBody.setEventsDto("");
        dataBody.setCalendarDfrom("2019-10-21");
        dataBody.setCalendarDto("2019-10-24");
        dataBody.setReservationsDfrom("2019-10-21");
        dataBody.setReservationsDto("2019-10-24");
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

        reservationAdapter = new ReservationAdapter(getActivity(), reservationList);
        rvReservation.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvReservation.setAdapter(reservationAdapter);

        reservationAdapter.setOnReservationClickListener((v, position, reservation) -> {
            Intent intent = new Intent(getActivity(), ReservationDescActivity.class);
            intent.putExtra("reservation", reservation);
            startActivity(intent);
        });

        clFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FilterActivity.class);
                startActivity(i);
            }
        });

        return view;
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
