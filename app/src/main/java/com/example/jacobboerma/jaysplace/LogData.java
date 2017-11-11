package com.example.jacobboerma.jaysplace;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by matthew on 11/10/17.
 */

public class LogData {
    public static void writeLogs(ArrayList<LogEntry> logEntryArrayList, Context context){

        try {
            OutputStream os = context.openFileOutput("purchaseHistory.logShit", Context.MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            Log.e(TAG, "writeLogs: " + logEntryArrayList.size());
            for (LogEntry entry : logEntryArrayList) {
                bw.write(String.format("%s,%s\n", "" + entry.getDateEntered().getTimeInMillis(), entry.getItem()));
            }
            bw.close();
            os.close();
        }
        catch(Exception ex){
            Log.e(TAG, "writeLogs: " + ex.toString());
        }
    }

    public static ArrayList<LogEntry> readLogs(Context context){

        ArrayList<LogEntry> out = new ArrayList<>();
        try {
            InputStream in = context.openFileInput("purchaseHistory.logShit");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] things = line.split(",");
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(Long.parseLong(things[0]));
                out.add(new LogEntry(c, things[1]));
            }
            br.close();
            in.close();
            return out;
        }
        catch(Exception ex){
            return null;
        }
    }

    public static ArrayList<LogEntry> readLogs(Calendar earliest, Context context) {

        ArrayList<LogEntry> out = new ArrayList<>();
        try {
            InputStream in = context.openFileInput("purchaseHistory.logShit");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                String[] things = line.split(",");
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(Long.parseLong(things[0]));
                Log.e(TAG, "C time: " + c.getTimeInMillis());
                Log.e(TAG, "Earliest time:" + earliest.getTimeInMillis());
                if (c.before(earliest)) {
                    return out;
                }
                out.add(new LogEntry(c, things[1]));
                Log.e(TAG, "readLogs: " + out.size());
            }
            return out;
        } catch (java.io.FileNotFoundException ex) {
            Log.e(TAG, "readLogs: WHY IS THIS STILL HAPPENING");
            return new ArrayList<>();
        }
        catch(Exception ex){
            Log.e(TAG, "readLogs: " + ex.toString());
            Log.e(TAG, "" + out.size());
            return new ArrayList<>();
        }
    }
    public static int readFlex(Context context){
        try{
            FileInputStream fis = context.openFileInput("amount.flex");
            ObjectInputStream ois = new ObjectInputStream(fis);

            int out = ois.readInt();
            ois.close();
            return out;
        }
        catch (Exception ex){
            Log.e(TAG, "readFlex: " + ex.toString());
        }
        return -1;
    }
    public static void writeFlex(int flex, Context context){
        try {
            FileOutputStream fos = context.openFileOutput("amount.flex",Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(flex);
            oos.close();
        }
        catch (Exception ex){

        }
    }
}
