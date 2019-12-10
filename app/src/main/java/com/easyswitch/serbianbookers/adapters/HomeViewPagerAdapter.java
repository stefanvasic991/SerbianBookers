package com.easyswitch.serbianbookers.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.easyswitch.serbianbookers.views.home.CalendarFragment;
import com.easyswitch.serbianbookers.views.home.HomeActivity;
import com.easyswitch.serbianbookers.views.home.HomeFragment;
import com.easyswitch.serbianbookers.views.home.PriceRestrictionFragment;
import com.easyswitch.serbianbookers.views.home.ReservationFragment;
import com.easyswitch.serbianbookers.views.home.StatisticsFragment;


public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    public HomeViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return HomeFragment.newInstance();
        } else if (position == 1) {
            return ReservationFragment.newInstance();
        } else if (position == 2) {
            return CalendarFragment.newInstance();
        } else if (position == 3) {
            return PriceRestrictionFragment.newInstance();
        } else if (position == 4) {
            return StatisticsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() { return HomeActivity.NUM_ITEMS; }
}
