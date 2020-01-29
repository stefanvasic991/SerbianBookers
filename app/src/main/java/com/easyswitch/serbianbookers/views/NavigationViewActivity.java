package com.easyswitch.serbianbookers.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.SP;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.dialog.SendMailActivity;
import com.easyswitch.serbianbookers.views.home.HomeActivity;
import com.easyswitch.serbianbookers.views.login.LoginActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationViewActivity extends AppCompatActivity {

    @BindView(R.id.tvPickedVila)
    TextView tvPickedVila;
//    @BindView(R.id.ivUserLogo)
//    ImageView userLogo;
//    @BindView(R.id.rgVila)
//    RadioGroup rgVila;

    @BindView(R.id.llFinishTouchOutside)
    LinearLayout llFinishTouchOutside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);

        getWindow().setGravity(Gravity.END);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));

        tvPickedVila.setText(App.getInstance().getCurrentUser().getProperties().get(0).getName());
//        Picasso.with(this).load(App.getInstance().getCurrentUser().getProperties().get(0).getLogo());


//        rgVila.setOnCheckedChangeListener((group, checkedId) -> {
//            switch (checkedId) {
//                case R.id.vila1:
//                    tvPickedVila.setText("Nalog Vila 1");
//                    break;
//                case R.id.vila2:
//                    tvPickedVila.setText("Nalog Vila 2");
//                    break;
//                case R.id.vila3:
//                    tvPickedVila.setText("Nalog Vila 3");
//                    break;
//                case R.id.vila4:
//                    tvPickedVila.setText("Nalog Vila 4");
//                    break;
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @OnClick(R.id.ivLogout)
    public void logout() {
//        User user = new User();
//        user.setUsername(null);
//        user.setPassword(null);
//        user.setKey(null);
//        user.setEmail(null);
//        user.setAccount(null);

        SP.getInstance().logout();

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @OnClick(R.id.llFinishTouchOutside)
    public void setLlFinishTouchOutside() {
        finish();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @OnClick(R.id.llSupport)
    public void sendEmail() {
        Intent i = new Intent(this, SendMailActivity.class);
        startActivity(i);
        finish();
    }
}
