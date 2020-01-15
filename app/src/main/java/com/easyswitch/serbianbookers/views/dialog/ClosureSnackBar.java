package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.User;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClosureSnackBar extends AppCompatActivity {


    User u;
    String date, tag, closure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_dialog);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llSnackBar)
    public void cancelSnackBar() { finish(); }

    @OnClick(R.id.tvReset)
    public void onReset() {
        Intent i = new Intent();
        i.putExtra("tag", tag);
        setResult(RESULT_CANCELED, i);
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @OnClick(R.id.tvSave)
    public void onSave() {
        Intent i = new Intent();
        i.putExtra("tag", tag);
        setResult(RESULT_OK, i);
        finish();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
