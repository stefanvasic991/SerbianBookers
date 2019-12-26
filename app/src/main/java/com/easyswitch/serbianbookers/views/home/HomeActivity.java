package com.easyswitch.serbianbookers.views.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.adapters.HomeViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    public static final int NUM_ITEMS = 5;


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bnvNavigation)
    AHBottomNavigation bnvNavigation;

    HomeViewPagerAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

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

