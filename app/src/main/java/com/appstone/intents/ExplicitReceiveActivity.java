package com.appstone.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitReceiveActivity extends AppCompatActivity {

    private EditText mEtReceivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_receive);

        mEtReceivedData = findViewById(R.id.et_received_text);

        Bundle data = getIntent().getExtras();

        String sentText = data.getString("DATA");

        mEtReceivedData.setText(sentText);


        Button btnSend = findViewById(R.id.btn_send);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("RECITIFIED_DATA", mEtReceivedData.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
