package com.example.jacobboerma.jaysplace;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Calculator class
 * will reference an xml to get prices and items - at start of program (main activity)
 * will reference an xml to get preferred items - at start of program (IF previous preferred items)
 *
 * will reference an xml to get preferred items - again if need be
 *
 * will write save the most preferred items to itself,
 * so can be accessed directly (with little computation) by Android
 */

public class Calculator
{
    private static Map<String, Integer> items;
    private static Map<String, Integer> hatedItems;
    private static Map<String, Integer> prefItems;
    private static Map<String, Integer> recItems;
    private static int flexAvailable;
    private static int flexPerWeek;

    private static ArrayList<LogEntry> entries;

    public static Map<String, Integer> getItems() {
        return items;
    }

    /**
     * initiate items
     * initiates recItems
     * reads from file to write to items
     */
    public static void start()
    {
        items = new HashMap<String, Integer>();
        prefItems = new HashMap<String, Integer>();
        recItems = new HashMap<String, Integer>();
        hatedItems = new HashMap<String, Integer>();
        items.put("Pizza", 100); // TODO: get a file working and replace this //Prices.getPrices();
        items.put("Love", 450);
        items.put("Sanity", 700);
        items.put("Myself", 950);
        hatedItems.put("Myself", 950);
        prefItems.put("Sanity", 700);
        flexAvailable = 1400;

//        try {
//            entries = LogData.readLogs();
//            int moneyUsed = 0;
//            for (int i = 0; i < entries.size(); i++) {
//                moneyUsed += items.get(entries.get(i).getItem());
//            }
//            flexPerWeek = LogData.readFlex();
//            flexAvailable = flexPerWeek - moneyUsed;
//        }
//        catch (Exception ex)
//        {
//            Log.d("ERROR", "Null entries");
//        }


        //itemCosts = new ArrayList<String>();
    }

    /**

     * Will write to an file, adding an item.
     * @param item String defining item
     */
    public static void addPreferredItem(String item)
    {
        int price = items.get(item);
        prefItems.put(item, price);
        updateRecItems();
    }

    public static void removePreferredItem(String item)
    {
        prefItems.remove(item);
        updateRecItems();
    }

    /**
     * Will write to aa file, adding an item.
     * @param item String defining item
     */
    public static void addHatedItem(String item)
    {
        int price = items.get(item);
        hatedItems.put(item, price);
        updateRecItems();
    }

    public static void removeHatedItem(String item)
    {
        hatedItems.remove(item);
        updateRecItems();
    }

    /**
     * Write current recItems to the file (overwrite whole thing)
     */
    public static void updateRecItems()
    {
        // TODO: RecItems
    }

    public static Map<String, Integer> getRecItems()
    {
        return recItems;
    }

    public static ArrayList<String> getRecItemsArrayList()
    {
        ArrayList<String> myList = new ArrayList<String>();
        Object[] keys = recItems.keySet().toArray();
        for(int i = 0; i < recItems.keySet().size(); i++)
        {
            myList.add(keys[i].toString() + " : " + recItems.get(keys[i].toString()));
        }
        return myList;
    }

    /**
     * Will find arraylist containing items, number of them, and cost
     * WIll save as a static arrayList that can be accessed by other code
     */
    public static void calculate()
    {
        // TODO: implement algorithms.
        Log.d("HAPPENING", "Calculate is happening");
        //recItems = prefItems;
        sketchCalcForThon();
    }

    public static void sketchCalcForThon()
    {
        Object[] itemKeys = items.keySet().toArray();
        Map<String, Integer> tempMap = new HashMap<String, Integer>();
        for(int i = 0; i < items.size(); i++)
        {
            tempMap.put((String) itemKeys[i], items.get(itemKeys[i].toString()));
        }
        Object[] hatedKeys = hatedItems.keySet().toArray();
        for(int i = 0; i < hatedItems.size(); i++)
        {
            tempMap.remove(hatedKeys[i]);
        }

        Object[] prefKeys = prefItems.keySet().toArray();
        for(int i = 0; i < prefItems.size(); i++)
        {
            int cost = items.get(prefKeys[i].toString());
            if(flexAvailable - cost >= 0)
            {
                flexAvailable -= cost;
                recItems.put(prefKeys[i].toString(), cost);
            }
        }

        Object[] tempKeys = tempMap.keySet().toArray();
        for(int i = 0; i < tempMap.size(); i++)
        {
            int cost = items.get(tempKeys[i].toString());
            if(flexAvailable - cost >= 0)
            {
                flexAvailable -= cost;
                recItems.put(tempKeys[i].toString(), cost);
            }
        }
    }
}
