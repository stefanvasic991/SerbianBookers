package com.easyswitch.serbianbookers.adapters;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.easyswitch.serbianbookers.views.calendar.DvosobniFragment;
import com.easyswitch.serbianbookers.views.calendar.JednosobniFragment;
import com.easyswitch.serbianbookers.views.calendar.StudioFragment;
import com.easyswitch.serbianbookers.views.calendar.TrosobniFragment;

public class RoomViewPagerAdapter extends FragmentStatePagerAdapter {

    public RoomViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return JednosobniFragment.newInstance();
        } else if (position == 1) {
            return DvosobniFragment.newInstance();
        } else if (position == 2) {
            return TrosobniFragment.newInstance();
        } else if (position == 3) {
            return StudioFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
        return "Jednosobni";
    } else if (position == 1) {
            return "Dvosobni";
    } else if (position == 2) {
            return "Trosobni";
    } else if (position == 3) {
            return "Studio";
    }
        return null;
    }
}
