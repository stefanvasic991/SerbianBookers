package com.easyswitch.serbianbookers.views.calendar;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.adapters.CalendarAdapter;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.Calendar;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.dialog.AvailabilitySnackBar;
import com.easyswitch.serbianbookers.views.dialog.ClosureSnackBar;
import com.easyswitch.serbianbookers.views.dialog.RestrictionSnackBar;
import com.easyswitch.serbianbookers.views.dialog.OpenClosureActivity;
import com.easyswitch.serbianbookers.views.dialog.OtaActivity;
import com.easyswitch.serbianbookers.views.dialog.PriceSnackBar;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by: Stefan Vasic
 */
public class SixthRoomFragment extends Fragment {

    @BindView(R.id.rvCalendar)
    RecyclerView rvCalendar;

    List<AvailabilityData> calendarList = new ArrayList<>();
    CalendarAdapter calendarAdapter;
    BroadcastReceiver broadcastReceiver;
    String dateFromBroadcast, changeFormat;

    EditText price;
    MaterialButton status;
    private TextView tvPrice, openClosure, checkIn, checkOut, ota, minStay, minStayArr, maxStay, etAvail;
    private TextView tvMinStay, tvMinStayArr, tvMaxStay;
    View vCheckIn, vCheckOut;
    ImageView ivClose;
    LinearLayout llInfo;
    Integer id, closedOptions;
    String priceID, restrictionID, noOta, datum, closure;
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateParse = new SimpleDateFormat("dd.MM.yyyy.");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static SixthRoomFragment newInstance() {
        Bundle args = new Bundle();
        SixthRoomFragment fragment = new SixthRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SixthRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sixth_room, container, false);
        ButterKnife.bind(this, view);

//        Calendar c = new Calendar();
//        c.setKey(App.getInstance().getCurrentUser().getKey());
//        c.setAccount(App.getInstance().getCurrentUser().getAccount());
//        c.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
//        c.setDfrom(LocalDate.now().toString());
//        c.setDto(LocalDate.now().plusDays(30).toString());
////        c.setArr("");
//        c.setPriceId(App.getInstance().getData().getPrices().get(0).getId());
//        c.setRestrictionId(App.getInstance().getData().getRestrictions().get(0).getId());
//
//        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
//        webApiClient.getCalDetails(c).observe(this, new Observer<Calendar>() {
//            @Override
//            public void onChanged(Calendar calendar) {
//
//                if (calendar.getAvailabilityList() != null) {
//                    calendarList.clear();
//                    calendarList.addAll(calendar.getAvailabilityList().get(5).getData());
//                    calendarAdapter.notifyDataSetChanged();
//                } else {
//                    List<AvailabilityData> tmpList = new ArrayList<>();
//                    tmpList.addAll(calendar.getAvailabilityList().get(5).getData());
//                }
//            }
//        });

        calendarAdapter = new CalendarAdapter(getActivity(), calendarList);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalendar.setAdapter(calendarAdapter);

        calendarAdapter.setOnCalendarClickListener(new CalendarAdapter.OnCalendarClickListener() {
            @Override
            public void onCalendarClick(View view, int position, AvailabilityData av) {
                ivClose = view.findViewById(R.id.ivClose);
                llInfo = view.findViewById(R.id.llInfo);

                if (ivClose.getTag().equals("0")) {
                    llInfo.setVisibility(View.VISIBLE);
                    ivClose.setImageResource(R.drawable.ic_arrow_up);
                    ivClose.setTag("1");
                }
                else if (ivClose.getTag().equals("1")){
                    llInfo.setVisibility(View.GONE);
                    ivClose.setImageResource(R.drawable.ic_arrow_down);
                    ivClose.setTag("0");
                }
            }
        });

        calendarAdapter.setOnAvailClickListener(new CalendarAdapter.OnAvailClickListener() {
            @Override
            public void onAvailClick(View view, int position, AvailabilityData av) {
                TextView tvAvail = view.findViewById(R.id.tvNoAvail);
                etAvail = view.findViewById(R.id.etNoAvail);
                tvAvail.setVisibility(View.GONE);
                etAvail.setVisibility(View.VISIBLE);

                etAvail.setCursorVisible(true);
                etAvail.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etAvail, InputMethodManager.SHOW_IMPLICIT);

                etAvail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @SuppressLint("PrivateResource")
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (etAvail.getText().length() == 0) {
                            etAvail.setError("Polje ne sme biti prazno");
                        } else {
                            tvAvail.setText(etAvail.getText().toString());
                            etAvail.setVisibility(View.GONE);
                            tvAvail.setVisibility(View.VISIBLE);
                            Intent avails = new Intent(getActivity(), AvailabilitySnackBar.class);
                            avails.putExtra("datum", av.getDate());
                            avails.putExtra("roomID", id);
                            avails.putExtra("avail", tvAvail.getText().toString());
//                            Toast.makeText(getActivity(), tvAvail.getText().toString(), Toast.LENGTH_SHORT).show();
                            startActivityForResult(avails, 15);
                            etAvail.clearFocus();
                        }
                        return false;
                    }
                });

            }
        });

        calendarAdapter.setOnPriceClickListener(new CalendarAdapter.OnPriceClickListener() {
            @Override
            public void onPriceClick(View view, int position, AvailabilityData av) {
                tvPrice = view.findViewById(R.id.tvPrice);
                price = view.findViewById(R.id.etPrice);
                tvPrice.setVisibility(View.GONE);
                price.setVisibility(View.VISIBLE);

                price.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(price, InputMethodManager.SHOW_IMPLICIT);
                price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (price.getText().length() == 0) {
                            price.setError("Polje ne sme biti prazno");
                        } else {
                            tvPrice.setText(price.getText().toString());
                            price.setVisibility(View.GONE);
                            tvPrice.setVisibility(View.VISIBLE);

                            Intent i = new Intent(getActivity(), PriceSnackBar.class);
                            i.putExtra("datum", av.getDate());
                            i.putExtra("roomID", id);
                            i.putExtra("staraCena", av.getPrice().toString());
                            i.putExtra("cena", price.getText().toString());
                            getActivity().startActivityForResult(i, 12);
                        }
                        return false;
                    }
                });
            }
        });

        calendarAdapter.setOnStatusChangeListener(new CalendarAdapter.OnStatusChangeListener() {
            @Override
            public void onStatusChanged(View view, int position, AvailabilityData av) {

                status = view.findViewById(R.id.mbStatus);
                openClosure = view.findViewById(R.id.tvClosure);
                checkIn = view.findViewById(R.id.tvOnCheckIn);
                checkOut = view.findViewById(R.id.tvOnCheckOut);
                if (status.getTag().equals("0")) {
                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
                    status.setTag("1");
                    closedOptions = 1;
                    openClosure.setText("Zatvoreno");
                    checkIn.setFocusable(false);
                    checkOut.setFocusable(false);

                    Intent i = new Intent(getActivity(), ClosureSnackBar.class);
                    i.putExtra("datum", av.getDate());
                    i.putExtra("closedOptions", closedOptions);
                    i.putExtra("status", status.getTag().toString());
                    startActivityForResult(i, 11);
                } else if (status.getTag().equals("1")) {
                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
                    status.setTag("0");
                    closedOptions = 0;
                    openClosure.setText("Otvoreno");

                    Intent i = new Intent(getActivity(), ClosureSnackBar.class);
                    i.putExtra("datum", av.getDate());
                    i.putExtra("closedOptions", closedOptions);
                    i.putExtra("status", status.getTag().toString());
                    startActivityForResult(i, 11);
                }
            }
        });

//        calendarAdapter.setOnOpenClosureChangeListener(new CalendarAdapter.OnOpenClosureChangeListener() {
//            @Override
//            public void onOpenClosureChanged(View view, int position, AvailabilityData av) {
//                openClosure = view.findViewById(R.id.tvClosure);
//                llInfo = view.findViewById(R.id.llInfo);
//                Intent openClosure = new Intent(getActivity(), OpenClosureActivity.class);
//                startActivityForResult(openClosure, 22);
//            }
//        });

        calendarAdapter.setOnCheckInListener(new CalendarAdapter.OnCheckInListener() {
            @Override
            public void onCheckIn(View view, int position, AvailabilityData av) {
                checkIn = view.findViewById(R.id.tvOnCheckIn);
                vCheckIn = view.findViewById(R.id.vOnCheckIn);
                llInfo = view.findViewById(R.id.llInfo);
                Intent openClosure = new Intent(getActivity(), OpenClosureActivity.class);
                openClosure.putExtra("datum", av.getDate());
                startActivityForResult(openClosure, 21);
            }
        });

        calendarAdapter.setOnCheckOutListener(new CalendarAdapter.OnCheckOutListener() {
            @Override
            public void onCheckOut(View view, int position, AvailabilityData av) {
                checkOut = view.findViewById(R.id.tvOnCheckOut);
                vCheckOut = view.findViewById(R.id.vOnCheckOut);
                llInfo = view.findViewById(R.id.llInfo);
                Intent openClosure = new Intent(getActivity(), OpenClosureActivity.class);
                openClosure.putExtra("datum", av.getDate());
                startActivityForResult(openClosure, 20);
            }
        });

        calendarAdapter.setOtaClickListener(new CalendarAdapter.OtaClickListener() {
            @Override
            public void otaClick(View view, int position, AvailabilityData av) {
                ota = view.findViewById(R.id.tvOTA);
                Intent changeOta = new Intent(getActivity(), OtaActivity.class);
                changeOta.putExtra("datum", av.getDate());
                startActivityForResult(changeOta, 19);
            }
        });

        calendarAdapter.setMinStayClickLitener(new CalendarAdapter.MinStayClickLitener() {
            @Override
            public void minStayClick(View view, int position, AvailabilityData av) {
                minStay = view.findViewById(R.id.etMinStay);
                tvMinStay = view.findViewById(R.id.tvMinStay);

                tvMinStay.setVisibility(View.GONE);
                minStay.setVisibility(View.VISIBLE);

                minStay.setCursorVisible(true);
                minStay.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(minStay, InputMethodManager.SHOW_IMPLICIT);
                minStay.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (minStay.getText().length() == 0) {
                            minStay.setError("Polje ne sme biti prazno");
                        } else {
                            tvMinStay.setText(minStay.getText().toString());
                            minStay.setVisibility(View.GONE);
                            tvMinStay.setVisibility(View.VISIBLE);
                            Intent minStays = new Intent(getActivity(), RestrictionSnackBar.class);
                            minStays.putExtra("datum", av.getDate());
                            minStays.putExtra("minStay", minStay.getText().toString());
                            startActivityForResult(minStays, 18);
                        }
                        return false;
                    }
                });

            }
        });

        calendarAdapter.setMinStayArrClickLitener(new CalendarAdapter.MinStayArrClickLitener() {
            @Override
            public void minStayArrClick(View view, int position, AvailabilityData av) {
                minStayArr = view.findViewById(R.id.etMinStayArr);
                tvMinStayArr = view.findViewById(R.id.tvMinStayArr);

                tvMinStayArr.setVisibility(View.GONE);
                minStayArr.setVisibility(View.VISIBLE);

                minStayArr.setCursorVisible(true);
                minStayArr.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(minStayArr, InputMethodManager.SHOW_IMPLICIT);
                minStayArr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (minStayArr.getText().length() == 0) {
                            minStayArr.setError("Polje ne sme biti prazno");
                        } else {
                            tvMinStayArr.setText(minStayArr .getText().toString());
                            minStayArr.setVisibility(View.GONE);
                            tvMinStayArr.setVisibility(View.VISIBLE);
                            Intent minStayArrs = new Intent(getActivity(), RestrictionSnackBar.class);
                            minStayArrs.putExtra("datum", av.getDate());
                            minStayArrs.putExtra("minStayArr", minStayArr.getText().toString());
                            startActivityForResult(minStayArrs, 17);
                        }
                        return false;
                    }
                });
            }
        });

        calendarAdapter.setMaxStayClickLitener(new CalendarAdapter.MaxStayClickLitener() {
            @Override
            public void maxStayClick(View view, int position, AvailabilityData av) {
                maxStay = view.findViewById(R.id.etMaxStay);
                tvMaxStay = view.findViewById(R.id.tvMaxStay);

                tvMaxStay.setVisibility(View.GONE);
                maxStay.setVisibility(View.VISIBLE);

                maxStay.setCursorVisible(true);
                maxStay.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(maxStay, InputMethodManager.SHOW_IMPLICIT);
                maxStay.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (maxStay.getText().length() == 0) {
                            maxStay.setError("Polje ne sme biti prazno");
                        } else {
                            tvMaxStay.setText(maxStay.getText().toString());
                            maxStay.setVisibility(View.GONE);
                            tvMaxStay.setVisibility(View.VISIBLE);

                            Intent maxStays = new Intent(getActivity(), RestrictionSnackBar.class);
                            maxStays.putExtra("datum", av.getDate());
                            maxStays.putExtra("maxStay", maxStay.getText().toString());
                            startActivityForResult(maxStays, 16);
                        }
                        return false;
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("sendDateToChild");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                dateFromBroadcast = intent.getExtras().getString("date");
                changeFormat = getDate(dateFromBroadcast);

                Calendar c = new Calendar();
                c.setKey(App.getInstance().getCurrentUser().getKey());
                c.setAccount(App.getInstance().getCurrentUser().getAccount());
                c.setLcode(App.getInstance().getCurrentUser().getProperties().get(0).getLcode());
                c.setDfrom(changeFormat);
                c.setDto(LocalDate.now().plusDays(35).toString());
//                a.setArr("");
                c.setPriceId(App.getInstance().getData().getPrices().get(0).getId());
                c.setRestrictionId(App.getInstance().getData().getRestrictions().get(0).getId());

                WebApiManager.get(getContext()).getWebApi().calDetails(c).enqueue(new Callback<Calendar>() {
                    @Override
                    public void onResponse(Call<Calendar> call, Response<Calendar> response) {
                        if (response.isSuccessful()) {
                            calendarList.clear();
                            calendarList.addAll(response.body().getAvailabilityList().get(5).getData());
                            calendarAdapter.notifyDataSetChanged();
                        } else {}
                    }

                    @Override
                    public void onFailure(Call<Calendar> call, Throwable t) {
                        t.printStackTrace();
                        Timber.v("onFailure");
                    }
                });

            }
        };
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11) {
            if (resultCode == RESULT_CANCELED) {
                String tag = data.getStringExtra("tag");
//                Toast.makeText(getActivity(), tag, Toast.LENGTH_SHORT).show();
                if (tag.equals("1")) {
                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
                    status.setTag("0");
                    openClosure.setText("Otvoreno");
                } else  if (tag.equals("0")) {
                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
                    status.setTag("1");
                    openClosure.setText("Zatvoreno");
                }
            }

//            if (resultCode == RESULT_OK) {
//                String tag = data.getStringExtra("tag");
//                Toast.makeText(getActivity(), tag, Toast.LENGTH_SHORT).show();
//                if (tag.equals("1")) {
//                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
//                    status.setTag("0");
//                }
//                if (tag.equals("0")) {
//                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
//                    status.setTag("1");
//                }
//            }
        }

        if (requestCode == 12) {
            if (resultCode == RESULT_CANCELED) {
                price.setBackground(getResources().getDrawable(R.drawable.price_edit));
                price.setCursorVisible(false);
            }

            if (resultCode == RESULT_OK) {
                price.setBackground(getResources().getDrawable(R.drawable.price_edit));
                price.setCursorVisible(false);
                calendarAdapter.notifyDataSetChanged();
            }
        }

        //tvClosure
//        if (requestCode == 22) {
//            if (resultCode == RESULT_OK) {
//                openClosure.setText(getResources().getString(R.string.open));
//            } else if (resultCode == RESULT_CANCELED) {
//                openClosure.setText(getResources().getString(R.string.closed));
//            }
//
//            Intent closure = new Intent(getActivity(), ClosureSnackBar.class);
//            closure.putExtra("closure", openClosure.getText().toString());
//            startActivityForResult(closure, 222);
//
////            if (openClosure.getText().toString().equals(getResources().getString(R.string.closed))) {
////                status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
////                checkIn.setFocusable(false);
////                checkOut.setFocusable(false);
////            }
//        }
//
//
//        if (requestCode == 222) {
//            if (resultCode == RESULT_CANCELED) {
//                if (openClosure.getText().toString().equals("Zatvoreno")) {
//                    openClosure.setText("Otvoreno");
//                } else if (openClosure.getText().toString().equals("Otvoreno")) {
//                    openClosure.setText("Zatvoreno");
//                }
//            }
//
//            if (resultCode == RESULT_OK) {
//                llInfo.setVisibility(View.GONE);
//                ivClose.setImageResource(R.drawable.ic_arrow_down);
//                ivClose.setTag("0");
//            }
//        }


        //tvOnCheckIn
        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                datum = data.getStringExtra("datum");
                closure = data.getStringExtra("closure");
                checkIn.setText(closure);
                closedOptions = 0;
                vCheckIn.setVisibility(View.GONE);
            } else if (resultCode == RESULT_CANCELED) {
                datum = data.getStringExtra("datum");
                closure = data.getStringExtra("closure");
                checkIn.setText(closure);
                closedOptions = 1;
                vCheckIn.setVisibility(View.VISIBLE);
            }

//            String date = data.getStringExtra("datum");

            Intent closure = new Intent(getActivity(), RestrictionSnackBar.class);
            closure.putExtra("onCheckIn", checkIn.getText().toString());
            closure.putExtra("closed", closedOptions);
            closure.putExtra("datum", datum);
            startActivityForResult(closure, 221);
        }

        if (requestCode == 221) {
            if (resultCode == RESULT_CANCELED) {
                if (checkIn.getText().toString().equals("Zatvoreno")) {
                    checkIn.setText("Otvoreno");
                    closedOptions = 0;
                    vCheckIn.setVisibility(View.GONE);
                } else if (checkIn.getText().toString().equals("Otvoreno")) {
                    checkIn.setText("Zatvoreno");
                    closedOptions = 1;
                    vCheckIn.setVisibility(View.VISIBLE);
                }
            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
                ivClose.setImageResource(R.drawable.ic_arrow_down);
                ivClose.setTag("0");

                if (closure.equals("Zatvoreno")) {
                    vCheckIn.setVisibility(View.VISIBLE);
                }
            }
        }
        //tvOnCheckOut
        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                datum = data.getStringExtra("datum");
                closure = data.getStringExtra("closure");
                checkOut.setText(closure);
                closedOptions = 0;
                vCheckOut.setVisibility(View.GONE);
            } else if (resultCode == RESULT_CANCELED) {
                datum = data.getStringExtra("datum");
                closure = data.getStringExtra("closure");
                checkOut.setText(closure);
                closedOptions = 1;
                vCheckOut.setVisibility(View.VISIBLE);
            }

            Intent closure = new Intent(getActivity(), RestrictionSnackBar.class);
            closure.putExtra("onCheckOut", checkOut.getText().toString());
            closure.putExtra("closed", closedOptions);
            closure.putExtra("datum", datum);
            startActivityForResult(closure, 220);
        }


        if (requestCode == 220) {
            if (resultCode == RESULT_CANCELED) {
                if (checkOut.getText().toString().equals("Zatvoreno")) {
                    checkOut.setText("Otvoreno");
                    closedOptions = 0;
                    vCheckOut.setVisibility(View.GONE);
                } else if (checkOut.getText().toString().equals("Otvoreno")) {
                    checkOut.setText("Zatvoreno");
                    closedOptions = 1;
                    vCheckOut.setVisibility(View.VISIBLE);
                }
            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
                ivClose.setImageResource(R.drawable.ic_arrow_down);
                ivClose.setTag("0");

                if (closure.equals("Zatvoreno")) {
                    vCheckOut.setVisibility(View.VISIBLE);
                }
            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                datum = data.getStringExtra("datum");
                ota.setText(getResources().getString(R.string.yes));
                noOta = "1";
            } else if (resultCode == RESULT_CANCELED) {
                ota.setText(getResources().getString(R.string.no));
                noOta = "0";
            }

            Intent closure = new Intent(getActivity(), AvailabilitySnackBar.class);
            closure.putExtra("datum", datum);
            closure.putExtra("ota", noOta);
            startActivityForResult(closure, 219);
        }

        if (requestCode == 219) {
            if (resultCode == RESULT_CANCELED) {
                if (ota.getText().toString().equals("Ne")) {
                    ota.setText(getResources().getString(R.string.yes));
                } else if (ota.getText().toString().equals("Da")) {
                    ota.setText(getResources().getString(R.string.no));
                }
            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
                ivClose.setImageResource(R.drawable.ic_arrow_down);
                ivClose.setTag("0");
            }
        }

        if (requestCode == 18) {
            if (resultCode ==  RESULT_OK) {
            }

            if (resultCode == RESULT_CANCELED) {
            }
        }

//        if (requestCode == 218) {
//            if (resultCode == RESULT_CANCELED) {
//                minStay.setText(av.getAvailabilityList().get(0).getData().get(0).getRestriction());
//            }
//
//            if (resultCode == RESULT_OK) {
//                llInfo.setVisibility(View.GONE);
//                ivClose.setImageResource(R.drawable.ic_arrow_down);
//                ivClose.setTag("0");
//            }
//        }


//        if (requestCode == 17) {
//            if (resultCode ==  RESULT_OK) {
//                String date = data.getStringExtra("datum");
//                String day = data.getStringExtra("day");
//                minStayArr.setText(day);
//                Intent minStayArrs = new Intent(getActivity(), RestrictionSnackBar.class);
//                minStayArrs.putExtra("minStayArr", day);
//                minStayArrs.putExtra("datum", date);
//                startActivityForResult(minStayArrs, 217);
//            }
//
//        }

        if (requestCode == 217) {
            if (resultCode == RESULT_CANCELED) {

            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
                ivClose.setImageResource(R.drawable.ic_arrow_down);
                ivClose.setTag("0");
            }
        }

        if (requestCode == 16) {
            if (resultCode ==  RESULT_OK) {
//                assert data != null;
//                String date = data.getStringExtra("datum");
//                String day = data.getStringExtra("day");
//                maxStay.setText(day);
//                Intent maxStays = new Intent(getActivity(), RestrictionSnackBar.class);
//                maxStays.putExtra("maxStay", day);
//                maxStays.putExtra("dates", date);
//                startActivityForResult(maxStays, 216);
            }
        }

        if (requestCode == 216) {
            if (resultCode == RESULT_CANCELED) {

            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
                ivClose.setImageResource(R.drawable.ic_arrow_down);
                ivClose.setTag("0");
            }
        }
    }

    private void configureReceiver() {
        IntentFilter filter = new IntentFilter("sendDateToChild");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String dateFromBroadcast = intent.getExtras().getString("date");
                Toast.makeText(context, dateFromBroadcast, Toast.LENGTH_SHORT).show();
            }
        };
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
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
}
