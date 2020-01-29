package com.easyswitch.serbianbookers.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.SP;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.home.HomeActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.btnLogin)
    public void login() {

        if (!checkUser()) return;

        showLoading(false);
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        if (checkBox.isChecked()) {
            user.setRemember("1");
        } else {
            user.setRemember("0");
        }

        WebApiManager.get(this).getWebApi().login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (user != null) {
                    if (checkBox.isChecked()) {
                        SP.getInstance().setRemember("1");
                    } else {
                        SP.getInstance().setRemember("0");
                    }
//                    SP.getInstance().setUser(user);
                    SP.getInstance().setUsername(username.getText().toString());
                    SP.getInstance().setPassword(password.getText().toString());
                    SP.getInstance().setUserExist(true);
                    App.getInstance().setCurrentUser(response.body());

                    if (response.body().getStatus().equals("error") || response.body().getStatus().equals("Invalid username/password")) {
                        password.setError(getText(R.string.password_not_match));
                        login.setVisibility(View.VISIBLE);
                        pbLoading.setVisibility(View.GONE);
                        return;
                    }

                    Intent i = new Intent(getApplication(), HomeActivity.class);
                    i.putExtra("currentUser", response.body());
                    startActivity(i);

                    Intent intent = new Intent();
                    intent.putExtra("user", response.body());
                    setResult(RESULT_OK, intent);

                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Timber.v("OnFailure");
            }
        });

        showLoading(true);
    }

    public boolean checkUser() {

        boolean isValid = true;

        if (username.getText().toString().length() == 0) {
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
