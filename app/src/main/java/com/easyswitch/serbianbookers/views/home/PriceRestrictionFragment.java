package com.easyswitch.serbianbookers.views.home;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.dialog.SaveAvailabilityDialog;
import com.easyswitch.serbianbookers.views.dialog.SavePriceDialog;
import com.easyswitch.serbianbookers.views.dialog.SaveRestrictionDialog;
import com.easyswitch.serbianbookers.views.filter.CalendarFilterActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by: Stefan Vasic
 */
public class PriceRestrictionFragment extends Fragment {

    @BindView(R.id.mbResDateFrom)
    MaterialButton  mbResDateFrom;
    @BindView(R.id.mbResDateTo)
    MaterialButton  mbResDateTo;
    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.etInsertPrice)
    EditText etInsertPrice;
    @BindView(R.id.etInsertAvailability)
    EditText etInsertAvailability;
    @BindView(R.id.etInsertMinStay)
    EditText etInsertMinStay;
    @BindView(R.id.etInsertMaxStay)
    EditText etInsertMaxStay;

    @BindView(R.id.tvMenuPrice)
    TextView tvMenuPrice;
    @BindView(R.id.tvMenuAvail)
    TextView tvMenuAvail;
    @BindView(R.id.tvMenuRestr)
    TextView tvMenuRestr;

    @BindView(R.id.rlPrice)
    RelativeLayout rlPrice;
    @BindView(R.id.rlAvailability)
    RelativeLayout rlAvailability;
    @BindView(R.id.rlRestriction)
    RelativeLayout rlRestriction;

    @BindView(R.id.savePrice)
    MaterialButton mbSavePrice;
    @BindView(R.id.saveAvailability)
    MaterialButton mbSaveAvailability;
    @BindView(R.id.saveRestriction)
    MaterialButton mbSaveRestriction;

    User u;

    public static PriceRestrictionFragment newInstance() {

        Bundle args = new Bundle();

        PriceRestrictionFragment fragment = new PriceRestrictionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public PriceRestrictionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price_restriction, container, false);
        ButterKnife.bind(this, view);

        u = getActivity().getIntent().getParcelableExtra("currentUser");

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String status = data.getStringExtra("data");
                mbResDateFrom.setText(status);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                mbSavePrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InsertPrice insertPrice = new InsertPrice();
                        insertPrice.setKey(u.getKey());
                        insertPrice.setAccount(u.getAccount());
                        insertPrice.setLcode(u.getProperties().get(0).getLcode());
                        insertPrice.setDfrom(status);
                        insertPrice.setPid("");
                        insertPrice.setOldValues("");
                        insertPrice.setNewValues(etInsertPrice.getText().toString());

                        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
                        webApiClient.getInsertPrice(insertPrice).observe(getActivity(), new Observer<InsertPrice>() {
                            @Override
                            public void onChanged(InsertPrice insertPrice) {
                                Intent i = new Intent(getActivity(), SavePriceDialog.class);
                                startActivity(i);
                            }
                        });
                    }
                });

                mbSaveAvailability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InsertAvail insertAvail = new InsertAvail();
                        insertAvail.setKey(u.getKey());
                        insertAvail.setAccount(u.getAccount());
                        insertAvail.setLcode(u.getProperties().get(0).getLcode());
                        insertAvail.setDfrom(status);
                        insertAvail.setOldValues("");
                        insertAvail.setNewValues(etInsertAvailability.getText().toString());

                        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
                        webApiClient.getInsertAvail(insertAvail).observe(getActivity(), new Observer<InsertAvail>() {
                            @Override
                            public void onChanged(InsertAvail insertAvail) {
                                Intent i = new Intent(getActivity(), SaveAvailabilityDialog.class);
                                startActivity(i);
                            }
                        });
                    }
                });
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String status = data.getStringExtra("data");
                mbResDateTo.setText(status);
                mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    @OnClick(R.id.mbResDateFrom)
    public void pickDateFrom() {
        Intent i = new Intent(getActivity(), CalendarFilterActivity.class);
        startActivityForResult(i, 1);
    }

    @OnClick(R.id.mbResDateTo)
    public void pickDateTo() {
        Intent i = new Intent(getActivity(), CalendarFilterActivity.class);
        startActivityForResult(i, 2);
    }

    @OnClick(R.id.tvMenuPrice)
    public void menuPrice() {
        tvMenuPrice.setBackground(getResources().getDrawable(R.drawable.menus_background));
        tvMenuAvail.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tvMenuRestr.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        rlPrice.setVisibility(View.VISIBLE);
        rlAvailability.setVisibility(View.GONE);
        rlRestriction.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvMenuAvail)
    public void menuAvail() {
        tvMenuPrice.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tvMenuAvail.setBackground(getResources().getDrawable(R.drawable.menus_background));
        tvMenuRestr.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        rlPrice.setVisibility(View.GONE);
        rlAvailability.setVisibility(View.VISIBLE);
        rlRestriction.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvMenuRestr)
    public void menuRestriction() {
        tvMenuPrice.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tvMenuAvail.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        tvMenuRestr.setBackground(getResources().getDrawable(R.drawable.menus_background));

        rlAvailability.setVisibility(View.GONE);
        rlPrice.setVisibility(View.GONE);
        rlRestriction.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }

    @OnClick(R.id.savePrice)
    public void savePrice() {

        Intent i = new Intent(getActivity(), SavePriceDialog.class);
        startActivity(i);
    }

    @OnClick(R.id.saveAvailability)
    public void saveAvailability() {

        Intent i = new Intent(getActivity(), SaveAvailabilityDialog.class);
        startActivity(i);

    }

    @OnClick(R.id.saveRestriction)
    public void saveRestriction() {

        Intent i = new Intent(getActivity(), SaveRestrictionDialog.class);
        startActivity(i);
    }

}
