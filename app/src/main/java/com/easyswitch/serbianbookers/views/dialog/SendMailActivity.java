package com.easyswitch.serbianbookers.views.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.easyswitch.serbianbookers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendMailActivity extends AppCompatActivity {

    @BindView(R.id.etSubject)
    EditText etSubject;
    @BindView(R.id.etContent)
    EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fabSendMail)
    public void sendEmail() {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"stefanvasic991@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, etContent.getText().toString());
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
