package lodz.uni.portal.utils;

import java.sql.Date;
import java.util.Calendar;

public class CustomDateUtils {

    public static boolean isDateBeforeToday(Date date) {
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        return date.before(today);
    }
}
