package com.example.jacobboerma.jaysplace;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new StartCalc().execute();
    }

    protected void finishDis() {
        // handler waits for a TIME_OUT time, then runs the code in run()
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(MainActivity.this, OptionActivity.class);
                startActivity(myIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, TIME_OUT);
    }

    private class StartCalc extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... blah) {
            Calculator.start();
            return null;
        }

        protected void onProgressUpdate()
        {
            // update - say we're "calculating"
        }

        protected void onPostExecute(Void result)
        {
            finishDis();
        }
    }
}
