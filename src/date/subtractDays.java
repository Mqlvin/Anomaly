package date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class subtractDays {
    public static Date subtract(Date date, int days) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, - days);
        return gc.getTime();
    }
}