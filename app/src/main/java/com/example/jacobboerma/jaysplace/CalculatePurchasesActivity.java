package com.example.jacobboerma.jaysplace;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatePurchasesActivity extends AppCompatActivity {
    private ListView itemListView;
    private String[] stringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_purchases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //itemListView = findViewById(R.id.item_list);

//        String[] stringArray = {"HI", "BYE"};
//        itemListView.setAdapter(
//                new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1,
//                        stringArray)
//        );
        Log.d("HAPPENING", "before CalculateFood is happening");
        new CalculateFood().execute();
        Log.d("HAPPENING", "after CalculateFood is happening");
    }




    private class CalculateFood extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... blah) {
            Calculator.calculate(); // has the Calculator update values
            Calculator.addPreferredItem("Pizza");
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
            //writeToList();
        }

        void writeToList()
        {
            itemListView = findViewById(R.id.item_list);
            itemListView.setAdapter(
                    new ArrayAdapter<String>(CalculatePurchasesActivity.this,
                            android.R.layout.simple_list_item_1,
                            Calculator.getRecItemsArrayList())
            );
        }
    }
}
