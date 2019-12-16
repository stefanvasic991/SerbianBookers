package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.GuestNotShow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvalidCardDialog extends AppCompatActivity {

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.tvQuit)
    TextView tvQuit;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;

    String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_card);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        question = getIntent().getStringExtra("question");
        tvQuestion.setText(question);
    }

    @OnClick(R.id.tvConfirm)
    public void onFilterClick(){
        setResult(RESULT_OK);
        noShow();
        finish();
    }

    @OnClick(R.id.tvQuit)
    public void onCancelClick(){
        setResult(RESULT_CANCELED);
        finish();
    }

    public void noShow() {

        String rCode = getIntent().getStringExtra("reservationCode");

        GuestNotShow notShow = new GuestNotShow();
        notShow.setKey("91ec94f885ed43e0f360c05369b0af4c4b99a674");
        notShow.setAccount("PV117");
        notShow.setLcode("1521199571");
        notShow.setRcode(rCode);

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getInvalidCard(notShow).observe(this, new Observer<GuestNotShow>() {
            @Override
            public void onChanged(GuestNotShow guestNotShow) {

            }
        });
    }

    public static void show(Activity activity, String question){
        Intent i = new Intent(activity, InvalidCardDialog.class);
        i.putExtra("question", question);
        activity.startActivityForResult(i, 1);

    }

    public static void show(Fragment fragment, String question){
        Intent i = new Intent(fragment.getActivity(), InvalidCardDialog.class);
        i.putExtra("question", question);
        fragment.startActivityForResult(i, 1);
    }
}
