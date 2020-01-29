package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.easyswitch.serbianbookers.App;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.PriceAdapter;
import com.easyswitch.serbianbookers.adapters.ReservationAdapter;
import com.easyswitch.serbianbookers.adapters.RestrictionAdapter;
import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.DataBody;
import com.easyswitch.serbianbookers.models.Price;
import com.easyswitch.serbianbookers.models.Restrictions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestrictionPlanDialog extends AppCompatActivity {

    @BindView(R.id.rvRoomList)
    RecyclerView rvRoomList;
    List<Restrictions> restrictionsList = new ArrayList<>();
    RestrictionAdapter adapter;
    String rName, rID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_dialog);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        restrictionsList.clear();
        restrictionsList.addAll(App.getInstance().getData().getRestrictions());

        adapter = new RestrictionAdapter(this, restrictionsList);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        rvRoomList.setAdapter(adapter);

        adapter.setOnRestrictionClickListener(new RestrictionAdapter.OnRestrictionClickListener() {
            @Override
            public void onRestrictionPlanClick(View view, int position, Restrictions r) {
                Restrictions clickedRestriction = restrictionsList.get(position);
                rName = clickedRestriction.getName();
                rID = clickedRestriction.getId();
                Intent i = new Intent();
                i.putExtra("restrictionPlanName", rName);
                i.putExtra("restrictionPlanID", rID);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    @OnClick(R.id.tvCancel)
    public void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

//    @OnClick(R.id.tvOkej)
//    public void okej() {
//        setResult(RESULT_OK);
//        finish();
//    }
}
