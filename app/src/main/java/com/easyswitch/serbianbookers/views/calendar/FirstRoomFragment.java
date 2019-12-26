package com.easyswitch.serbianbookers.views.calendar;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.CalendarAdapter;
import com.easyswitch.serbianbookers.models.Availability;
import com.easyswitch.serbianbookers.models.AvailabilityData;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.dialog.ClosureSnackBar;
import com.easyswitch.serbianbookers.views.dialog.OpenClosureActivity;
import com.easyswitch.serbianbookers.views.dialog.OtaActivity;
import com.easyswitch.serbianbookers.views.dialog.PriceSnackBar;
import com.easyswitch.serbianbookers.views.dialog.SnackBarDialog;
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

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by: Stefan Vasic
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class FirstRoomFragment extends Fragment {

    @BindView(R.id.rvCalendar)
    RecyclerView rvCalendar;

    List<AvailabilityData> calendarList = new ArrayList<>();
    CalendarAdapter calendarAdapter;
    User u;
    Availability av = new Availability();
    BroadcastReceiver broadcastReceiver;
    String dateFromBroadcast, changeFormat;

    EditText price;
    MaterialButton status;
    private  TextView tvPrice, openClosure, checkIn, checkOut, ota, minStay, minStayArr, maxStay;
    View vCheckIn, vCheckOut;
    LinearLayout llInfo;

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateParse = new SimpleDateFormat("dd.MM.yyyy.");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static FirstRoomFragment newInstance() {

        Bundle args = new Bundle();

        FirstRoomFragment fragment = new FirstRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FirstRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jednosobni, container, false);
        ButterKnife.bind(this, view);

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        assert u != null;
        av.setKey(u.getKey());
        av.setAccount(u.getAccount());
        av.setLcode(u.getProperties().get(0).getLcode());
        av.setDfrom(LocalDate.now().toString());
        av.setDto(LocalDate.now().plusDays(60).toString());
        av.setArr("");
        Intent intent = new Intent("send");



        WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
        webApiClient.getAvailability(av).observe(this, new Observer<Availability>() {
            @Override
            public void onChanged(Availability availability) {

                if (availability == null) return;

                        if (availability.getAvailabilityList() != null) {
                            calendarList.clear();
                            calendarList.addAll(availability.getAvailabilityList().get(0).getData());
                            calendarAdapter.notifyDataSetChanged();
                        } else {
                            List<AvailabilityData> tmpList = new ArrayList<>();
                            tmpList.addAll(availability.getAvailabilityList().get(0).getData());
                            calendarAdapter.notifyDataSetChanged();
                        }

            }
        });

        calendarAdapter = new CalendarAdapter(getActivity(), calendarList);
        rvCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalendar.setAdapter(calendarAdapter);

        calendarAdapter.setOnPriceClickListener(new CalendarAdapter.OnPriceClickListener() {
            @Override
            public void onPriceClick(View view, int position, AvailabilityData av) {
//                tvPrice = view.findViewById(R.id.tvPrice);
                price = view.findViewById(R.id.etPrice);

                price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Intent i = new Intent(getActivity(), PriceSnackBar.class);
                        i.putExtra("datum", av.getDate());
                        i.putExtra("staraCena", av.getPrice().toString());
                        i.putExtra("cena", price.getText().toString());
                        getActivity().startActivityForResult(i, 12);
                        return false;
                    }
                });
            }
        });

        calendarAdapter.setOnStatusChangeListener(new CalendarAdapter.OnStatusChangeListener() {
            @Override
            public void onStatusChanged(View view, int position, AvailabilityData av) {

                status = view.findViewById(R.id.mbStatus);
                if (status.getTag().equals("0")) {
                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
                    status.setTag("1");

                    Intent i = new Intent(getActivity(), SnackBarDialog.class);
                    i.putExtra("currentUser", u);
                    i.putExtra("status", status.getTag().toString());
                    startActivityForResult(i, 11);
                } else if (status.getTag().equals("1")) {
                    status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
                    status.setTag("0");
                }
            }
        });

        calendarAdapter.setOnOpenClosureChangeListener(new CalendarAdapter.OnOpenClosureChangeListener() {
            @Override
            public void onOpenClosureChanged(View view, int position, AvailabilityData av) {
                openClosure = view.findViewById(R.id.tvClosure);
                llInfo = view.findViewById(R.id.llInfo);
                Intent openClosure = new Intent(getActivity(), OpenClosureActivity.class);
                startActivityForResult(openClosure, 22);
            }
        });

        calendarAdapter.setOnCheckInListener(new CalendarAdapter.OnCheckInListener() {
            @Override
            public void onCheckIn(View view, int position, AvailabilityData av) {
                checkIn = view.findViewById(R.id.tvOnCheckIn);
                vCheckIn = view.findViewById(R.id.vOnCheckIn);
                llInfo = view.findViewById(R.id.llInfo);
                Intent openClosure = new Intent(getActivity(), OpenClosureActivity.class);
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
                startActivityForResult(openClosure, 20);
            }
        });

        calendarAdapter.setOtaClickListener(new CalendarAdapter.OtaClickListener() {
            @Override
            public void otaClick(View view, int position, AvailabilityData av) {
                ota = view.findViewById(R.id.tvOTA);
                Intent changeOta = new Intent(getActivity(), OtaActivity.class);
                startActivityForResult(changeOta, 19);
            }
        });

        calendarAdapter.setMinStayClickLitener(new CalendarAdapter.MinStayClickLitener() {
            @Override
            public void minStayClick(View view, int position, AvailabilityData av) {
                minStay = view.findViewById(R.id.tvMinStay);
                minStay.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Intent minStays = new Intent(getActivity(), ClosureSnackBar.class);
                        minStays.putExtra("minStay", minStay.getText().toString());
                        startActivityForResult(minStays, 18);
                        return false;
                    }
                });

            }
        });

        calendarAdapter.setMinStayArrClickLitener(new CalendarAdapter.MinStayArrClickLitener() {
            @Override
            public void minStayArrClick(View view, int position, AvailabilityData av) {
                minStayArr = view.findViewById(R.id.tvMinStayArr);
                minStayArr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Intent minStaysArr = new Intent(getActivity(), ClosureSnackBar.class);
                        minStaysArr.putExtra("minStayArr", minStayArr.getText());
                        startActivityForResult(minStaysArr, 17);
                        return false;
                    }
                });

            }
        });

        calendarAdapter.setMaxStayClickLitener(new CalendarAdapter.MaxStayClickLitener() {
            @Override
            public void maxStayClick(View view, int position, AvailabilityData av) {
                maxStay = view.findViewById(R.id.tvMaxStay);
                maxStay.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Intent maxStays = new Intent(getActivity(), ClosureSnackBar.class);
                        maxStays.putExtra("maxStay", maxStay.getText().toString());
                        startActivityForResult(maxStays, 16);
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
                Toast.makeText(getActivity(), changeFormat, Toast.LENGTH_SHORT).show();
                assert u != null;

                Availability a = new Availability();
                a.setKey(u.getKey());
                a.setAccount(u.getAccount());
                a.setLcode(u.getProperties().get(0).getLcode());
                a.setDfrom(changeFormat);
                a.setDto(LocalDate.now().plusDays(35).toString());
                a.setArr("");

                WebApiClient webApiClient = ViewModelProviders.of(getActivity()).get(WebApiClient.class);
                webApiClient.getAvailability(a).observe(getActivity(), new Observer<Availability>() {
                    @Override
                    public void onChanged(Availability availability) {

                        if (availability == null) return;

                        calendarList.clear();
                        calendarAdapter.notifyDataSetChanged();
                        calendarList.addAll(availability.getAvailabilityList().get(0).getData());
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
                status.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
            }
        }

        if (requestCode == 12) {
            if (resultCode == RESULT_CANCELED) {
                String oldPrice = data.getStringExtra("oldPrice");
                price.setText(oldPrice);
                Toast.makeText(getActivity(), oldPrice, Toast.LENGTH_SHORT).show();
            }
        }
        //tvClosure
        if (requestCode == 22) {
            if (resultCode == RESULT_OK) {
                openClosure.setText(getResources().getString(R.string.open));
            } else if (resultCode == RESULT_CANCELED) {
                openClosure.setText(getResources().getString(R.string.closed));
            }

            Intent closure = new Intent(getActivity(), ClosureSnackBar.class);
            closure.putExtra("closure", openClosure.getText().toString());
            startActivityForResult(closure, 222);
        }


        if (requestCode == 222) {
            if (resultCode == RESULT_CANCELED) {
                if (openClosure.getText().toString().equals("Zatvoreno")) {
                    openClosure.setText("Otvoreno");
                } else if (openClosure.getText().toString().equals("Otvoreno")) {
                    openClosure.setText("Zatvoreno");
                }
            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
            }
        }


        //tvOnCheckIn
        if (requestCode == 21) {
            if (resultCode == RESULT_OK) {
                checkIn.setText(getResources().getString(R.string.open));
                vCheckIn.setVisibility(View.GONE);
            } else if (resultCode == RESULT_CANCELED) {
                checkIn.setText(getResources().getString(R.string.closed));
                vCheckIn.setVisibility(View.VISIBLE);
            }

            Intent closure = new Intent(getActivity(), ClosureSnackBar.class);
            startActivityForResult(closure, 221);
        }

        if (requestCode == 221) {
            if (resultCode == RESULT_CANCELED) {
                if (checkIn.getText().toString().equals("Zatvoreno")) {
                    checkIn.setText("Otvoreno");
                    vCheckIn.setVisibility(View.GONE);
                } else if (checkIn.getText().toString().equals("Otvoreno")) {
                    checkIn.setText("Zatvoreno");
                    vCheckIn.setVisibility(View.VISIBLE);
                }
            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
            }
        }
        //tvOnCheckOut
        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                checkOut.setText(getResources().getString(R.string.open));
                vCheckOut.setVisibility(View.GONE);
            } else if (resultCode == RESULT_CANCELED) {
                checkOut.setText(getResources().getString(R.string.closed));
                vCheckOut.setVisibility(View.VISIBLE);
            }

            Intent closure = new Intent(getActivity(), ClosureSnackBar.class);
            startActivityForResult(closure, 220);
        }


        if (requestCode == 220) {
            if (resultCode == RESULT_CANCELED) {
                if (checkOut.getText().toString().equals("Zatvoreno")) {
                    checkOut.setText("Otvoreno");
                    vCheckOut.setVisibility(View.GONE);
                } else if (checkOut.getText().toString().equals("Otvoreno")) {
                    checkOut.setText("Zatvoreno");
                    vCheckOut.setVisibility(View.VISIBLE);
                }
            }

            if (resultCode == RESULT_OK) {
                llInfo.setVisibility(View.GONE);
            }
        }

        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                ota.setText(getResources().getString(R.string.yes));
            } else if (resultCode == RESULT_CANCELED) {
                ota.setText(getResources().getString(R.string.no));
            }

            Intent closure = new Intent(getActivity(), ClosureSnackBar.class);
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
