package com.example.jacobboerma.jaysplace;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by matthew on 11/11/17.
 */

public class Prices {
    public static Map<String, Integer> getPrices(Context context){
        Map<String, Integer> out = new HashMap<>();
        try {
            InputStream in = context.getResources().openRawResource(R.raw.prices);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while( (line = br.readLine()) != null ){
                String[] things = line.split(",");
                out.put(things[0],Integer.parseInt(things[1]));
            }
            return out;
        }
        catch (Exception ex){
            Log.e(TAG, "getPrices: "+ex.toString());

        }
        return null;
    }
}
