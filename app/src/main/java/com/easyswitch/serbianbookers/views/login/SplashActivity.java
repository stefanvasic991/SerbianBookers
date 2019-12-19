package com.easyswitch.serbianbookers.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.User;

public class SplashActivity extends AppCompatActivity {

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplication(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);

//        boolean userHasLogged = SP.getInstance().getUserExist();
//
//        if (userHasLogged) {
//            user.setUsername(SP.getInstance().getUsername());
//            user.setPassword(SP.getInstance().getPassword());

//            WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
//            webApiClient.getLogin(user).observe(this, new Observer<User>() {
//                @Override
//                public void onChanged(User user) {
//                    if (user != null) {
//
//                        SP.getInstance().setUser(user);
//                        App.getInstance().setCurrentUser(user);
//
//                        Intent i = new Intent(getApplication(), HomeActivity.class);
//                        i.putExtra("currentUser", user);
//                        startActivity(i);
//                        finish();
//                    } else {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Intent i = new Intent(getApplication(), LoginActivity.class);
//                                startActivity(i);
//                                finish();
//                            }
//                        }, 1000);
//                    }
//                }
//            });
//        }
    }
}
