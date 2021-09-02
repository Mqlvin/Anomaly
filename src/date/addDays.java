package date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class addDays {
    public static Date add(Date date, int days) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, + days);
        return gc.getTime();
    }
}
