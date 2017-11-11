package com.example.jacobboerma.jaysplace;

import java.util.Calendar;

/**
 * Created by matthew on 11/10/17.
 */

public class LogEntry {
    private Calendar dateEntered;
        private String item;

    public LogEntry(Calendar entryDate, String itemBought) {
        dateEntered = entryDate;
        item = itemBought;
    }

    public Calendar getDateEntered() {
        return dateEntered;
    }

    public String getItem() {
        return item;
    }
}
