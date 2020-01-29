package com.easyswitch.serbianbookers.views.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Day;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.NewValuesRestriction;
import com.easyswitch.serbianbookers.models.Restriction;
import com.easyswitch.serbianbookers.models.Room;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.models.Values;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.dialog.ChangeRestrictionActivity;
import com.easyswitch.serbianbookers.views.dialog.PickRoomDialog;
import com.easyswitch.serbianbookers.views.dialog.PricingPlanDialog;
import com.easyswitch.serbianbookers.views.dialog.RestrictionPlanDialog;
import com.easyswitch.serbianbookers.views.dialog.SaveAvailabilityDialog;
import com.easyswitch.serbianbookers.views.dialog.SavePriceDialog;
import com.easyswitch.serbianbookers.views.dialog.SaveRestrictionDialog;
import com.easyswitch.serbianbookers.views.filter.CalendarFilterActivity;
import com.google.android.material.button.MaterialButton;
import com.thomashaertel.widget.MultiSpinner;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.Period;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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
    TextView spinner;
    @BindView(R.id.ppSpinner)
    TextView ppSpinner;
    @BindView(R.id.rSpinner)
    TextView rSpinner;

    @BindView(R.id.etInsertPrice)
    EditText etInsertPrice;
    @BindView(R.id.mbOpenRestriction)
    TextView mbOpenRestriction;
    @BindView(R.id.etInsertAvailability)
    EditText etInsertAvailability;
    @BindView(R.id.etInsertMinStay)
    EditText etInsertMinStay;
    @BindView(R.id.etInsertMinStayArr)
    EditText etInsertMinStayArr;
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

    String dateFrom, dateTo, dFrom, dTo, pid, rid, ids;
    int canceled, cancelInOut, roomNumber;
    int minStay, minStayArr, maxStay;
    List<Room> a = new ArrayList<>();
    List<String> multilpeIDs = new ArrayList<>();

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateParse = new SimpleDateFormat("dd.MM.yyyy.");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


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

//        ppSpinner.setText(App.getInstance().getData().getPrices().get(0).getName());
//        rSpinner.setText(App.getInstance().getData().getRestrictions().get(0).getName());

        return view;
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
        }
    };

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                dateFrom = data.getStringExtra("data");
                dFrom = getDate(dateFrom);
                mbResDateFrom.setText(dateFrom);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);


            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                dateTo = data.getStringExtra("data");
                dTo = getDate(dateTo);
                mbResDateTo.setText(dateTo);
                mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                LocalDate from = LocalDate.parse(dFrom);
                LocalDate to = LocalDate.parse(dTo);

                Period period = Period.between(from, to);
                int daysBetween = period.getDays();

                Timber.e(String.valueOf(daysBetween));

                mbSavePrice.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {

                        if (etInsertPrice.getText().toString().trim().length() == 0) {
                            etInsertPrice.setError(getResources().getString(R.string.emptyField));
                            return;
                        }

                        if (ppSpinner.getText().toString().trim().length() == 0) {
                            ppSpinner.setError(getResources().getString(R.string.emptyField));
                        }

                        Intent intent = new Intent(getActivity(), SavePriceDialog.class);
                        intent.putExtra("insertedPrice", Integer.valueOf(etInsertPrice.getText().toString()));
                        intent.putExtra("daysBetween", daysBetween);
                        intent.putExtra("dFrom", dFrom);
                        intent.putExtra("priceID", pid);
                        intent.putStringArrayListExtra("multipleIDs", (ArrayList<String>) multilpeIDs);
                        startActivityForResult(intent, 3);

                    }
                });

                mbSaveAvailability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etInsertAvailability.getText().toString().trim().length() == 0) {
                            etInsertAvailability.setError(getResources().getString(R.string.emptyField));
                            return;
                        }

                        Intent intent = new Intent(getActivity(), SaveAvailabilityDialog.class);
                        intent.putExtra("insertedAvail", Integer.valueOf(etInsertAvailability.getText().toString()));
                        intent.putExtra("daysBetween", daysBetween);
                        intent.putExtra("dFrom", dFrom);
                        intent.putStringArrayListExtra("multipleIDs", (ArrayList<String>) multilpeIDs);
                        startActivityForResult(intent, 4);

//                        Day day = new Day();
//                        day.setAvail(Integer.valueOf(etInsertAvailability.getText().toString()));
//
//                        List<Day> dayList = new ArrayList<>();
//                        dayList.add(day);
//
//                        NewValues newValues = new NewValues();
//                        newValues.setId("");
//                        newValues.setDays(dayList);
//
//                        List<NewValues> nwList = new ArrayList<>();
//                        for (int i = 0; i < daysBetween; i ++) {
//
//                            nwList.add(i, newValues);
//                        }
//
//                        InsertAvail ia = new InsertAvail();
//                        ia.setKey(App.getInstance().getCurrentUser().getKey());
//                        ia.setAccount(App.getInstance().getCurrentUser().getAccount());
//                        ia.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
//                        ia.setDfrom(dFrom);
//                        ia.setOldValues(nwList);
//                        ia.setNewValues(nwList);
//                        ia.setMultipleIDs(multilpeIDs);
//
//                        WebApiManager.get(getActivity()).getWebApi().insertAvail(ia).enqueue(new Callback<InsertAvail>() {
//                            @Override
//                            public void onResponse(Call<InsertAvail> call, Response<InsertAvail> response) {
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<InsertAvail> call, Throwable t) {
//
//                            }
//                        });
                    }
                });

                mbSaveRestriction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), SaveRestrictionDialog.class);

                        if (etInsertMinStay.getText().toString().trim().length() == 0) {
                            intent.putExtra("minStay", 0);
                        } else {
                            intent.putExtra("minStay", Integer.valueOf(etInsertMinStay.getText().toString()));
                        }

                        if (etInsertMinStayArr.getText().toString().trim().length() == 0) {
                            intent.putExtra("minStayArr", 0);
                        } else {
                            intent.putExtra("minStayArr", Integer.valueOf(etInsertMinStayArr.getText().toString()));
                        }

                        if (etInsertMaxStay.getText().toString().trim().length() == 0) {
                            intent.putExtra("maxStay", 0);
                        } else {
                            intent.putExtra("maxStay", Integer.valueOf(etInsertMaxStay.getText().toString()));
                        }

                        intent.putExtra("cancel", canceled);
                        intent.putExtra("canceledInOut", cancelInOut);
                        intent.putExtra("restrictionID", rid);
                        intent.putExtra("daysBetween", daysBetween);
                        intent.putExtra("dFrom", dFrom);
                        intent.putExtra("roomNumber", roomNumber);
                        intent.putStringArrayListExtra("multipleIDs", (ArrayList<String>) multilpeIDs);
                        startActivityForResult(intent, 5);
                    }
                });
            }
        }

        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                String restriction = data.getStringExtra("restriction");
                canceled = data.getIntExtra("canceled", 0);
                cancelInOut = data.getIntExtra("cancelInOut", 0);
                mbOpenRestriction.setText(restriction);
            }
        }

        if (requestCode == 33) {
            if (resultCode == RESULT_OK) {
                String a = data.getStringExtra("pricingPlanName");
                pid = data.getStringExtra("pricingPlanID");
                ppSpinner.setText(a);
            }
        }

        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {
                 String a = data.getStringExtra("restrictionPlanName");
                 rid = data.getStringExtra("restrictionPlanID");
                rSpinner.setText(a);
            }
        }

        if (requestCode == 44) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                a = data.getExtras().getParcelableArrayList("checkedList");
                for (int i = 0; i < a.size(); i++) {
                    if (a.size() <= 3) {
                        spinner.setText(spinner.getText() + a.get(i).getShortname() + " ");
                        multilpeIDs.add(a.get(i).getId());
                        Timber.e(String.valueOf(multilpeIDs));
                    } else if (a.size() == 4) {
                        spinner.setText("4 selektovane sobe");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 5) {
                        spinner.setText("5 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 6) {
                        spinner.setText("6 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 7) {
                        spinner.setText("7 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 8) {
                        spinner.setText("8 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    } else if (a.size() == 9) {
                        spinner.setText("9 selektovanih soba");
                        multilpeIDs.add(a.get(i).getId());
                    }  else {
                        spinner.setText("Sve sobe su selektovane");
                        multilpeIDs.add(a.get(i).getId());
                    }

                    roomNumber = a.size();
                }
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
            mbResDateFrom.setText("");
            mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
            mbResDateTo.setText("");
            mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
            etInsertPrice.setText("");
        }

        if (requestCode == 4 && resultCode == RESULT_OK) {
            mbResDateFrom.setText("");
            mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
            mbResDateTo.setText("");
            mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
            etInsertAvailability.setText("");
        }

        if (requestCode == 5 && resultCode == RESULT_OK) {
            mbResDateFrom.setText("");
            mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
            mbResDateTo.setText("");
            mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
            etInsertMinStay.setText("");
            etInsertMinStayArr.setText("");
            etInsertMaxStay.setText("");
        }
    }

    @NotNull
    private String getDate(String date) {
        try {
            Date d = dateParse.parse(date);

            return dateFormat.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        if (etInsertPrice.getText().toString().isEmpty()) {
            etInsertPrice.setError(getResources().getString(R.string.emptyField));
        }
    }

    @OnClick(R.id.saveAvailability)
    public void saveAvailability() {

        if (etInsertAvailability.getText().toString().isEmpty()) {
            etInsertAvailability.setError(getResources().getString(R.string.emptyField));
        }
    }

    @OnClick(R.id.saveRestriction)
    public void saveRestriction() {

    }

    @OnClick(R.id.mbOpenRestriction)
    public void changeRestriction() {
        Intent i = new Intent(getActivity(), ChangeRestrictionActivity.class);
        startActivityForResult(i, 11);
    }

    @OnClick(R.id.spinner)
    public void pickrooms() {
        Intent i  = new Intent(getActivity(), PickRoomDialog.class);
        startActivityForResult(i, 44);
        spinner.setText("");
    }

    @OnClick(R.id.ppSpinner)
    public void openPrice() {
        Intent i = new Intent(getActivity(), PricingPlanDialog.class);
        startActivityForResult(i, 33);
    }

    @OnClick(R.id.rSpinner)
    public void openDialog() {
        Intent i = new Intent(getActivity(), RestrictionPlanDialog.class);
        startActivityForResult(i, 111);
    }
}
