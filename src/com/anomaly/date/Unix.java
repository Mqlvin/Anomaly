package com.anomaly.date;

import java.util.Date;

public class Unix {
    public static void toUnix() {

    }

    public static Date toDate(Long unix) {
        Date date = new Date();
        date.setTime(unix *1000);
        return date;
    }
}
