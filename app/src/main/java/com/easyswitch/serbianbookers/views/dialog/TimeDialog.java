package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.WebApiClient;
import com.easyswitch.serbianbookers.adapters.ReservationAdapter;
import com.easyswitch.serbianbookers.models.News;
import com.easyswitch.serbianbookers.models.Reservation;
import com.easyswitch.serbianbookers.models.User;
import com.google.android.material.button.MaterialButton;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyswitch.serbianbookers.Consts.TODAY;
import static com.easyswitch.serbianbookers.Consts.TODAYiTOMMOROW;
import static com.easyswitch.serbianbookers.Consts.TOMMOROW;
import static com.easyswitch.serbianbookers.Consts.YESTERDAY;
import static com.easyswitch.serbianbookers.Consts.YESTERDAYiTODAY;

public class TimeDialog extends AppCompatActivity {

    @BindView(R.id.ivClose)
    ImageView ivCLose;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.cbOne)
    CheckBox cbOne;

    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.cbTwo)
    CheckBox cbTwo;

    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.cbThree)
    CheckBox cbThree;

    @BindView(R.id.btnFilter)
    MaterialButton btnFilter;

    String checkedDays;
    List<CheckBox> checkBoxList = new ArrayList<>();
    User u;


    ArrayList<Reservation> reservationList = new ArrayList<>();
    private ReservationAdapter reservationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Dialog);
        setContentView(R.layout.activity_time_dialog);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        u = getIntent().getParcelableExtra("currentUser");

        checkBoxList.add(cbOne);
        checkBoxList.add(cbTwo);
        checkBoxList.add(cbThree);
    }

//    @SuppressLint("NewApi")
    public void getCheckedDays() {

        if (cbOne.isChecked()) {
            checkedDays = YESTERDAY;
//
//            News news = new News();
//            news.setKey(u.getKey());
//            news.setLcode(u.getProperties().get(0).getLcode());
//            news.setAccount(u.getAccount());
//            news.setNewsOrderBy("");
//            news.setNewsOrderType("ASC");
//            news.setNewsDfrom(LocalDate.now().minusDays(1).toString());
//
//            WebApiClient webApiClient = ViewModelProviders.of(this).get(WebApiClient.class);
//            webApiClient.getNews(news).observe(this, new Observer<News>() {
//                @Override
//                public void onChanged(News news) {
//                    if (news.getReceived() != null) {
//                        reservationList.clear();
//                        reservationList.addAll(news.getReceived());
//                        reservationAdapter.notifyDataSetChanged();
//                    } else {
//                        ArrayList<Reservation> tmpList = new ArrayList<>();
//                        tmpList.addAll(news.getReceived());
//                    }
//                }
//            });
        }
        if (cbTwo.isChecked()) {
            checkedDays = TODAY;
        }
        if (cbThree.isChecked()) {
            checkedDays = TOMMOROW;
        }

        if (cbOne.isChecked() && cbTwo.isChecked()) {
            checkedDays = YESTERDAYiTODAY;
        }
        if (cbTwo.isChecked() && cbThree.isChecked()) {
            checkedDays = TODAYiTOMMOROW;
        }


        Intent sendData = new Intent();
        sendData.putExtra("data", checkedDays);
        setResult(RESULT_OK, sendData);
    }

    @OnClick(R.id.btnFilter)
    public void onFilterClick(){
        setResult(RESULT_OK);
        getCheckedDays();
        finish();
    }

    @OnClick(R.id.ivClose)
    public void onCancelClick(){
        setResult(RESULT_CANCELED);
        finish();
    }

    public static void show(Activity activity, String one, String two, String three, String filter){
        Intent i = new Intent(activity, TimeDialog.class);
        i.putExtra("one", one);
        i.putExtra("two", two);
        i.putExtra("three", three);
        i.putExtra("filter", filter);
        activity.startActivityForResult(i, Consts.REQ_TIME_DIALOG);
    }

    public static void show(Fragment fragment, String one, String two, String three, String filter){
        Intent i = new Intent(fragment.getActivity(), TimeDialog.class);
        i.putExtra("one", one);
        i.putExtra("two", two);
        i.putExtra("three", three);
        i.putExtra("filter", filter);
        fragment.startActivityForResult(i, Consts.REQ_TIME_DIALOG);
    }
}
