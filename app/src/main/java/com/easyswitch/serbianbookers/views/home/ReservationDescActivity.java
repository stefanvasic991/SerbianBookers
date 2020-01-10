package com.easyswitch.serbianbookers.views.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.GuestAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.Guest;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.ShowCard;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.dialog.GuestNotShowDialog;
import com.easyswitch.serbianbookers.views.dialog.InvalidCardDialog;
import com.easyswitch.serbianbookers.views.dialog.SendMailActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReservationDescActivity extends AppCompatActivity {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPerNight)
    TextView tvPerNight;
    @BindView(R.id.tvNights)
    TextView tvNights;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvReservationCode)
    TextView tvReservationCode;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvArrival)
    TextView tvArrival;
    @BindView(R.id.tvDeparture)
    TextView tvDeparture;
    @BindView(R.id.tvApartment)
    TextView tvApartment;
    @BindView(R.id.tvPeople)
    TextView tvPeople;
//    @BindView(R.id.tvExtra)
//    TextView tvExtra;
    @BindView(R.id.etNote)
    EditText etNote;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.ivEditNote)
    ImageView ivEditNote;
    @BindView(R.id.llEditNote)
    LinearLayout llEditNote;
    @BindView(R.id.rlSaveNote)
    RelativeLayout rlSaveNote;
    @BindView(R.id.textNote)
    TextView textNote;
    @BindView(R.id.rlNote)
    RelativeLayout rlNote;

    Reservation reservation;
    User u;
    BroadcastReceiver broadcastReceiver;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_desc);
        ButterKnife.bind(this);

        getWindow().setGravity(Gravity.CENTER);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        setFinishOnTouchOutside(true);

        reservation = getIntent().getParcelableExtra("reservation");
        u = getIntent().getParcelableExtra("currentUser");

        assert reservation != null;
        tvName.setText(reservation.getCustomerName());

        double dayPrice = Double.parseDouble(String.valueOf(Double.parseDouble(reservation.getTotalPrice())
                - Double.parseDouble(reservation.getPaymentGatewayFee()))) / Double.parseDouble(reservation.getNights());
        int pricePerNight = (int) dayPrice;
        tvPerNight.setText(pricePerNight + " / noć");

        tvNights.setText(reservation.getNights() + " noćenja");

        double total = Double.parseDouble(reservation.getTotalPrice());
        int totalPrice = (int) total;
        tvTotalPrice.setText("€" + totalPrice);

        tvEmail.setText(reservation.getCustomerMail());
        tvPhone.setText(reservation.getCustomerPhone());
        tvReservationCode.setText(reservation.getReservationCode());
        tvTime.setText(reservation.getDateReceived() + " " + reservation.getTimeReceived() + "h");
        tvArrival.setText(reservation.getDateArrival());
        tvDeparture.setText(reservation.getDateDeparture());
        tvApartment.setText(reservation.getRoomNumbers());
        tvPeople.setText(reservation.getMen() + ", " + reservation.getChildren());
//        tvExtra.setText(reservation);
        etNote.setText(reservation.getCustomerNotes());
        tvNote.setText(reservation.getCustomerNotes());

        Picasso.with(this).load(reservation.getChannelLogo()).into(ivLogo);

    }

    @OnClick(R.id.tvPhone)
    public void makeCall() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(reservation.getCustomerPhone())));
        }
    }

    @OnClick(R.id.ivEditNote)
    public void editNote() {
        llEditNote.setVisibility(View.GONE);
        textNote.setVisibility(View.GONE);

        rlNote.setVisibility(View.VISIBLE);
        rlSaveNote.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.ivClose)
    public void onCancelClick() {
        finish();
    }

    @OnClick(R.id.saveNote)
    public void saveNote() {
//        reservation.setCustomerNotes(etNote.getText().toString());
        tvNote.setText(etNote.getText().toString());

        llEditNote.setVisibility(View.VISIBLE);
        textNote.setVisibility(View.VISIBLE);

        rlNote.setVisibility(View.GONE);
        rlSaveNote.setVisibility(View.GONE);
    }

//    @OnClick(R.id.btnBack)
//    public void back() {
//        onBackPressed();
//    }

    @OnClick(R.id.showCard)
    public void showCard() {
        ShowCard showCard = new ShowCard();
        showCard.setKey(u.getKey());
        showCard.setAccount(u.getAccount());
        showCard.setLcode(u.getProperties().get(0).getLcode());
        showCard.setRcode(reservation.getReservationCode());
        showCard.setCcPass("");

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getShowCard(showCard).observe(this, new Observer<ShowCard>() {
            @Override
            public void onChanged(ShowCard showCard) {

            }
        });


    }

    @OnClick(R.id.invalidCard)
    public void invalidCard() {
        Intent i = new Intent(this, InvalidCardDialog.class);
        i.putExtra("question", getResources().getString(R.string.doesInvalidCard));
        i.putExtra("reservationCode", reservation.getReservationCode());
        startActivity(i);
    }

    @OnClick(R.id.guestNotShow)
    public void guestNotShow() {
        Intent i = new Intent(this, GuestNotShowDialog.class);
        i.putExtra("question", getResources().getString(R.string.gost_not_show));
        i.putExtra("reservationCode", reservation.getReservationCode());
        startActivity(i);
    }

    @OnClick({R.id.imageView2, R.id.tvEmail})
    public void sentMail() {
        Intent i = new Intent(this, SendMailActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick({R.id.imageView4, R.id.tvPhone})
    public void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(reservation.getCustomerPhone()));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == InvalidCardDialog.RESULT_OK) {
                
            }
        }

        if (requestCode == 2) {
            if (resultCode == GuestNotShowDialog.RESULT_OK) {

            }
        }
    }
}
