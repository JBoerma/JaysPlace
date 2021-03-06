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
import android.widget.TextView;

public class EnterFlexActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(EnterFlexActivity.this, OptionActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_flex);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text = findViewById(R.id.editText);
        final FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    int amount = Integer.parseInt(text.getText().toString())*100;
                    Calculator.setFlex(amount);
                    Calculator.resetRecList();
                    LogData.writeFlex(amount, EnterFlexActivity.this);
                    goToOption();
                }
                catch(Exception ex)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(EnterFlexActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Please enter an actual value");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    Log.d("Testin", "onClick: Clicked");
                }
            }
        });
    }

    private void goToOption(){
        Intent intent = new Intent(this,OptionActivity.class);
        startActivity(intent);
        finish();
    }
}
