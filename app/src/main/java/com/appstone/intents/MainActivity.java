package com.appstone.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnImplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImplicit = findViewById(R.id.btn_implicit);

        btnImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ImplicitIntentActivity.class));
            }
        });
    }


    public void onExplicitIntentClicked(View view) {
        startActivity(new Intent(MainActivity.this, ExplicitIntentActivity.class));
    }
}
