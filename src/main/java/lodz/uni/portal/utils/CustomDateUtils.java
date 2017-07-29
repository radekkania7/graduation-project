package lodz.uni.portal.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CustomDateUtils {

    public static boolean isDateBeforeToday(Date date) {
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        return date.before(today);
    }

    public static boolean isDateAfterToday(Date date) {
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        return date.after(today);
    }

    public static Date convertStringToSqlDate(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy");
        LocalDate date = LocalDate.parse(source);
        return Date.valueOf(date);
    }

    public static boolean isDate(Object object) {
        return object instanceof Date;
    }

    public static Time getSqlTimeFromString(String source) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Time time = null;
        try {
            long milliSeconds = format.parse(source).getTime();
            time = new Time(milliSeconds);
        } catch (Exception e) {
            //in the case when Exception was thrown
            //returned is null
        }
        return time;
    }

}
