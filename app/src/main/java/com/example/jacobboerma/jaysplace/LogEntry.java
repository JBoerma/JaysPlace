package com.example.jacobboerma.jaysplace;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matthew on 11/10/17.
 */

public class LogEntry {
        private Date dateEntered;
        private String item;

    public Date getDateEntered() {
        return dateEntered;
    }

    public String getItem() {
        return item;
    }
    public LogEntry(Date entryDate, String itemBought){
        dateEntered = entryDate;
        item = itemBought;
    }
}
