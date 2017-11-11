package com.example.jacobboerma.jaysplace;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class OptionActivity extends AppCompatActivity {
    final static int TIME_OUT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent(OptionActivity.this, CalculatePurchasesActivity.class);
                        startActivity(myIntent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, TIME_OUT);
            }
        });

        final Button flexButton = findViewById(R.id.enter_flex_button);
        flexButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent(OptionActivity.this, EnterFlexActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, TIME_OUT);
            }
        });

        final Button spentButton = findViewById(R.id.flex_spent_button);
        spentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent(OptionActivity.this, FlexSpentActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, TIME_OUT);
            }
        });

        final TextView text = findViewById(R.id.editText2);
        int val;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        ArrayList<LogEntry> entries = LogData.readLogs(cal, this);
        int sum = 0;
        Map<String, Integer> valueMap = Prices.getPrices(this);
        for (LogEntry entry : entries) {
            sum += valueMap.get(entry.getItem());
        }
        text.setText((val = LogData.readFlex(this)) > -1 ? String.format("$%.2f of Flex Left", (float) ((val - sum)) / 100) : "Unknown flex amount");
    }
}
