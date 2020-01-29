package com.easyswitch.serbianbookers.views.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.SP;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.HomeViewPagerAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity {

    public static final int NUM_ITEMS = 5;


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bnvNavigation)
    AHBottomNavigation bnvNavigation;

    User u;
    HomeViewPagerAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        u = getIntent().getExtras().getParcelable("currentUser");
        DataBody dataBody = new DataBody();
        dataBody.setKey(u.getKey());
        dataBody.setLcode(u.getProperties().get(0).getLcode());
        dataBody.setAccount(u.getAccount());
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
            @Override
            public void onChanged(Data data) {

                Intent i = new Intent();
                i.setAction("data");
                i.putExtra("priceId", data.getPrices().get(0).getId());
                i.putExtra("pricePlanName", data.getPrices().get(0).getName());
                if (data.getRestrictions().isEmpty()) {
                    Timber.e("Empty Restriction");
                } else {
                    i.putExtra("rId", data.getRestrictions().get(0).getId());
                    i.putExtra("restrictionPlanName", data.getRestrictions().get(0).getName());
                }
                sendBroadcast(i);
            }
        });

        homeAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(homeAdapter);
        viewPager.setOffscreenPageLimit(5);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                bnvNavigation.setCurrentItem(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        homeAdapter.notifyDataSetChanged();

        setupBottomNavigation();
    }

    public void setupBottomNavigation() {
        AHBottomNavigationItem home = new AHBottomNavigationItem(R.string.home, R.drawable.home, R.color.colorBlack);
        AHBottomNavigationItem reservations = new AHBottomNavigationItem(R.string.reservation, R.drawable.reservation, R.color.colorBlack);
        AHBottomNavigationItem calendar = new AHBottomNavigationItem(R.string.calendar, R.drawable.calendar, R.color.colorBlack);
        AHBottomNavigationItem price = new AHBottomNavigationItem(R.string.price, R.drawable.price, R.color.colorBlack);
        AHBottomNavigationItem statistics = new AHBottomNavigationItem(R.string.statistics, R.drawable.statistics, R.color.colorBlack);

        bnvNavigation.addItem(home);
        bnvNavigation.addItem(reservations);
        bnvNavigation.addItem(calendar);
        bnvNavigation.addItem(price);
        bnvNavigation.addItem(statistics);

        bnvNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        bnvNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.colorGray));
        bnvNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorWhite));
        bnvNavigation.setCurrentItem(0, true);
        bnvNavigation.setTranslucentNavigationEnabled(true);
        bnvNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);


        bnvNavigation.setOnTabSelectedListener((position, wasSelected) -> {

            if (wasSelected)
                return true;

            switch (position) {
                case 0:
                    viewPager.setCurrentItem(0);
                    break;
                case 1:
                    viewPager.setCurrentItem(1);
                    break;
                case 2:
                    viewPager.setCurrentItem(2);
                    break;
                case 3:
                    viewPager.setCurrentItem(3);
                    break;
                case 4:
                    viewPager.setCurrentItem(4);
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        viewPager.setCurrentItem(0, true);
    }
}

