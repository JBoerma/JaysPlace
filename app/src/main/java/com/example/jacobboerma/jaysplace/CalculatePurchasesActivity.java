package com.example.jacobboerma.jaysplace;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalculatePurchasesActivity extends AppCompatActivity {
    private ListView itemListView;
    private String[] stringArray;

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(CalculatePurchasesActivity.this, OptionActivity.class);
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_purchases);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("HAPPENING", "before CalculateFood is happening");
        new CalculateFood().execute();
        Log.d("HAPPENING", "after CalculateFood is happening");
    }


    protected void onResume(Bundle savedInstanceState)
    {
        Log.d("HAPPENING", "before CalculateFood is happening");
        new CalculateFood().execute();
        Log.d("HAPPENING", "after CalculateFood is happening");
    }




    private class CalculateFood extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... blah) {
            Calculator.calculate(); // has the Calculator update values
            return null;
        }

        protected void onProgressUpdate()
        {
            // update - say we're "calculating"
        }

        protected void onPostExecute(Void result)
        {
            // change activities to display items, their costs, current flex, and total cost.
            Log.d("HAPPENING", "onPostExecute is happening");
            itemListView = findViewById(R.id.item_list);
            itemListView.setAdapter(
                    new ArrayAdapter<String>(CalculatePurchasesActivity.this,
                            android.R.layout.simple_list_item_1,
                            Calculator.getRecItemsArrayList())
            );
        }
    }
}
