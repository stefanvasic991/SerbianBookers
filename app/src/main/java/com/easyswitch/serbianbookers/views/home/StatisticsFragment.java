package com.easyswitch.serbianbookers.views.home;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.easyswitch.serbianbookers.views.filter.CalendarFilterActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        countryPieChart.setUsePercentValues(true);
        countryPieChart.getDescription().setEnabled(false);
        countryPieChart.setExtraOffsets(5, 10, 5, 5);
        countryPieChart.setDragDecelerationFrictionCoef(0.95f);
        countryPieChart.setHoleRadius(0);

        Legend l = countryPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);


        channelPieChart.setUsePercentValues(true);
        channelPieChart.getDescription().setEnabled(false);
        channelPieChart.setExtraOffsets(5, 10, 5, 5);
        channelPieChart.setDragDecelerationFrictionCoef(0.95f);
        channelPieChart.setHoleRadius(0);

        Legend lg = countryPieChart.getLegend();
        lg.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        lg.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        lg.setOrientation(Legend.LegendOrientation.VERTICAL);
        lg.setDrawInside(false);
        lg.setXEntrySpace(7f);
        lg.setYEntrySpace(0f);
        lg.setYOffset(0f);

        setCountries(6, 10);
        setChannels(5, 10);

        return view;
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(getActivity(), NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }

    private void setCountries(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        String[] countries = new String[]{"Crna Gora", "Srbija", "Nemacka", "Austrija", "Grcka", "Ostale"};
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 6),
                    countries[i % countries.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(1f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        countryPieChart.setData(data);

        // undo all highlights
//        countryPieChart.highlightValues(null);
//
//        countryPieChart.invalidate();
    }

    private void setChannels(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        String[] channels = new String[]{"Booking", "AirBnb", "TripAdvisor", "Expedia", "Ostali"};
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    channels[i % channels.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(1f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        channelPieChart.setData(data);

        // undo all highlights
//        countryPieChart.highlightValues(null);
//
//        countryPieChart.invalidate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
                String status = data.getStringExtra("data");
                mbResDateFrom.setText(status);
                mbResDateFrom.setTextColor(getResources().getColor(R.color.colorWhite));
                mbResDateFrom.getBackground().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
            }
        }

        if (requestCode == 2) {
            if (resultCode == CalendarFilterActivity.RESULT_OK) {
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
}
