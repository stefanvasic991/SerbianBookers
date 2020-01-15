package com.easyswitch.serbianbookers.views.home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.WebApiManager;
import com.easyswitch.serbianbookers.adapters.ChannelStatsAdapter;
import com.easyswitch.serbianbookers.adapters.RoomStatsAdapter;
import com.easyswitch.serbianbookers.models.Statistics;
import com.easyswitch.serbianbookers.models.StatsChannel;
import com.easyswitch.serbianbookers.models.StatsRoom;
import com.easyswitch.serbianbookers.models.User;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.filter.CalendarFilterActivity;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;

import org.threeten.bp.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by: Stefan Vasic
 */
public class StatisticsFragment extends Fragment {

    @BindView(R.id.mbResDateFrom)
    MaterialButton mbResDateFrom;
    @BindView(R.id.mbResDateTo)
    MaterialButton  mbResDateTo;
    @BindView(R.id.countryPieChart)
    PieChart countryPieChart;
    @BindView(R.id.channelPieChart)
    PieChart channelPieChart;
    @BindView(R.id.combinedIncomeChart)
    CombinedChart incomeChart;
    @BindView(R.id.combinedNightChart)
    CombinedChart nightChart;

    @BindView(R.id.country_channels)
    TextView countryChannels;
    @BindView(R.id.unit_channels)
    TextView unitChannelsl;
    @BindView(R.id.units)
    TextView units;
    @BindView(R.id.ivCountryLeft)
    ImageView ivCountryLeft;
    @BindView(R.id.ivCountryRight)
    ImageView ivCountryRight;
    @BindView(R.id.ivIncomeLeft)
    ImageView ivIncomeLeft;
    @BindView(R.id.ivIncomeRight)
    ImageView ivIncomeRight;
    @BindView(R.id.byIncome)
    TextView byIncome;

    @BindView(R.id.rvRoomStats)
    RecyclerView rvRoomStats;
    @BindView(R.id.rvChannelStats)
    RecyclerView rvChannelStats;

    @BindView(R.id.unit_channel)
    TextView unitChannel;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.five)
    TextView five;

    private RoomStatsAdapter adapter;
    private ChannelStatsAdapter channelAdapter;
    private List<StatsRoom> statsRoomList = new ArrayList<>();
    private List<StatsChannel> statsChannelList = new ArrayList<>();
    private ArrayList<PieEntry> countryPieEntries = new ArrayList<>();
    private ArrayList<PieEntry> channelPieEntries = new ArrayList<>();
    private ArrayList<Entry> lineEntries = new ArrayList<>();
    private ArrayList<BarEntry> barEntries = new ArrayList<>();
    private ArrayList<Entry> lineNightEntries = new ArrayList<>();
    private ArrayList<BarEntry> barNightEntries = new ArrayList<>();
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateParse = new SimpleDateFormat("dd.MM.yyyy.");
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    String[] amount = {"0", "10000", "20000", "30000"};

    String[] months = new String[] {
                            "Jan", "Feb", "Mar", "Apr", "Maj", "Jun", "Jul", "Avg", "Sep", "Oct", "Nov", "Dec"
                    };

    User u;

    public static StatisticsFragment newInstance() {

        Bundle args = new Bundle();

        StatisticsFragment fragment = new StatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this, view);

        String dFrom = LocalDate.now().minusDays(365).toString();
        String dTo = LocalDate.now().toString();

        u = getActivity().getIntent().getParcelableExtra("currentUser");
        countryPieChart.setVisibility(View.VISIBLE);
        rvRoomStats.setVisibility(View.VISIBLE);
        incomeChart.setVisibility(View.VISIBLE);
        mbResDateFrom.setText(getDateOnStart(dFrom));
//        Toast.makeText(getActivity(), dFrom, Toast.LENGTH_SHORT).show();
        mbResDateTo.setText(getDateOnStart(dTo));

        setPieChart();
        setCombinedChart();

        Statistics stats = new Statistics();
        stats.setKey(u.getKey());
        stats.setAccount(u.getAccount());
        stats.setLcode(u.getProperties().get(0).getLcode());
        stats.setDfrom(dFrom);
        stats.setDto(dTo);
        stats.setFilterBy("date_arrival");

        WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
        webApiClient.getStatistics(stats).observe(this, new Observer<Statistics>() {
            @SuppressLint("ResourceType")
            @Override
            public void onChanged(Statistics statistics) {

                if (statistics == null) return;

                if (statistics.getData().getRooms() != null) {
                    statsRoomList.clear();
                    statsRoomList.addAll(statistics.getData().getRooms());
                    adapter.notifyDataSetChanged();
                } else {
                    List<StatsRoom> tmpList = new ArrayList<>();
                    tmpList.addAll(statistics.getData().getRooms());
                }

                if (statistics.getData().getChannels() != null) {
                    statsChannelList.clear();
                    statsChannelList.addAll(statistics.getData().getChannels());
                    channelAdapter.notifyDataSetChanged();
                } else {
                    List<StatsChannel> tmpList = new ArrayList<>();
                    tmpList.addAll(statistics.getData().getChannels());
                }

                for (int i = 0; i < statistics.getData().getCountriesPercentage().size() ; i++) {

                    String[] countries = new String[] {
                            statistics.getData().getCountriesPercentage().get(i).getCountry()
                    };

                    countryPieEntries.add(new PieEntry(statistics.getData().getCountriesPercentage().get(i).getValue(),
                            countries[i % countries.length]));

                    generateCountyPie();

                }

                for (int ii = 0; ii < statistics.getData().getChannelsPercentage().size() ; ii++) {

                    String[] channels = new String[] {
                            statistics.getData().getChannelsPercentage().get(ii).getChannel()
                    };

                    channelPieEntries.add(new PieEntry(statistics.getData().getChannelsPercentage().get(ii).getValue().intValue(),
                            channels[ii % channels.length]));

                    generateChannelsPie();
                }

                //CombinedChart
                for (int i = 0; i < statistics.getData().getMonths().size(); i++) {
                    lineEntries.add(new Entry(i, statistics.getData().getMonths().get(i).getAvgIncome().floatValue() + 0.5f));
                    barEntries.add(new BarEntry(i, statistics.getData().getMonths().get(i).getIncome().floatValue()));
                    setCombinedChart();

                    lineNightEntries.add(new Entry(i, statistics.getData().getMonths().get(i).getAvgIncome().floatValue()));
                    barNightEntries.add(new BarEntry(i, statistics.getData().getMonths().get(i).getNights()));
                    setCombinedNightChart();
                }
            }
        });

        adapter = new RoomStatsAdapter(getContext(), statsRoomList);
        rvRoomStats.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRoomStats.setAdapter(adapter);

        channelAdapter = new ChannelStatsAdapter(getContext(), statsChannelList);
        rvChannelStats.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChannelStats.setAdapter(channelAdapter);

        return view;
    }

    private void setCombinedChart() {

        incomeChart.getDescription().setEnabled(false);
        incomeChart.setDrawGridBackground(false);
        incomeChart.setDrawBarShadow(false);
        incomeChart.setHighlightFullBarEnabled(false);

        incomeChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });
        Legend l = incomeChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = incomeChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(getResources().getColor(R.color.colorDataBackground));
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = incomeChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = incomeChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return months[(int) value % months.length];
            }
        });
        CombinedData combinedData = new CombinedData();

        combinedData.setData(generateLineData());
        combinedData.setData(generateBarData());

        xAxis.setAxisMaximum(combinedData.getXMax() + 0.25f);

        incomeChart.setData(combinedData);
        incomeChart.invalidate();
    }

    private void setCombinedNightChart() {

        nightChart.getDescription().setEnabled(false);
        nightChart.setDrawGridBackground(false);
        nightChart.setDrawBarShadow(false);
        nightChart.setHighlightFullBarEnabled(false);

        nightChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });
        Legend l = nightChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = nightChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(getResources().getColor(R.color.colorDataBackground));
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = nightChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = nightChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return months[(int) value % months.length];
            }
        });
        CombinedData combinedData = new CombinedData();

        combinedData.setData(generateNightLineData());
        combinedData.setData(generateNightBarData());

        xAxis.setAxisMaximum(combinedData.getXMax() + 0.25f);

        nightChart.setData(combinedData);
        nightChart.invalidate();
    }

    private void setPieChart() {

        //countryPieChart
        countryPieChart.setUsePercentValues(true);
        countryPieChart.getDescription().setEnabled(false);
        countryPieChart.setExtraOffsets(5, 10, 5, 5);
        countryPieChart.setDragDecelerationFrictionCoef(0.95f);
        countryPieChart.setHoleRadius(0);
        countryPieChart.setEntryLabelTextSize(10f);

        Legend l = countryPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        Legend cl = channelPieChart.getLegend();
        cl.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        cl.setOrientation(Legend.LegendOrientation.VERTICAL);
        cl.setDrawInside(false);
        cl.setXEntrySpace(0f);
        cl.setYEntrySpace(0f);
        cl.setYOffset(0f);


    }

    private LineData generateLineData() {

        LineData lineData = new LineData();

        LineDataSet lineDataSet = new LineDataSet(lineEntries, getResources().getString(R.string.avIncome));
        lineDataSet.setColor(getResources().getColor(R.color.lineChart));
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setFillColor(Color.rgb(240, 238, 70));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.colorWhite));

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineData.addDataSet(lineDataSet);

        return lineData;
    }

    private LineData generateNightLineData() {

        LineData lineData = new LineData();

//        for (int index = 0; index < 12; index++)
//            lineEntries.add(new Entry(index + 0.5f, getRandom(15, 5)));

        LineDataSet lineDataSet = new LineDataSet(lineNightEntries, getResources().getString(R.string.avIncome));
        lineDataSet.setColor(getResources().getColor(R.color.lineChart));
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setFillColor(Color.rgb(240, 238, 70));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.colorWhite));

        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineData.addDataSet(lineDataSet);

        return lineData;
    }

    private BarData generateBarData() {

        BarDataSet set1 = new BarDataSet(barEntries, getResources().getString(R.string.incomee));
        set1.setColor(getResources().getColor(R.color.colorBlue));
        set1.setValueTextColor(Color.WHITE);
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(barEntries, "");
        set2.setColors(getResources().getColor(R.color.colorBlue));
        set2.setValueTextColor(Color.WHITE);
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    private BarData generateNightBarData() {

        BarDataSet set1 = new BarDataSet(barNightEntries, getResources().getString(R.string.noNights));
        set1.setColor(getResources().getColor(R.color.colorBlue));
        set1.setValueTextColor(Color.WHITE);
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarDataSet set2 = new BarDataSet(barNightEntries, "");
        set2.setColors(getResources().getColor(R.color.colorBlue));
        set2.setValueTextColor(Color.WHITE);
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1, set2);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    @SuppressLint("ResourceType")
    private PieData generateCountyPie() {

        //PieChartCountry
        PieDataSet countryDataSet = new PieDataSet(countryPieEntries, "");
        countryDataSet.setSelectionShift(2f);

        countryDataSet.setColors(
                Color.parseColor(getResources().getString(R.color.colorDarkRed)),
                Color.parseColor(getResources().getString(R.color.colorDarkBlue)),
                Color.parseColor(getResources().getString(R.color.colorBlue)),
                Color.parseColor(getResources().getString(R.color.colorDarkPurple)),
                Color.parseColor(getResources().getString(R.color.colorPurple)),
                Color.parseColor(getResources().getString(R.color.colorDarkGreen)),
                Color.parseColor(getResources().getString(R.color.colorGreen)),
                Color.parseColor(getResources().getString(R.color.cardView)),
                Color.parseColor(getResources().getString(R.color.colorYellow))
        );

        PieData data = new PieData(countryDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setDrawValues(false);

        countryPieChart.setDrawHoleEnabled(false);
        countryPieChart.setDrawSliceText(false);
        countryPieChart.getDescription().setEnabled(false);
        countryPieChart.setData(data);

        return data;
    }

    @SuppressLint("ResourceType")
    private PieData generateChannelsPie() {

        //PieChartChannels
        PieDataSet channelpieDataSet = new PieDataSet(channelPieEntries, "");
        channelpieDataSet.setSelectionShift(2f);

        channelpieDataSet.setColors(
                Color.parseColor(getResources().getString(R.color.colorBlue)),
                Color.parseColor(getResources().getString(R.color.colorDarkBlue)),
                Color.parseColor(getResources().getString(R.color.colorGreen)),
                Color.parseColor(getResources().getString(R.color.colorDarkPurple)),
                Color.parseColor(getResources().getString(R.color.colorPurple)),
                Color.parseColor(getResources().getString(R.color.colorDarkRed)),
                Color.parseColor(getResources().getString(R.color.colorDarkGreen)),
                Color.parseColor(getResources().getString(R.color.colorYellow))
        );

        PieData dataChannel = new PieData(channelpieDataSet);
        dataChannel.setValueFormatter(new PercentFormatter());
        dataChannel.setDrawValues(false);

        channelPieChart.setDrawHoleEnabled(false);
        channelPieChart.setDrawSliceText(false);
        channelPieChart.getDescription().setEnabled(false);
        channelPieChart.setData(dataChannel);

        return dataChannel;
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        String dFrom = data.getStringExtra("data");
        String dTo = data.getStringExtra("data");


        if (requestCode == 1) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                mbResDateFrom.setText(dFrom);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
            }
        }

        if (requestCode == 2) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                mbResDateTo.setText(dTo);
                mbResDateTo.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateTo.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);


            }
        }

        String dateFrom = getDate(dFrom);
        String dateTo = getDate(dTo);

        Statistics stats = new Statistics();
        stats.setKey(u.getKey());
        stats.setAccount(u.getAccount());
        stats.setLcode(u.getProperties().get(0).getLcode());
        stats.setDfrom(dateFrom);
        stats.setDto(dateTo);
        stats.setFilterBy("date_arrival");

        WebApiManager.get(getActivity()).getWebApi().statistics(stats).enqueue(new Callback<Statistics>() {
            @Override
            public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                if (response.isSuccessful()) {
                    statsRoomList.clear();
                    statsRoomList.addAll(response.body().getData().getRooms());
                    adapter.notifyDataSetChanged();



                    for (int i = 0; i < response.body().getData().getCountriesPercentage().size() ; i++) {

                        String[] countries = new String[] {
                                response.body().getData().getCountriesPercentage().get(i).getCountry()
                        };

                        countryPieEntries.clear();
                        countryPieEntries.add(new PieEntry(response.body().getData().getCountriesPercentage().get(i).getValue(),
                                countries[i % countries.length]));

                        generateCountyPie();

                    }

                    for (int ii = 0; ii < response.body().getData().getChannelsPercentage().size() ; ii++) {

                        String[] channels = new String[] {
                                response.body().getData().getChannelsPercentage().get(ii).getChannel()
                        };

                        channelPieEntries.clear();
                        channelPieEntries.add(new PieEntry(response.body().getData().getChannelsPercentage().get(ii).getValue().intValue(),
                                channels[ii % channels.length]));

                        generateChannelsPie();
                    }

                    //CombinedChart
                    for (int i = 0; i < response.body().getData().getMonths().size(); i++) {
                        lineEntries.clear();
                        lineEntries.add(new Entry(i, response.body().getData().getMonths().get(i).getAvgIncome().floatValue() + 0.5f));
                        barEntries.clear();
                        barEntries.add(new BarEntry(i, response.body().getData().getMonths().get(i).getIncome().floatValue()));
                        setCombinedChart();

                        lineNightEntries.clear();
                        lineNightEntries.add(new Entry(i, response.body().getData().getMonths().get(i).getAvgIncome().floatValue()));
                        barNightEntries.clear();
                        barNightEntries.add(new BarEntry(i, response.body().getData().getMonths().get(i).getNights()));
                        setCombinedNightChart();
                    }
                }
            }

            @Override
            public void onFailure(Call<Statistics> call, Throwable t) {

            }
        });
    }

    private String getDate(String date) {
        try {
            Date d = dateParse.parse(date);

            return dateFormat.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private String getDateOnStart(String date) {
        try {
            Date d = dateFormat.parse(date);

            return dateParse.format(d);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
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

    @OnClick(R.id.ivIncomeLeft)
    public void incomeLeft() {
        byIncome.setText(getResources().getString(R.string.incomee));
        incomeChart.setVisibility(View.VISIBLE);
        nightChart.setVisibility(View.GONE);
    }

    @OnClick(R.id.ivIncomeRight)
    public void incomeRight() {
        byIncome.setText(getResources().getString(R.string.noNights));
        incomeChart.setVisibility(View.GONE);
        nightChart.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.ivCountryLeft)
    public void pieClickLeft() {
        countryPieChart.setVisibility(View.VISIBLE);
        channelPieChart.setVisibility(View.GONE);
        countryChannels.setText(getResources().getString(R.string.byCountries));
    }

    @OnClick(R.id.ivCountryRight)
    public void pieClickRight() {
        countryPieChart.setVisibility(View.GONE);
        channelPieChart.setVisibility(View.VISIBLE);
        countryChannels.setText(getResources().getString(R.string.byChannels));
    }

    @OnClick(R.id.ivUnitLeft)
    public void clickLeft() {
        unitChannelsl.setText(getResources().getString(R.string.byUnits));
        one.setText(getResources().getString(R.string.noRes));
        two.setText(getResources().getString(R.string.noincome));
        three.setText(getResources().getString(R.string.noNights));
        four.setText(getResources().getString(R.string.midPrice));
        five.setText(getResources().getString(R.string.avNights));

        rvRoomStats.setVisibility(View.VISIBLE);
        rvChannelStats.setVisibility(View.GONE);
    }

    @OnClick(R.id.ivUnitRight)
    public void Right() {
        unitChannelsl.setText(getResources().getString(R.string.byChannels));
        one.setText(getResources().getString(R.string.cnlNoRes));
        two.setText(getResources().getString(R.string.cnlIncome));
        three.setText(getResources().getString(R.string.cnlCosts));
        four.setText(getResources().getString(R.string.cnlEarnings));
        five.setText(getResources().getString(R.string.cnlCancel));

        rvRoomStats.setVisibility(View.GONE);
        rvChannelStats.setVisibility(View.VISIBLE);
    }
}
