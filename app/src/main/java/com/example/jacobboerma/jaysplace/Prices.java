package com.example.jacobboerma.jaysplace;

import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthew on 11/11/17.
 */

public class Prices {
    public Map<String, Integer> getPrices(){
        Uri path = Uri.parse("android.resource://com.example.jacobboerma.jaysplace/raw/prices.csv");
        File prices = new File(path.toString());
        Map<String, Integer> out = new HashMap<>();
        try {
            InputStream in = new FileInputStream(prices);
            BufferedInputStream bf = new BufferedInputStream(in);
            ArrayList<String> lines = new ArrayList<>();
            for(String line:lines){
                String[] things = line.split(",");
                out.put(things[0],Integer.parseInt(things[1]));
            }
            return out;
        }
        catch (Exception ex){

        }
        return null;
    }
}
