package com.easyswitch.serbianbookers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDexApplication;

import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.User;
import com.google.gson.Gson;
import com.jakewharton.threetenabp.AndroidThreeTen;

import timber.log.Timber;

public class App extends MultiDexApplication {


    private static App INSTANCE = new App();
    private Gson gson;
    private User currentUser;
    private Data data;
    private AvailabilityData availabilityData;

    public static App getInstance() {
        if (INSTANCE == null) {
            synchronized (App.class) {
                if (INSTANCE == null) {
                    INSTANCE = new App();
                }
            }
        }
        return INSTANCE;
    }

    public App() {
        INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

        AndroidThreeTen.init(this);

        //create notification channels
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }
    }

    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel mainChannel = new NotificationChannel(Consts.GENERAL_CHANNEL_ID,
                Consts.GENERAL_CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
        mainChannel.enableLights(true);
        mainChannel.enableVibration(true);
        mainChannel.setShowBadge(false);
        mainChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        notificationManager.createNotificationChannel(mainChannel);
    }


    public User getCurrentUser() {
        if(currentUser == null)
            currentUser = SP.getInstance().getUser();
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Data getData() {
        if (data == null) {
            data = SP.getInstance().getDATA();
        }
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public AvailabilityData getAvailabilityData() {
        return availabilityData;
    }

    public void setAvailabilityData(AvailabilityData availabilityData) {
        this.availabilityData = availabilityData;
    }
}
