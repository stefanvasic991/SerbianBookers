package com.easyswitch.serbianbookers.views.home;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.InsertAvail;
import com.easyswitch.serbianbookers.models.InsertPrice;
import com.easyswitch.serbianbookers.models.InsertRestriction;
import com.easyswitch.serbianbookers.models.NewValues;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.dialog.ChangeRestrictionActivity;
import com.easyswitch.serbianbookers.views.dialog.PickRoomDialog;
import com.easyswitch.serbianbookers.views.dialog.SaveAvailabilityDialog;
import com.easyswitch.serbianbookers.views.dialog.SavePriceDialog;
import com.easyswitch.serbianbookers.views.dialog.SaveRestrictionDialog;
import com.easyswitch.serbianbookers.views.filter.CalendarFilterActivity;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.thomashaertel.widget.MultiSpinner;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by: Stefan Vasic
 */
public class PriceRestrictionFragment extends Fragment {

    @BindView(R.id.mbResDateFrom)
    MaterialButton  mbResDateFrom;
    @BindView(R.id.mbResDateTo)
    MaterialButton  mbResDateTo;
    @BindView(R.id.spinner)
    MultiSpinner spinner;
    @BindView(R.id.ppSpinner)
    Spinner ppSpinner;
    @BindView(R.id.rSpinner)
    Spinner rSpinner;

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

    User u;
    String dateFrom, dateTo;
    Set<LocalDate> selectedDays = new HashSet<>();
    int year, month, dayOfMonth;
    Calendar calendar;
    private int roomId;
    String pricingPlaId, restrictionPlanId;
    List<String> rooms = new ArrayList<>();
    List<String> pricingPlan = new ArrayList<>();
    List<String> restrictionPlan = new ArrayList<>();
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateParse = new SimpleDateFormat("dd.MM.yyyy.");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    private PopupWindow pw;
    private boolean expanded;
    public static boolean[] checkSelected;

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
        Availability av = new Availability();
        av.setKey(App.getInstance().getCurrentUser().getKey());
        av.setAccount(App.getInstance().getCurrentUser().getAccount());
        av.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        av.setDfrom(LocalDate.now().toString());
        av.setDto(LocalDate.now().plusDays(30).toString());
        av.setArr("");

        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
        webApiClient.getAvailability(av).observe(this, new Observer<Availability>() {
            @Override
            public void onChanged(Availability availability) {

                if (availability == null) return;

                for (int i = 0; i < availability.getAvailabilityList().size(); i++) {
                    rooms.addAll(Collections.singleton(availability.getAvailabilityList().get(i).getShortName()));
                    roomId = availability.getAvailabilityList().get(i).getId();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, rooms);
                spinner.setAdapter(adapter, false, onSelectedListener);

                boolean[] selectedItems = new boolean[adapter.getCount()];
                selectedItems[1] = true; // select second item
                spinner.setSelected(selectedItems);

            }
        });

        DataBody dataBody = new DataBody();
        dataBody.setKey(App.getInstance().getCurrentUser().getKey());
        dataBody.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
        dataBody.setAccount(App.getInstance().getCurrentUser().getAccount());
        dataBody.setNewsOrderBy("2019-12-25");
        dataBody.setNewsOrderType("");
        dataBody.setNewsDfrom("");
        dataBody.setEventsDfrom("");
        dataBody.setEventsDto("");
        dataBody.setCalendarDfrom("2019-12-25");
        dataBody.setCalendarDto("2020-12-24");
        dataBody.setReservationsDfrom("2020-12-25");
        dataBody.setReservationsDto("2020-01-24");
        dataBody.setReservationsOrderBy("3");
        dataBody.setReservationsFilterBy("2019-12-24");
        dataBody.setReservationsOrderType("");
        dataBody.setGuestsOrderBy("135");
        dataBody.setGuestsOrderType("");

        WebApiClient dataClient = ViewModelProviders.of(this).get(WebApiClient.class);
        dataClient.getData(dataBody).observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {

                if (data == null) return;

                for (int i = 0; i < data.getPrices().size(); i++) {
                    pricingPlan.addAll(Collections.singleton(data.getPrices().get(i).getName()));
                    pricingPlaId = data.getPrices().get(i).getId();
                }

                ArrayAdapter<String> ppAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, pricingPlan);
                ppSpinner.setAdapter(ppAdapter);

                for (int i = 0; i < data.getRestrictions().size(); i++) {
                    restrictionPlan.addAll(Collections.singleton(data.getRestrictions().get(i).getName()));
                    restrictionPlanId = data.getRestrictions().get(i).getId();
                }

                ArrayAdapter<String> rAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, pricingPlan);
                rSpinner.setAdapter(rAdapter);
            }
        });

        return view;
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
        }
    };

    public ArrayList<String> getSelectedDays() {
        ArrayList<String> tmp = new ArrayList<>();

        Iterator<LocalDate> iterator = selectedDays.iterator();

        while (iterator.hasNext()) {
            LocalDate localDate = iterator.next();
            tmp.add(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        return tmp;
    }

//    @OnClick(R.id.ppSpinner)
//    public void pricingPlan() {
//        Intent intent = new Intent(getActivity(), PickRoomDialog.class);
//        startActivityForResult(intent,33);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                dateFrom = data.getStringExtra("data");
                String dFrom = getDate(dateFrom);
                mbResDateFrom.setText(dateFrom);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);

                mbSavePrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        AvailabilityData av = new AvailabilityData();
//                        Integer prc = av.setPrice(Integer.valueOf(etInsertPrice.getText().toString()));
//
//                        List<Integer> priceList = new ArrayList<>();
//                        priceList.add(prc);
//
//                        NewValues newValues = new NewValues();
//                        newValues.setRoomId(String.valueOf(roomId));
//                        newValues.setAvailabilityData(priceList);
//
//                        InsertPrice insertPrice = new InsertPrice();
//                        insertPrice.setKey(u.getKey());
//                        insertPrice.setAccount(u.getAccount());
//                        insertPrice.setLcode(u.getProperties().get(0).getLcode());
//                        insertPrice.setDfrom(dFrom);
//                        insertPrice.setPid("151731");
//                        insertPrice.setOldValues("");
//                        insertPrice.setNewValues(newValues);
//
//                        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
//                        webApiClient.getInsertPrice(insertPrice).observe(getActivity(), new Observer<InsertPrice>() {
//                            @Override
//                            public void onChanged(InsertPrice insertPrice) {
//                                Intent i = new Intent(getActivity(), SavePriceDialog.class);
//                                startActivity(i);
//                            }
//                        });
                    }
                });

                Toast.makeText(getActivity(), pricingPlaId, Toast.LENGTH_SHORT).show();

                mbSaveAvailability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InsertAvail insertAvail = new InsertAvail();
                        insertAvail.setKey(u.getKey());
                        insertAvail.setAccount(u.getAccount());
                        insertAvail.setLcode(u.getProperties().get(0).getLcode());
                        insertAvail.setDfrom(dFrom);
                        insertAvail.setOldValues("");
//                        insertAvail.setNewValues(etInsertAvailability.getText().toString());

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

                mbSaveRestriction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences dataPrefs = getActivity().getSharedPreferences(HomeFragment.MY_PREFS_NAME, MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = dataPrefs.getString("Data", "");
                        Data data = gson.fromJson(json, Data.class);

                        AvailabilityData av = new AvailabilityData();

                        av.setMinStay(Integer.parseInt(etInsertMinStay.getText().toString()));
                        av.setMinStayArrival(Integer.parseInt(etInsertMinStayArr.getText().toString()));
                        av.setMaxStay(Integer.parseInt(etInsertMaxStay.getText().toString()));

                        NewValues newValues = new NewValues();
                        newValues.setRoomId(String.valueOf(roomId));
//                        newValues.setAvailabilityData(av);

                        InsertRestriction ir = new InsertRestriction();
                        ir.setKey(App.getInstance().getCurrentUser().getKey());
                        ir.setAccount(App.getInstance().getCurrentUser().getAccount());
                        ir.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                        ir.setDfrom(dFrom);
                        ir.setPid("45325");
                        ir.setOldValues("");
                        ir.setNewValues(newValues);

                        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
                        webApiClient.getInsertRestriction(ir).observe(getActivity(), new Observer<InsertRestriction>() {
                            @Override
                            public void onChanged(InsertRestriction insertRestriction) {
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
                dateTo = data.getStringExtra("data");
                mbResDateTo.setText(dateTo);
                mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
            }
        }

        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                String restriction = data.getStringExtra("restriction");
                mbOpenRestriction.setText(restriction);
            }
        }

//        if (requestCode == 33) {
//            if (resultCode == RESULT_OK) {
//                String plan = data.getStringExtra("pricingPlanName");
//                ppSpinner.setText(plan);
//            }
//        }
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

//        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
//                new DatePickerDialog.OnDateSetListener() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(year, month, day);
//                        @SuppressLint("SimpleDateFormat")
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//
//                        String dateString = dateFormat.format(calendar.getTime());
//                        mbResDateFrom.setText(dateString);
//                        mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
//                        mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
//                    }
//                }, year, month, dayOfMonth);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//        datePickerDialog.setCanceledOnTouchOutside(true);
//        datePickerDialog.setTitle("");
//        datePickerDialog.show();
    }

    @OnClick(R.id.mbResDateTo)
    public void pickDateTo() {
        Intent i = new Intent(getActivity(), CalendarFilterActivity.class);
        startActivityForResult(i, 2);

//        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
//                new DatePickerDialog.OnDateSetListener() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(year, month, day);
//                        @SuppressLint("SimpleDateFormat")
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//
//                        String dateString = dateFormat.format(calendar.getTime());
//                        mbResDateTo.setText(dateString);
//                        mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
//                        mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
//                    }
//                }, year, month, dayOfMonth);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//        datePickerDialog.setCanceledOnTouchOutside(true);
//        datePickerDialog.setTitle("");
//        datePickerDialog.show();
//        datesBetween(mbResDateFrom.getText().toString(), mbResDateTo.getText().toString());
    }

    public static List<LocalDate> datesBetween(LocalDate start, LocalDate end) {
        List<LocalDate> ret = new ArrayList<>();
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            ret.add(date);
        }
        return ret;
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

    @OnClick(R.id.mbOpenRestriction)
    public void changeRestriction() {
        Intent i = new Intent(getActivity(), ChangeRestrictionActivity.class);
        startActivityForResult(i, 11);
    }
}
