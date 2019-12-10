package com.easyswitch.serbianbookers.views.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.GuestAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.Guest;
import com.easyswitch.serbianbookers.models.GuestList;
import com.easyswitch.serbianbookers.models.Reservation;
import com.google.android.material.button.MaterialButton;
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
    @BindView(R.id.tvExtra)
    TextView tvExtra;
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

    private ArrayList<Guest> guestArrayList = new ArrayList<>();
    private GuestAdapter guestAdapter;
    Reservation reservation;

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

        assert reservation != null;
        tvName.setText(reservation.getCustomerName());
//        tvPerNight.setText(String.valueOf(reservation.getDayprices().get288968().get(1) + " / noć"));
        tvNights.setText(reservation.getNights() + " noćenja");
        tvTotalPrice.setText("€" + reservation.getTotalPrice());
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


//        Data data = new Data();
//        if (reservation.getIdWoodoo().equals("110238")) {
//            Picasso.with(this).load(Integer.parseInt(data.getChannels().get(0).getLogo())).into(ivLogo);
//        } else if (reservation.getIdWoodoo().equals("110239")) {
//            Picasso.with(this).load(Integer.parseInt(data.getChannels().get(1).getLogo())).into(ivLogo);
//        } else if (reservation.getIdWoodoo().equals("110240")) {
//            Picasso.with(this).load(Integer.parseInt(data.getChannels().get(2).getLogo())).into(ivLogo);
//        } else if (reservation.getIdWoodoo().equals("141075")) {
//            Picasso.with(this).load(Integer.parseInt(data.getChannels().get(3).getLogo())).into(ivLogo);
//        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        finish();
        return super.dispatchTouchEvent(ev);

    }

    @OnClick(R.id.ivEditNote)
    public void editNote() {
        llEditNote.setVisibility(View.GONE);
        textNote.setVisibility(View.GONE);

        rlNote.setVisibility(View.VISIBLE);
        rlSaveNote.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.btnBack)
    public void back() {
        onBackPressed();
    }
}
