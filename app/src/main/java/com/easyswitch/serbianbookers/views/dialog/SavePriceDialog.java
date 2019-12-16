package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;

import com.easyswitch.serbianbookers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SavePriceDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @OnClick(R.id.cancel)
    public void cancel() { finish(); }

    @OnClick(R.id.ok)
    public void ok() {

        finish();
    }
}
