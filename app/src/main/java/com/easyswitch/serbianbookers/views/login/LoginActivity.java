package com.easyswitch.serbianbookers.views.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.SP;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.home.HomeActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etUsername)
    EditText username;
    @BindView(R.id.etPassword)
    EditText password;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.btnLogin)
    MaterialButton login;
    @BindView(R.id.progressBar2)
    ProgressBar pbLoading;

    private static boolean checkUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }private void showLoading(boolean show) {
        if (show) {
            pbLoading.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
        } else {
            pbLoading.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btnLogin)
    public void login() {

        showLoading(false);
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        if (checkBox.isChecked()) {
            user.setRemember("1");
        } else {
            user.setRemember("0");
        }


        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getLogin(user).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user.getStatus().equals("error")) return;

                if (user != null) {

//                    SP.getInstance().setUser(user);
                    SP.getInstance().setUsername(username.getText().toString());
                    SP.getInstance().setPassword(password.getText().toString());
                    if (checkBox.isChecked()) {
                        SP.getInstance().setRemember("1");
                    } else {
                        SP.getInstance().setRemember("0");
                    }
                    SP.getInstance().setUserExist(true);
                    App.getInstance().setCurrentUser(user);

                    Intent i = new Intent(getApplication(), HomeActivity.class);
                    i.putExtra("currentUser", user);
                    startActivity(i);
                    finish();
                }
            }
        });

//        Intent i = new Intent(getApplication(), HomeActivity.class);
////        i.putExtra("currentUser", user);
//        startActivity(i);
//        finish();

        showLoading(true);
    }

    public boolean checkUser() {

        boolean isValid = true;

        if (username.getText().toString().isEmpty()) {
            username.setError(getText(R.string.username_required));
            isValid = false;
        }

        if (password.getText().toString().length() < 6) {
            password.setError(getText(R.string.password_required));
            isValid = false;
        }

        return isValid;
    }
}