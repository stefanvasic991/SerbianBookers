package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyswitch.serbianbookers.Consts;
import com.easyswitch.serbianbookers.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyswitch.serbianbookers.Consts.ARRIVAL;
import static com.easyswitch.serbianbookers.Consts.ARRIVALiSTAY;
import static com.easyswitch.serbianbookers.Consts.DEPARTURE;
import static com.easyswitch.serbianbookers.Consts.STAY;
import static com.easyswitch.serbianbookers.Consts.STAYiDEPARTURE;
import static com.easyswitch.serbianbookers.Consts.TODAY;
import static com.easyswitch.serbianbookers.Consts.TODAYiTOMMOROW;
import static com.easyswitch.serbianbookers.Consts.YESTERDAYiTODAY;

public class StatusDialog extends AppCompatActivity {

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

    String checkedStatus;
    List<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Dialog);
        setContentView(R.layout.status_dialog);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        checkBoxList.add(cbOne);
        checkBoxList.add(cbTwo);
        checkBoxList.add(cbThree);
    }

    public void getCheckedStatus()
    {
        if (cbOne.isChecked()) {
            checkedStatus = ARRIVAL;
        }
        if (cbTwo.isChecked()) {
            checkedStatus = STAY;
        }
        if (cbThree.isChecked()) {
            checkedStatus = DEPARTURE;
        }
        if (cbOne.isChecked() && cbTwo.isChecked()) {
            checkedStatus = ARRIVALiSTAY;
        }
        if (cbTwo.isChecked() && cbThree.isChecked()) {
            checkedStatus = STAYiDEPARTURE;
        }
        if (!cbOne.isChecked() && !cbTwo.isChecked() && !cbThree.isChecked()) {
            checkedStatus = STAY;

        }

        Intent sendStatus = new Intent();
        sendStatus.putExtra("status", checkedStatus);
        setResult(RESULT_OK, sendStatus);
    }

    @OnClick(R.id.btnFilter)
    public void onFilterClick(){
        setResult(RESULT_OK);
        getCheckedStatus();
        finish();
    }

    @OnClick(R.id.ivClose)
    public void onCancelClick(){
        setResult(RESULT_CANCELED);
        finish();
    }

    public static void show(Activity activity, String one, String two, String three, String filter){
        Intent i = new Intent(activity, StatusDialog.class);
        i.putExtra("one", one);
        i.putExtra("two", two);
        i.putExtra("three", three);
        i.putExtra("filter", filter);
        activity.startActivityForResult(i, Consts.REQ_STATUS_DIALOG);

    }

    public static void show(Fragment fragment, String one, String two, String three, String filter){
        Intent i = new Intent(fragment.getActivity(), StatusDialog.class);
        i.putExtra("one", one);
        i.putExtra("two", two);
        i.putExtra("three", three);
        i.putExtra("filter", filter);
        fragment.startActivityForResult(i, Consts.REQ_STATUS_DIALOG);
    }
}
