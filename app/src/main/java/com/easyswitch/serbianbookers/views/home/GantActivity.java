package com.easyswitch.serbianbookers.views.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.adapters.GanttTableAdapter;
import com.easyswitch.serbianbookers.gantogram.model.GanttData;
import com.easyswitch.serbianbookers.views.NavigationViewActivity;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class GantActivity extends AppCompatActivity {
    private static final String TAG = GantActivity.class.getSimpleName();
    private GanttData ganttData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gant);
        ButterKnife.bind(this);
        ganttData = GanttData.getGanttData();
        if (ganttData != null)
            setGanttTable();
        else
            Timber.e(TAG, "Gantt Data Null, Please Set Gantt Data.");
    }

    private void setGanttTable() {
        TableFixHeaders tableFixHeaders = findViewById(R.id.table);
        GanttTableAdapter baseTableAdapter = new GanttTableAdapter(this, ganttData);
        tableFixHeaders.setAdapter(baseTableAdapter);
    }

    @OnClick(R.id.fab)
    public void back() {
        onBackPressed();
        finish();
    }

    @OnClick(R.id.navigationViewBtn)
    public void openNavigationView() {
        Intent i = new Intent(this, NavigationViewActivity.class);
        startActivityForResult(i, 200);
    }

}
