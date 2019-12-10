package com.easyswitch.serbianbookers.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ActionMenuView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationViewActivity extends AppCompatActivity {

    @BindView(R.id.tvPickedVila)
    TextView tvPickedVila;

    @BindView(R.id.rgVila)
    RadioGroup rgVila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        // ...but notify us that it happened.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        setContentView(R.layout.activity_navigation_view);
        ButterKnife.bind(this);

        getWindow().setGravity(Gravity.END);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));

        rgVila.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.vila1:
                    tvPickedVila.setText("Nalog Vila 1");
                    break;
                case R.id.vila2:
                    tvPickedVila.setText("Nalog Vila 2");
                    break;
                case R.id.vila3:
                    tvPickedVila.setText("Nalog Vila 3");
                    break;
                case R.id.vila4:
                    tvPickedVila.setText("Nalog Vila 4");
                    break;
            }
        });
    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        // TODO Auto-generated method stub
//        finish();
//        return super.dispatchTouchEvent(ev);
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            finish();
        }
        return super.onTouchEvent(event);
    }

    @OnClick(R.id.tvLogout)
    public void logout() {
        User user = new User();
        user.setUsername(null);
        user.setPassword(null);
        user.setKey(null);
        user.setEmail(null);
        user.setAccount(null);

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
