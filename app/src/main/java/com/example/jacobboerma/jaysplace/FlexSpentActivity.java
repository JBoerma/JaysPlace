package com.example.jacobboerma.jaysplace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class FlexSpentActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(FlexSpentActivity.this, OptionActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_spent);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Map<String, Integer> foodMap = Prices.getPrices(this);

        final Spinner spinner = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        adapter.addAll(foodMap.keySet());
        adapter.sort(String.CASE_INSENSITIVE_ORDER);
        spinner.setAdapter(adapter);

        final TextView text = findViewById(R.id.editText);
        final FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Object choice = spinner.getSelectedItem();

                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
                    cal.clear(Calendar.MINUTE);
                    cal.clear(Calendar.SECOND);
                    cal.clear(Calendar.MILLISECOND);
                    cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

                    ArrayList<LogEntry> logsData = LogData.readLogs(cal, FlexSpentActivity.this);
                    Log.e("Count", "" + logsData.size());
                    //Log.e("Fucking hell", "onClick: "+logsData );
                    logsData.add(0, new LogEntry(Calendar.getInstance(), (String) choice));

                    //Log.e("AAAAA", "onClick: "+logsData.size());

                    LogData.writeLogs(logsData, FlexSpentActivity.this);
                    for (LogEntry entry : logsData) {
                        Log.e("Test", entry.getItem());
                    }
                    Calculator.resetRecList();
                    goToOption();
                } catch (Exception ex) {
                    AlertDialog alertDialog = new AlertDialog.Builder(FlexSpentActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Please enter an actual value");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    //Log.e("God damn it",ex.toString());
                }
            }
        });
    }

    private void goToOption() {
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
        finish();
    }
}
