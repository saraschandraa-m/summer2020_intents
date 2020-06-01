package com.appstone.intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExplicitIntentActivity extends AppCompatActivity {

    private EditText mEtSendData;
    private TextView mTvEditedData;


    /**
    1. StartActivityForResult : Expects a result back from the intent transition
     2. Create Intent the ususal way
     3. Instead of startActivity we will be using startActivityForResult
    **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        mEtSendData = findViewById(R.id.et_send_data);
        mTvEditedData = findViewById(R.id.tv_edited_text);
    }


    public void sendDataButtonClicked(View view) {
        String sendData = mEtSendData.getText().toString();

        Intent sendIntent = new Intent(ExplicitIntentActivity.this, ExplicitReceiveActivity.class);
        sendIntent.putExtra("DATA", sendData);
        startActivity(sendIntent);
    }

    public void sendDataandReceiveButtonClicked(View view) {
        String sendData = mEtSendData.getText().toString();
        Intent sendIntentResult = new Intent(ExplicitIntentActivity.this, ExplicitReceiveActivity.class);
        sendIntentResult.putExtra("DATA", sendData);
        startActivityForResult(sendIntentResult, 1234);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            Bundle returnData = data.getExtras();
            String rectifiedData = returnData.getString("RECITIFIED_DATA");

            mTvEditedData.setText(rectifiedData);
        }
    }
}
