package com.appstone.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtCall;
    private EditText mEtSearchURL;
    private EditText mEtEmailAddress;
    private EditText mEtSubject;
    private EditText mEtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        ImageView btnCall = findViewById(R.id.btn_call);
        ImageButton btnSearch = findViewById(R.id.btn_search);
        Button btnSendEmail = findViewById(R.id.btn_send_email);

        mEtCall = findViewById(R.id.et_phone_number);
        mEtSearchURL = findViewById(R.id.et_search_url);
        mEtEmailAddress = findViewById(R.id.et_email_address);
        mEtSubject = findViewById(R.id.et_email_subject);
        mEtText = findViewById(R.id.et_email_text);

        btnCall.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                String phoneNumber = mEtCall.getText().toString();
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(ImplicitIntentActivity.this, "Please Enter your PhoneNumber", Toast.LENGTH_LONG).show();
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    Uri calluri = Uri.parse("tel:".concat(phoneNumber));
                    callIntent.setData(calluri);
                    startActivity(callIntent);
                }
                break;

            case R.id.btn_search:
                String url = mEtSearchURL.getText().toString();
                if (url.isEmpty()) {
                    Toast.makeText(ImplicitIntentActivity.this, getString(R.string.alert_url_empty), Toast.LENGTH_LONG).show();
                } else {
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(url));
                    startActivity(urlIntent);
                }
                break;

            case R.id.btn_send_email:
                String emailAddress = mEtEmailAddress.getText().toString();
                String subject = mEtSubject.getText().toString();
                String text = mEtText.getText().toString();

                if (!emailAddress.isEmpty() && !subject.isEmpty() && !text.isEmpty()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, text);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, "Open Email"));
                } else {
                    if (emailAddress.isEmpty()) {
                        Toast.makeText(ImplicitIntentActivity.this, "Email Address is Empty", Toast.LENGTH_LONG).show();
                    } else if (subject.isEmpty()) {
                        Toast.makeText(ImplicitIntentActivity.this, "Subject is Empty", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ImplicitIntentActivity.this, "Text is Empty", Toast.LENGTH_LONG).show();
                    }
                }
                break;

        }
    }
}
