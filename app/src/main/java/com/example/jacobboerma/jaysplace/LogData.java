package com.example.jacobboerma.jaysplace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matthew on 11/10/17.
 */

public class LogData {
    public void writeLogs(ArrayList<LogEntry> logEntryArrayList){

        try {
            FileOutputStream fos = new FileOutputStream("purchaseHistory.logShit");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i< logEntryArrayList.size(); i++) {
                oos.writeObject(logEntryArrayList.get(i).getItem());
                oos.writeObject(logEntryArrayList.get(i).getDateEntered());
            }
            oos.close();
        }
        catch(Exception ex){

        }
    }

    public ArrayList<LogEntry> readLogs(){
        try {
            FileInputStream fis = new FileInputStream("purchaseHistory.logShit");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<LogEntry> output = new ArrayList<>();
            String item;
            while( (item = (String)ois.readObject()) != null ) {
                output.add(new LogEntry((Date)ois.readObject(),item));
            }

            ois.close();
            return output;
        }
        catch(Exception ex){

        }
        return null;
    }
}
